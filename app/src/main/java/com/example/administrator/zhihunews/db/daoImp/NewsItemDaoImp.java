package com.example.administrator.zhihunews.db.daoImp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.SoundEffectConstants;

import com.example.administrator.zhihunews.db.dao.NewsItemDao;
import com.example.administrator.zhihunews.db.model.NewsItem;
import com.example.administrator.zhihunews.db.until.MyDatabaseHelper;
import com.example.administrator.zhihunews.db.until.NewsItemTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2018/11/23.
 */

public class NewsItemDaoImp implements NewsItemDao {
    MyDatabaseHelper helper = null;

    public NewsItemDaoImp(Context context) {
        helper = new MyDatabaseHelper(context);

    }

    @Override
    public void add(NewsItem newsItem) {

    }

    @Override
    public void addDay(String newsItemDate) {
        JSONObject jsonObject;

        SQLiteDatabase db = helper.getWritableDatabase();
        helper.onUpgrade(db,1,1);
        try {

            jsonObject = new JSONObject(newsItemDate);
            // 获取stories json 列表
            String stories = jsonObject.get("stories").toString();
            // 获取date
            String date = jsonObject.get("date").toString();

            JSONArray jsonStories = new JSONArray(stories);
            for (int i = 0; i < jsonStories.length(); i++) {
                JSONObject story = (JSONObject) jsonStories.get(i);
                String ga_prefix = story.get(NewsItemTable.GAPREFIX).toString();
                String id = story.get(NewsItemTable.ID).toString();
                JSONArray images = (JSONArray) story.get("images");
                String image = (String) images.get(0);
                String title = story.get(NewsItemTable.TITLE).toString();
                String type = story.get(NewsItemTable.TYPE).toString();
                ContentValues value = new ContentValues();
                value.put(NewsItemTable.ID, id);
                value.put(NewsItemTable.GAPREFIX, ga_prefix);
                value.put(NewsItemTable.IMAGE, image);
                value.put(NewsItemTable.TITLE, title);
                value.put(NewsItemTable.TYPE, type);
                value.put(NewsItemTable.DATE,date);
                // 插入重复数据就更新，不是重复数据就添加
                db.insertWithOnConflict(NewsItemTable.TABLE_NAME, null, value, SQLiteDatabase.CONFLICT_REPLACE);
            }
        } catch (Exception e) {
            Log.d("zjd", "parse error:" + e.getMessage());
        }
        db.close();

    }

    @Override
    public List<NewsItem> findDate(Date date) {
        //TODO:zjd 编写findDate
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String stringDate = sdf.format(date);
        SQLiteDatabase db = helper.getWritableDatabase();
//        db.query(NewsItemTable.TABLE_NAME,NewsItemTable.)
        return null;


    }
}
