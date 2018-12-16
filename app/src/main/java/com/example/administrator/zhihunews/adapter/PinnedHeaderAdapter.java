package com.example.administrator.zhihunews.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2018/12/14.
 */

public abstract class PinnedHeaderAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    /**
     * 判断该position对应的位置是要固定
     *
     * @param position adapter position
     * @return true or false
     */
    public abstract boolean isPinnedPosition(int position);

}

