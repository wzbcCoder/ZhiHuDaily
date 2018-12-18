package com.example.administrator.zhihunews.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.activity.MainActivity;
import com.example.administrator.zhihunews.adapter.CommentAdapter;
import com.example.administrator.zhihunews.app.ClintApplication;
import com.example.administrator.zhihunews.db.model.Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

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
    private CommentAdapter commentAdapter;


//适配器


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    // onstart 在 oncreateView 之后执行
    // 可以加载数据
    @Override
    public void onStart() {
        super.onStart();
        addData();
    }

    public void addData(){
        NewsId =  getArguments().getInt("NewsId");
        fetchLongComment();
    }

    private void fetchShortComment() {
        //  创建一个requests请求
        JsonObjectRequest jsonObjectRequest1 =new JsonObjectRequest("http://news-at.zhihu.com/api/4/story/"+NewsId+"/short-comments", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray comments = response.getJSONArray("comments");
                    for (int i= 0 ;i<comments.length();i++){
                        JSONObject comment =  comments.getJSONObject(i);
                        Comment comment1 = new Comment();
                        comment1.setAuthor(comment.getString("author"));
                        comment1.setAvatar(comment.getString("avatar"));
                        comment1.setContent(comment.getString("content"));
                        comment1.setTime(comment.getInt("time"));
                        comment1.setLikes(comment.getInt("likes"));
                        commentList.add(comment1);

                    }
                    commentAdapter=new CommentAdapter(commentList,mActivity);
                    // 给listView 设置是配置
                    listView.setAdapter(commentAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new  Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        ClintApplication.getmRequestQueue().add(jsonObjectRequest1);
    }

    private void fetchLongComment(){
        //  创建一个requests请求
        JsonObjectRequest jsonObjectRequest2 =new JsonObjectRequest("http://news-at.zhihu.com/api/4/story/"+NewsId+"/long-comments", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    commentList = new ArrayList<>();
                    fetchShortComment();

                    JSONArray comments = response.getJSONArray("comments");
                    for (int i= 0 ;i<comments.length();i++){
                       JSONObject comment =  comments.getJSONObject(i);
                        Comment comment1 = new Comment();
                        comment1.setAuthor(comment.getString("author"));
                        comment1.setAvatar(comment.getString("avatar"));
                        comment1.setContent(comment.getString("content"));
                        comment1.setTime(comment.getInt("time"));
                        comment1.setLikes(comment.getInt("likes"));
                        commentList.add(comment1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new  Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        //add
        // 获取请求队列
        ClintApplication.getmRequestQueue().add(jsonObjectRequest2);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comment, container, false);
         listView = (ListView) view.findViewById(R.id.comment_list);
        MainActivity mainActivity = (MainActivity) mActivity;
        mainActivity.setTitle("", 3);

//         list.setAdapter(commentAdapter);
        //
        return view;
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
