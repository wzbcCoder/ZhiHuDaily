package com.example.administrator.zhihunews.db.until;

import android.content.Context;

/**

 * 使用三层数据库操作此管理类弃用
 *  */
public class DbManger {

    private static MyDatabaseHelper mDatabaseHelper;

    public static MyDatabaseHelper getIntance(Context context){
        if(mDatabaseHelper == null){
            mDatabaseHelper = new MyDatabaseHelper(context);
        }
        return mDatabaseHelper;
    }
}
