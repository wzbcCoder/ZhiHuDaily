package com.example.administrator.zhihunews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.db.model.NewsItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/11/28.
 */

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.InfoViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private ArrayList<NewsItem> mData;//用于储存数据
    private Context mContext;//上下文
    InfoViewHolder mHolder = null; //viewholde，可以提高recycleview的性能

    //构造方法，用于接收上下文和展示数据
    public InfoListAdapter(ArrayList<NewsItem> data, Context context) {
        this.mData = data;
        this.mContext = context;
    }


    // 充气一个viewholder
    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView != null && viewType == TYPE_HEADER) return new InfoViewHolder(headView);
        mHolder = new InfoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_base_swipe_list, parent, false));
        return mHolder;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, final int position) {
        //此方法内可以对布局中的控件进行操作
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder);

        holder.title.setText(mData.get(pos).getTitle());// 获取标题
        //FIXED 切换成real pos 就可以解决报错
        final int id = mData.get(pos).getId();
        //从NewsItem中获取图片 使用加载库加载
        Glide.with(mContext).load(mData.get(pos).getImage()).into(holder.img);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(pos,id);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(pos,id);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        //获取数据长度

//        return_self mData.size();
        //llh 更改

        return headView==null? mData.size():mData.size()+1;
    }
    //  回调事件接口

    public static interface OnItemClickListener {
        void onItemClick(int position,int id);

        void onItemLongClick(int position,int id);
    }

    //自己实现的设置点击事件
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {


        TextView title;//标题
        ImageView img;//显示的图片
        TextView headTitle;//头部标题

        public InfoViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            img = (ImageView) itemView.findViewById(R.id.item_image);
//            headTitle = (TextView) itemView.findViewById(R.id.item_headtitle);
        }
    }

    public static final int TYPE_HEADER = 0;//显示headvuiew
    public static final int TYPE_NORMAL = 1;//显示普通的item
    private View headView;//这家伙就是Banner

    public void setHeadView(View headView) {
        this.headView = headView;
        notifyItemInserted(0);
    }

    public View getHeadView() {
        return headView;
    }

    @Override
    public int getItemViewType(int position) {
        if (headView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headView == null ? position : position - 1;
    }

}
