package com.example.administrator.zhihunews.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.activity.BaseActivity;
import com.example.administrator.zhihunews.activity.MainActivity;
import com.example.administrator.zhihunews.adapter.PinnedHeaderAdapter;
import com.example.administrator.zhihunews.db.model.NewsItem;

import java.util.List;

/**
 * Created by Administrator on 2018/12/12.
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SectionDecoration";
    private TextPaint textPaint;
    private Paint paint;
    private int topGap;
    private Paint.FontMetrics fontMetrics;
    private DecorationCallback callback;
    // true 是bai
    public static boolean isSun = true;

    public SectionDecoration(Context context,DecorationCallback decorationCallback) {

        // 获取资源
        Resources res = context.getResources();
        // 设置样式
        this.callback = decorationCallback;
        paint = new Paint();
        //TODO:该颜色
        if (isSun){
            paint.setColor(res.getColor(R.color.listbg));
        }else {
            paint.setColor(res.getColor(R.color.listbgc));
        }
//        paint.setColor(res.getColor(R.color.white));
        textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(40);
        if(isSun){
            textPaint.setColor(Color.BLACK);
        }else{
            textPaint.setColor(Color.WHITE);
        }
//        textPaint.setColor(Color.BLACK);
        textPaint.getFontMetrics(fontMetrics);
        textPaint.setTextAlign(Paint.Align.LEFT);
        fontMetrics = new Paint.FontMetrics();
        topGap = res.getDimensionPixelSize(R.dimen.dd_dimen);//32dp

    }
    // 接口
    public interface DecorationCallback {
        // 获取item的groupID
        long getGroupId(int position);
        // 根据item获取组名
        String getGroupFirstLine(int position);
    }

    // 把要固定的View绘制在上层
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
//        if ( parent.getChildCount() > 0){
//            PinnedHeaderAdapter adapter = (PinnedHeaderAdapter)parent.getAdapter();
//            // 找到第一个需要固定的view
//            View firstView =parent.getChildAt(0);
//            int firstAdapterPosition = parent.getChildAdapterPosition(firstView);
//            System.out.println(firstAdapterPosition);
//        }


    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 因为有一个头部的轮播图
        // 所以我们需要在position 上面在-1
        // 这样会产生一个-1的position
        // 我们就对-1 不进行操作
        int pos = parent.getChildAdapterPosition(view)-1;
        if (pos ==-1){

        }
        else {
            long groupId = callback.getGroupId(pos);
            if (groupId < 0) return;
            if (isFirstInGroup(pos)) {//同组的第一个才添加padding
                outRect.top = topGap;
            } else {
                outRect.top = 0;
            }
        }



    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();

        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view)-1;
            if (position ==-1){

            }
            else {
                long groupId = callback.getGroupId(position);
                if (groupId < 0) return;
                String textLine = callback.getGroupFirstLine(position).toUpperCase();
                // 绘制文本
                if (position == 0 || isFirstInGroup(position)) {
                    float top = view.getTop() - topGap;
                    float bottom = view.getTop();
                    c.drawRect(left, top, right, bottom, paint);//绘制白色矩形
                    c.drawText(textLine, left+50, bottom-20, textPaint);//绘制文本
                }
            }

        }

    }
    // 判断一个item是否是第一个在group中的
    private boolean isFirstInGroup(int pos) {
        if (pos == 0) {
            return true;
        } else {
            long prevGroupId = callback.getGroupId(pos - 1);
            long groupId = callback.getGroupId(pos);
            return prevGroupId != groupId;
        }

    }
}
