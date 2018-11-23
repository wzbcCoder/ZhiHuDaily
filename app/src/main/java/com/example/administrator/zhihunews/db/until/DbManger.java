package com.example.administrator.zhihunews.db.until;

import android.content.Context;

/**

 * 主要是对数据库操作的工具栏
 */
public class DbManger {

    private static DatabaseHelper mDatabaseHelper;

    public static DatabaseHelper getIntance(Context context){
        if(mDatabaseHelper == null){
            mDatabaseHelper = new DatabaseHelper(context);
        }
        return mDatabaseHelper;
    }
}
