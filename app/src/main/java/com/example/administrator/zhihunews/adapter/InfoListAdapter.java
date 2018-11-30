package com.example.administrator.zhihunews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
    private ArrayList<NewsItem> mData;//用于储存数据
    private Context mContext;//上下文
    InfoViewHolder holder=null; //viewholde，可以提高recycleview的性能
    //构造方法，用于接收上下文和展示数据
    public  InfoListAdapter(ArrayList<NewsItem> data,Context context) {
        this.mData = data;
        this.mContext=context;
    }


    // 充气一个viewholder
    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        holder=new InfoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.info_item,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        //此方法内可以对布局中的控件进行操作

        holder.title.setText(mData.get(position).getTitle());// 获取标题

        //从NewsItem中获取图片 使用加载库加载
        Glide.with(mContext).load(mData.get(position).getImage()).into(holder.img);
    }
    @Override
    public int getItemCount() {

        //获取数据长度

        return mData.size();
    }

    public static interface OnItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
    }


    class InfoViewHolder extends RecyclerView.ViewHolder {
        //此部分我也难以用语言来解释，诸位可以搜索下网上各路大牛的详解
        //我也需要学习


        TextView title;//标题
        ImageView img;//显示的图片
        TextView headTitle;//头部标题

        public InfoViewHolder(View itemView) {
            super(itemView);

            title= (TextView) itemView.findViewById(R.id.item_title);
            img= (ImageView) itemView.findViewById(R.id.item_image);
            headTitle= (TextView) itemView.findViewById(R.id.item_headtitle);
        }
    }


}
