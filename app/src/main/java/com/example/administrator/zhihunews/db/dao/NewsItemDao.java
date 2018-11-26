package com.example.administrator.zhihunews.db.dao;

import com.example.administrator.zhihunews.db.model.NewsItem;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/11/23.
 */

public interface NewsItemDao {
    // 添加接口
    public void add(NewsItem newsItem);
    // 添加时间
    public List<NewsItem> findDate(Date date);
    // 添加某天的数据
    public void addDay(String newsItemDate);
}
