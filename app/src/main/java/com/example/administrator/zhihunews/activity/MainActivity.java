package com.example.administrator.zhihunews.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
//import com.example.administrator.zhihunews.db.until.DatabaseHelper;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.adapter.InfoListAdapter;
import com.example.administrator.zhihunews.db.model.NewsItem;
import com.example.administrator.zhihunews.db.until.MyDatabaseHelper;
import com.example.administrator.zhihunews.fragment.BaseFragment;
import com.example.administrator.zhihunews.fragment.NewsDetailFragment;
import com.example.administrator.zhihunews.fragment.NewsListFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.bingoogolapple.bgabanner.BGABanner;


public class MainActivity extends BaseActivity {
    FragmentManager mFragmentManger;
    // Fragment 事务
    FragmentTransaction mFragmentTransaction;
    MyDatabaseHelper mMySqliteHelper;
    private SQLiteDatabase db;


    //NewsItem为自定义的，在NewsItem.java中实现
    private ArrayList<NewsItem> bannerList;//banner控件

    private ArrayList<String> titles;//存放banner中的标题

    private ArrayList<String> images;//存放banner中的图片

    private ArrayList<Integer> ids;//存放每一项的id

    private RequestQueue mQueue;

    private RecyclerView mInfoList;

    private InfoListAdapter adapter;

//    private void initBanner() {
//        //初始化banner
//        titles=new ArrayList<>();
//        ids=new ArrayList<>();
//        images=new ArrayList<>();
//
//        bannerList = new ArrayList<>();
//
//        mQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://news-at.zhihu.com/api/4/news/latest", null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    //解析banner中的数据
//                    JSONArray topinfos = response.getJSONArray("top_stories");
//                    Log.d("TAG", "onResponse: "+topinfos);
//                    for (int i = 0; i < topinfos.length(); i++) {
//                        JSONObject item = topinfos.getJSONObject(i);
//                        NewsItem item1 = new NewsItem();
//                        item1.setImage(item.getString("image"));
//                        item1.setTitle(item.getString("title"));
//                        item1.setId(item.getInt("id"));
//                        bannerList.add(item1);
//                        titles.add(item1.getTitle());
//                        images.add(item1.getImage());
//                        ids.add(item1.getId());
//                    }
//
//
//                    setHeader(mInfoList, images, titles, ids);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        mQueue.add(jsonObjectRequest);
//
//
//    }
//
//    private void setHeader(RecyclerView view, ArrayList<String> urls, ArrayList<String> titles, final ArrayList<Integer> ids) {
//        View header = LayoutInflater.from(this).inflate(R.layout.headview, view, false);
//        //找到banner所在的布局
//        BGABanner banner = (BGABanner) header.findViewById(R.id.banner);
//        //绑定banner
//        banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
//
//
//            @Override
//            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
//                Glide.with(MainActivity.this)
//                        .load(model)
//                        .centerCrop()
//                        .dontAnimate()
//                        .into(itemView);
//            }
//        });
//        banner.setDelegate(new BGABanner.Delegate() {
//            @Override
//            public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
//                //此处可设置banner子项的点击事件
//
//            }
//        });
//        banner.setData(urls, titles);
//        adapter.setHeadView(header);//向适配器中添加banner
//    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsListFragment newsListFragment = NewsListFragment.newInstance();
        perpareFragmentManager();
        mFragmentTransaction.replace(R.id.NewsListFragment, newsListFragment);


        // 提交
        mFragmentTransaction.commit();
        initView();
    }

    private void initView() {
        setTitle("", 1);//设置标题内容，该方法继承自父类，所以再写一次
    }

    private void perpareFragmentManager() {
        // 通用的FragmentManager
        mFragmentManger = getSupportFragmentManager();
        mFragmentTransaction = getFragmentManager().beginTransaction();
    }

    @Override
    public void setTitle(String title, int type) {
        super.setTitle(title, type);
    }

    // 创建Fragment之前的操作
    public void switchFragment(Fragment fragment) {

        // 准备FragmentManager
        perpareFragmentManager();


        mFragmentTransaction.replace(R.id.NewsListFragment, fragment);
        // 加入到回退栈当中
        mFragmentTransaction.addToBackStack(null);
        // 提交
        mFragmentTransaction.commit();
    }


}
