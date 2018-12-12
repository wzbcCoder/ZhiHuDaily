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

    public SectionDecoration(Context context,DecorationCallback decorationCallback) {
        // 获取资源
        Resources res = context.getResources();
        this.callback = decorationCallback;
        paint = new Paint();
        paint.setColor(res.getColor(R.color.colorAccent));

        textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(80);
        textPaint.setColor(Color.BLACK);
        textPaint.getFontMetrics(fontMetrics);
        textPaint.setTextAlign(Paint.Align.LEFT);
        fontMetrics = new Paint.FontMetrics();
        topGap = res.getDimensionPixelSize(R.dimen.dd_dimen);//32dp

    }

    public interface DecorationCallback {
        long getGroupId(int position);

        String getGroupFirstLine(int position);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view)-1;
        if (pos ==-1){

        }
        else {
            long groupId = callback.getGroupId(pos);
            if (groupId < 0) return;
            if (pos == 1 || isFirstInGroup(pos)) {//同组的第一个才添加padding
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
                    c.drawRect(left, top, right, bottom, paint);//绘制红色矩形
                    c.drawText(textLine, left, bottom, textPaint);//绘制文本
                }
            }

        }

    }

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
