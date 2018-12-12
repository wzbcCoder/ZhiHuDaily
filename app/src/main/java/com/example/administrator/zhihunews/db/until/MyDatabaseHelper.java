package com.example.administrator.zhihunews.db.until;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.administrator.zhihunews.db.model.NewsItem;

/**
 * Created by Administrator on 2018/11/23.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // 使用单例模式 保证项目运行的时候是一个唯一对象
    private  static MyDatabaseHelper mInstance = null;

    public synchronized static MyDatabaseHelper getInstance(Context context){
        if (mInstance == null){
            return  new MyDatabaseHelper(context);
        }
        else{
            return mInstance;
        }
    }
    public MyDatabaseHelper(Context context) {
        super(context, NewsItemTable.DATABASE_NAME, null, NewsItemTable.DATABASE_VERSION);
    }
    // 新闻评论sql
    final  String CREATE_NEWS_ITEM_SQL="create table "+NewsItemTable.TABLE_NAME+"("+
            NewsItemTable.ID+" integer primary key,"+
            NewsItemTable.IMAGE+" varchar(200)," +
            NewsItemTable.GAPREFIX +" integer,"+
            NewsItemTable.TITLE+" varchar(150)," +
            NewsItemTable.TYPE+"  integer)" ;
//            NewsItemTable.DATE+"  varchar(20))";
    //  新闻细节sql
    final  String CREATE_DETAIL_ITEM_SQL="";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS_ITEM_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "ALTER TABLE " + NewsItemTable.TABLE_NAME + " ADD "+NewsItemTable.DATE+" VARCHAR(20) NULL";
        Log.i("sql", sql);
        db.execSQL(sql);
    }
}
