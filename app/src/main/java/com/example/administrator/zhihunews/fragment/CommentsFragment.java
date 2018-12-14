package com.example.administrator.zhihunews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.adapter.CommentAdapter;
import com.example.administrator.zhihunews.app.ClintApplication;
import com.example.administrator.zhihunews.db.model.Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/12.
 */

public class CommentsFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
    private ArrayList<Comment> commentList;
    private int NewsId;
    private ListView listView;
    private CommentAdapter commentAdapter;//适配器


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        addData();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    public void addData(){
        NewsId =  getArguments().getInt("NewsId");
        fetchComment();
        commentAdapter=new CommentAdapter(commentList);
        //从数据库中获取数据
    }
    private void fetchComment(){
        //  创建一个requests请求
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest("http://news-at.zhihu.com/api/4/story/"+NewsId+"/long-comments", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray comments = response.getJSONArray("comments");
                    for (int i= 0 ;i<comments.length();i++){
                       JSONObject comment =  comments.getJSONObject(i);
                        Comment comment1 = new Comment();
                        comment1.setAuthor(comment.getString("author"));
                        comment1.setAvatar(comment.getString("avatar"));
                        comment1.setContext(comment.getString("context"));
                        comment1.setTime(comment.getLong("time"));
                        comment1.setLikes(comment.getInt("likes"));
                        commentList.add(comment1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                    JSONArray topinfos = response.getJSONArray("??");
//
//                    for (int i = 0; i < topinfos.length(); i++) {
//                        JSONObject item = topinfos.getJSONObject(i);
//                        Comment comment1 = new Comment();
//                        comment1.setAuthor(item.getString("author"));
//                        comment1.setAvatar(item.getString("avatar"));
//                        comment1.setContext(item.getString("context"));
//                        author.add(comment1.getAuthor());
//                        avatar.add(comment1.getAvatar());
//                        context.add(comment1.getContext());
//                        time.add(comment1.getTime());


            }
        },new  Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        //add
        // 获取请求队列
        ClintApplication.getmRequestQueue().add(jsonObjectRequest);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        ListView list = (ListView) view.findViewById(R.id.comment_list);
        listView.setAdapter(commentAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    public static CommentsFragment newInstance(int newsId) {
        //TODO(ZJD):添加返回操作
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putInt("NewsId", newsId);
        fragment.setArguments(args);
        return fragment;
    }
}
