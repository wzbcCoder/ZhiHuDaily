package com.example.administrator.zhihunews.fragment;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.activity.MainActivity;
import com.example.administrator.zhihunews.adapter.InfoListAdapter;
import com.example.administrator.zhihunews.app.ClintApplication;
import com.example.administrator.zhihunews.db.daoImp.NewsItemDaoImp;
import com.example.administrator.zhihunews.db.model.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by Administrator on 2018/11/23.
 */

public class NewsListFragment extends BaseFragment {
//    private ListView mListView;
//    private SimpleAdapter mAdapter;
    private View mView;
//    private List<Map<String, Object>> mList;
    private ArrayList<NewsItem> mDatas;
    private RecyclerView mInfoList;
    private InfoListAdapter adapter;

    private ArrayList<NewsItem> bannerList;//banner控件

    private ArrayList<String> titles;//存放banner中的标题

    private ArrayList<String> images;//存放banner中的图片

    private ArrayList<Integer> ids;//存放每一项的id

    private RequestQueue mQueue;

    private void initBanner() {
        //初始化banner
        titles=new ArrayList<>();
        ids=new ArrayList<>();
        images=new ArrayList<>();

        bannerList = new ArrayList<>();

        mQueue = Volley.newRequestQueue(mActivity);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://news-at.zhihu.com/api/4/news/latest", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //解析banner中的数据
                    JSONArray topinfos = response.getJSONArray("top_stories");
                    Log.d("TAG", "onResponse: "+topinfos);
                    for (int i = 0; i < topinfos.length(); i++) {
                        JSONObject item = topinfos.getJSONObject(i);
                        NewsItem item1 = new NewsItem();
                        item1.setImage(item.getString("image"));
                        item1.setTitle(item.getString("title"));
                        item1.setId(item.getInt("id"));
                        bannerList.add(item1);
                        titles.add(item1.getTitle());
                        images.add(item1.getImage());
                        ids.add(item1.getId());
                    }


                    setHeader(mInfoList, images, titles, ids);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(jsonObjectRequest);


    }

    private void setHeader(RecyclerView view, ArrayList<String> urls, ArrayList<String> titles, final ArrayList<Integer> ids) {
        View header = LayoutInflater.from(mActivity).inflate(R.layout.headview, view, false);
        //找到banner所在的布局
        BGABanner banner = (BGABanner) header.findViewById(R.id.banner);
        //绑定banner
        banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {


            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(NewsListFragment.this)
                        .load(model)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });
        banner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                //此处可设置banner子项的点击事件

            }
        });
        banner.setData(urls, titles);
        adapter.setHeadView(header);//向适配器中添加banner
    }

    ///




    // 初始化视图
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  应该使用view 去充气
        mView = inflater.inflate(R.layout.tab_layout, container, false);
        // 找到里面的listView
//        mListView = (ListView) mView.findViewById(R.id.tab_listview);
        return mView;
    }

    private void initView() {
        mInfoList = (RecyclerView) mActivity.findViewById(R.id.infolist);//绑定RecycleView
        mInfoList.setLayoutManager(new LinearLayoutManager(mActivity));//设置布局管理器，你可以通过这个来决定你是要做一个Listview还是瀑布流
        adapter = new InfoListAdapter(mDatas, mActivity);//初始化适配器

        // 为InfoListAdapter添加监听事件
        adapter.setOnItemClickListener(new InfoListAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position,int id) {
                NewsDetailFragment newsDetailFragment = NewsDetailFragment.newInstance(id);
                MainActivity activity = (MainActivity )getActivity();
                activity.switchFragment(newsDetailFragment);
            }

            @Override
            public void onItemLongClick(int position,int id) {
                System.out.println(id);
            }
        } );
        mInfoList.setAdapter(adapter);//为ReycleView设置适配器
    }

    private void initData() {
        mDatas = getData();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        initBanner();
    }
    //    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.tab_listview_item,
//                new String[]{"img", "title", "body"},
//                new int[]{R.id.itemimg, R.id.itemtitle, R.id.itembody});      //配置适配器，并获取对应Item中的ID
//        mListView.setAdapter(mAdapter);
//    }

    // 获取指定日期的新闻标题等
    private void fetchDaysNewsList(Date date) {
        // 解析时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // url构造
        String url = "http://news.at.zhihu.com/api/4/news/before/" + sdf.format(date);
        StringRequest mRequest = new StringRequest(Request.Method.GET, url,

                // 响应成功
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 插入数据库
                        NewsItemDaoImp newsItemDaoImp = new NewsItemDaoImp(mActivity);
                        newsItemDaoImp.addDay(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // 响应失败
                        System.out.println("error ----");
                    }
                }
        );
        ClintApplication.getmRequestQueue().add(mRequest);
    }

    // 静态工厂方法获取Fragment实例
    public static NewsListFragment newInstance() {
        NewsListFragment newsListFragment = new NewsListFragment();
        return newsListFragment;
    }


    private ArrayList<NewsItem> getData() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 将日期向后推一天
        fetchDaysNewsList(calendar.getTime());
        NewsItemDaoImp newsItemDaoImp = new NewsItemDaoImp(getActivity());
        ArrayList<NewsItem> newsList = newsItemDaoImp.findDate(date);
        return newsList;
    }
}






