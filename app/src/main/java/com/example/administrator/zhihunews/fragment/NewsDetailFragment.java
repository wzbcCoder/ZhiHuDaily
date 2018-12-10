package com.example.administrator.zhihunews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.activity.BaseActivity;
import com.example.administrator.zhihunews.activity.MainActivity;
import com.example.administrator.zhihunews.app.ClintApplication;
import com.example.administrator.zhihunews.db.daoImp.NewsItemDaoImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/30.
 */

public class NewsDetailFragment extends BaseFragment {
    private View mView;
    private WebView mDetail;
    // 新闻的id 从外部传参
    private int mNewsId;
    private String htmlNewsDetail;
    private final String tag = "NewsDetailFragment";
    private BaseActivity baseActivity;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.news_detail_fragment_layout, container, false);
        return mView;
    }

    private void initView() {
        mDetail = (WebView) mActivity.findViewById(R.id.web_view_detail);
    }

    private void initData() {
        mNewsId = getArguments().getInt("NewsId");

        Log.d(tag, "news id is  " + mNewsId);
        fetchNewsDetail();
    }

    // 调用api 获取制定id的新闻
    private void fetchNewsDetail() {
        // 细节新闻的地址
        String url = "http://news-at.zhihu.com/api/4/news/" + mNewsId;
        System.out.println(url);
        StringRequest mRequest = new StringRequest(Request.Method.GET, url,

                // 响应成功
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        HashMap<String,String> newsHashMap= parseNewsDetail(response);
                        htmlNewsDetail = matchDetailHTML(newsHashMap);
                        String mimeType = "text/html";
                        String enCoding = "utf-8";
                        mDetail.loadDataWithBaseURL(null,htmlNewsDetail,mimeType,enCoding,null);

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

    private HashMap<String, String> parseNewsDetail(String newsDetailJson) {
        JSONObject newsDetailJsonObject;
        HashMap<String,String> newsHashMap = new HashMap<>();
        try {
            newsDetailJsonObject = new JSONObject(newsDetailJson);
            JSONArray cssList = (JSONArray) newsDetailJsonObject.get("css");
            String css = (String) cssList.get(0);
            String body = (String)newsDetailJsonObject.get("body");
            String image = (String) newsDetailJsonObject.get("image");
            String js = (String) ((JSONArray) newsDetailJsonObject.get("css")).get(0);
            String title = (String) newsDetailJsonObject.get("title");
            String imageSource = (String) newsDetailJsonObject.get("image_source");
            newsHashMap.put("css",css);
            newsHashMap.put("body",body);
            newsHashMap.put("image",image);
            newsHashMap.put("js",js);
            newsHashMap.put("imageSource",imageSource);
            newsHashMap.put("title",title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsHashMap;
    }

    // 读取newsHashMap中的数据 转换为html
    private  String  matchDetailHTML(HashMap<String,String> newsHashMap){
//        System.out.println(newsHashMap.get("body"));
        String css = newsHashMap.get("css");
        String title = newsHashMap.get("title");
        String image = newsHashMap.get("image");
        String imageSource = newsHashMap.get("imageSource");
        String headLine =
                "<div class=\"img-wrap\">\n" +
                "<h1 class=\"headline-title\">"+title+"</h1>\n" +
                "\n" +
                "\n" +
                "<span class=\"img-source\">"+imageSource+"</span>\n" +
                "\n" +
                "\n" +
                "<img src=\""+image+"\" alt=\"\">\n" +
                "<div class=\"img-mask\"></div>\n" +
                "</div>\n";
        String headers = "<link rel=\"stylesheet\" href=\""+css+"\"> <script src=\"http://static.daily.zhihu.com/js/modernizr-2.6.2.min.js\"></script>";
        String body = "<body>"+newsHashMap.get("body")+"</body>";
        Document doc = Jsoup.parse(body);
        Element headImage = doc.select("div.headline").first();
        Element head = doc.select("head").first();
        head.append(headers);
        headImage.append(headLine);
        doc.select("div.img-place-holder").remove();
        System.out.println(doc.toString());
        return doc.toString();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity)mActivity;
        mainActivity.setTitle("",2);
        initView();
        initData();
    }

    public static NewsDetailFragment newInstance(int newsId) {
        //TODO(ZJD):添加返回操作
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putInt("NewsId", newsId);
        fragment.setArguments(args);
        return fragment;
    }

}
