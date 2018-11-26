package com.example.administrator.zhihunews.db.daoImp;

import android.content.Context;

import com.example.administrator.zhihunews.db.dao.NewsItemDao;
import com.example.administrator.zhihunews.db.model.NewsItem;
import com.example.administrator.zhihunews.db.until.DatabaseHelper;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/11/23.
 */

public class NewsItemDaoImp implements NewsItemDao {
    DatabaseHelper helper = null;
    public NewsItemDaoImp(Context context){
        helper = new DatabaseHelper(context);

    }
    @Override
    public void add(NewsItem newsItem) {

    }

    @Override
    public List<NewsItem> findDate(Date date) {
        return null;
    }
}
