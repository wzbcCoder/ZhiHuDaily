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
    public Context context;
    private LayoutInflater minflater;

    public CommentAdapter(ArrayList<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
        minflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = minflater.inflate(R.layout.comment, viewGroup,false);
        CommentHolder commentHolder = new CommentHolder();
        commentHolder.author = (TextView) view.findViewById(R.id.comment_username);
        commentHolder.content = (TextView) view.findViewById(R.id.comment_content);
        commentHolder.time=(TextView)view.findViewById(R.id.comment_time);
        view.setTag(commentHolder);

        bindView(i, view);
        return view;
    }



    //    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//

//    }



    public void bindView(int posotion, View view) {
        Comment comment = commentList.get(posotion);
        CommentHolder commentHolder = (CommentHolder) view.getTag();
        commentHolder.author.setText(comment.getAuthor());
        commentHolder.content.setText(comment.getContent());
        commentHolder.time.setText(comment.getTime());

    }

    public class CommentHolder {
        public TextView author;
        public TextView content;
        public TextView likes;
        public TextView time;
    }
}

