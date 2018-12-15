package com.example.administrator.zhihunews.adapter;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.db.model.Comment;

import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * Created by Administrator on 2018/12/14.
 */

public class CommentAdapter extends BaseAdapter {
    public ArrayList<Comment> commentList;
    public CommentAdapter(Context context,ArrayList<Comment> commentList) {

        
        this.commentList = commentList;
    }

    @Override
    public int getCount( ) {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecyclerView.ViewHolder holder =null;
        if (convertView==null){

            Comment comment=(Comment) commentList.get(position);
            TextView author=(TextView)convertView.findViewById(R.id.comment_username);
            author.setText(comment.getAuthor());
        }


        return null;
    }
}
