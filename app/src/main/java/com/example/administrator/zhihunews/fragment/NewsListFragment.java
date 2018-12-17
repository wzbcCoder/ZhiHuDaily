package com.example.administrator.zhihunews.fragment;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.activity.MainActivity;
import com.example.administrator.zhihunews.adapter.InfoListAdapter;
import com.example.administrator.zhihunews.app.ClintApplication;
import com.example.administrator.zhihunews.db.daoImp.NewsItemDaoImp;
import com.example.administrator.zhihunews.db.model.NewsItem;
import com.example.administrator.zhihunews.decoration.SectionDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;

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

    private ArrayList<String> images;//存放banner中的图片kkuyy

    private ArrayList<Integer> ids;//存放每一项的id

    private RequestQueue mQueue;


    // 初始化视图
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  应该使用view 去充气
        mView = inflater.inflate(R.layout.tab_layout, container, false);
        // 找到里面的listView
//        mListView = (ListView) mView.findViewById(R.id.tab_listview);

        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titles = new ArrayList<>();
        ids = new ArrayList<>();
        images = new ArrayList<>();
        bannerList = new ArrayList<>();
        mQueue = Volley.newRequestQueue(mActivity);
        mInfoList = (RecyclerView) mActivity.findViewById(R.id.infolist);//绑定RecycleView
        mInfoList.setLayoutManager(new LinearLayoutManager(mActivity));//设置布局管理器，你可以通过这个来决定你是要做一个Listview还是瀑布流
        // 添加滑动到底部的监听
        mInfoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 如果滑动到底部
                if (!recyclerView.canScrollVertically(1)) {
                    // 从recyclerView中获取layoutManager实例
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int itemCount = linearLayoutManager.getItemCount() - 1;
                    int lastIndex = itemCount - 1;
                    NewsItem lastItem = mDatas.get(lastIndex);
                    String stringDate = lastItem.getDate();

                    Date date = null;
                    try {
                        date = (new SimpleDateFormat("yyyyMMdd")).parse(stringDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    fetchDaysNewsList(date);

                }
            }
        });
        mInfoList.addItemDecoration(new SectionDecoration(mActivity, new SectionDecoration.DecorationCallback() {
            @Override
            public long getGroupId(int position) {
                String stringDate = mDatas.get(position).getDate();
                int days = 0;
                try {
                    Date date = (new SimpleDateFormat("yyyyMMdd")).parse(stringDate);
                    // 计算时间差
                    days = (int) ((new Date().getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                System.out.println(days);
                return days;
            }

            @Override
            public String getGroupFirstLine(int position) {

                return mDatas.get(position).getDate();
            }
        }));


        initData();
        //抓取最上方的新闻
//        fetchLeastHeaderNews();


    }

    // 将数据放入InfoListAdapter中
    private void addDataToAdapter() {
        adapter = new InfoListAdapter(mDatas, mActivity);//初始化适配器
        adapter.setOnItemClickListener(new InfoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int id) {
                NewsDetailFragment newsDetailFragment = NewsDetailFragment.newInstance(id);
                MainActivity activity = (MainActivity) getActivity();
                activity.switchFragment(newsDetailFragment);
            }

            @Override
            public void onItemLongClick(int position, int id) {
//                System.out.println(id);
            }
        });


        //为ReycleView设置适配器
    }

    //抓取最上方的新闻
    private void fetchLeastHeaderNews() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://news-at.zhihu.com/api/4/news/latest", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //解析banner中的数据
                    JSONArray topinfos = response.getJSONArray("top_stories");
//                    Log.d("TAG", "onResponse: " + topinfos);
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

                    View header = LayoutInflater.from(mActivity).inflate(R.layout.headview, null);
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
                    banner.setData(images, titles);
                    adapter.setHeadView(header);
                    mInfoList.setAdapter(adapter);

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

    private void initData() {
        // 获取最新一天的数据
        Date date = new Date();
        mDatas = new ArrayList<>();
        getData(date);
    }


    // 获取指定日期的新闻标题等
    private void fetchDaysNewsList(final Date date) {
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
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(date);

                        calendar.add(calendar.DATE, -1);
                        Date date1 = calendar.getTime();
                        int preSize = mDatas.size();
                        for (NewsItem item : newsItemDaoImp.findDate(date1)
                                ) {
                            mDatas.add(item);
                        }
//                        mDatas = mDatas

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String dataFormat = sdf.format(date1);
                        String curDate = sdf.format(new Date());
                        if (dataFormat.equals(curDate)) {
                            fetchLeastHeaderNews();
                            addDataToAdapter();
                        }
                        // 告知已经修改数据
                        adapter.notifyItemChanged(preSize);
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

    // 从数据库里面拿到最新一天的数据
    private void getData(Date date) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 将日期向后推一天
        fetchDaysNewsList(calendar.getTime());  // 从接口异步获取数
    }
}






