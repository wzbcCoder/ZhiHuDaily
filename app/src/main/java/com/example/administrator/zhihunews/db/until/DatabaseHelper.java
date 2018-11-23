package com.example.administrator.zhihunews.db.until;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/11/23.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, NewsItem.DATABASE_NAME, null, NewsItem.DATABASE_VERSION);
    }
    // 新闻评论sql
    final  String CREATE_NEWS_ITEM_SQL="create table "+NewsItem.TABLE_NAME+"("+
            NewsItem.ID+" integer primary key,"+
            NewsItem.IMAGE+" varchar(200)," +
            NewsItem.GAPREFIX +" integer,"+
            NewsItem.TITLE+" varchar(150)," +
            NewsItem.TYPE+"  integer)";
    //  新闻细节sql
    final  String CREATE_DETAIL_ITEM_SQL="";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS_ITEM_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
