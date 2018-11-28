package com.example.administrator.zhihunews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.activity.MainActivity;
import com.example.administrator.zhihunews.adapter.InfoListAdapter;
import com.example.administrator.zhihunews.app.ClintApplication;
import com.example.administrator.zhihunews.db.daoImp.NewsItemDaoImp;
import com.example.administrator.zhihunews.db.model.NewsItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        mInfoList.setAdapter(adapter);//为ReycleView设置适配器
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            NewsItem item = new NewsItem();
            item.setTitle("" + (char) i);
            item.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496110531&di=281ed2731cabceee7c851e5b2ca83a85&imgtype=jpg&er=1&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F15%2F64%2F44c58PICsni_1024.jpg");
            mDatas.add(item);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
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


    private List<? extends Map<String, ?>> getData() {
        Date date = new Date();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 将日期向后推一天
        fetchDaysNewsList(calendar.getTime());
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //将需要的值传入map中

        NewsItemDaoImp newsItemDaoImp = new NewsItemDaoImp(getActivity());
        List<NewsItem> newsList = newsItemDaoImp.findDate(date);
        for (NewsItem item:newsList
             ) {
            System.out.println(item.getTitle());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", item.getDate());
            map.put("body", item.getTitle());
            map.put("img", R.drawable.zzf2);
            list.add(map);
        }

        return list;
    }
}






