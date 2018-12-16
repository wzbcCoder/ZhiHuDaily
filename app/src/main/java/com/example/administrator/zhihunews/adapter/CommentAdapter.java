package com.example.administrator.zhihunews.adapter;


import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.db.model.CircleImageView;
import com.example.administrator.zhihunews.db.model.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
        commentHolder.timedate=(TextView) view.findViewById(R.id.comment_time);
        commentHolder.img=(ImageView) view.findViewById(R.id.comment_image);
        commentHolder.likes=(TextView)view.findViewById(R.id.comment_likes);
        view.setTag(commentHolder);
        bindView(i, view);
        return view;
    }



    //    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//

//    }



    public void bindView(int posotion, View view) {
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm");
        Comment comment = commentList.get(posotion);
        CommentHolder commentHolder = (CommentHolder) view.getTag();
        TextPaint paint=commentHolder.author.getPaint();
        paint.setFakeBoldText(true);
        commentHolder.author.setText(comment.getAuthor());
        commentHolder.content.setText(comment.getContent());
        commentHolder.time=comment.getTime();
        commentHolder.timedate.setText(sdf.format(new Date(commentHolder.time*1000)));
        commentHolder.likes.setText(String.valueOf(comment.getLikes()));
        Glide.with(context).load(commentList.get(posotion).getAvatar()).into(commentHolder.img);
    }

    public class CommentHolder {
        public TextView author;
        public TextView content;
        public TextView likes;
        public ImageView img;
        public int time;
        public TextView timedate;


    }
}

