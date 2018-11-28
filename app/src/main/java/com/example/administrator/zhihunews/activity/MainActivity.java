package com.example.administrator.zhihunews.activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
//import com.example.administrator.zhihunews.db.until.DatabaseHelper;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.db.until.MyDatabaseHelper;
import com.example.administrator.zhihunews.fragment.BaseFragment;
import com.example.administrator.zhihunews.fragment.NewsListFragment;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends BaseActivity {
    FragmentManager mFragmentManger;
    // Fragment 事务
    FragmentTransaction mFragmentTransaction;
    MyDatabaseHelper mMySqliteHelper;
    private SQLiteDatabase db;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareFragment();
        initView();
    }

    private void initView() {
        setTitle("首页",1);//设置标题内容，该方法继承自父类，所以再写一次
    }


    // 创建Fragment之前的操作
     private void prepareFragment() {
         // 通用的FragmentManager
         mFragmentManger = getSupportFragmentManager();
         mFragmentTransaction = getFragmentManager().beginTransaction();
         NewsListFragment newsListFragment = NewsListFragment.newInstance();
//         TabFragment tabFragment = new TabFragment();
         mFragmentTransaction.replace(R.id.NewsListFragment, newsListFragment);
         // 提交
         mFragmentTransaction.commit();
     }


//     private void prepareDatabase() {
//         mMySqliteHelper = DbManger.getIntance(MainActivity.this);
//         db = mMySqliteHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("ga_prefix",112309);
//        values.put("id",9702141);
//        values.put("image","https://pic3.zhimg.com/v2-8350fa5a9aadeb091d239ea1ecb9399a.jpg");
//        values.put("title","什么样的报表真难看？拿这种好看的比一下恍然大悟");
//        values.put("type",0);
//        long rowid = db.insert(NewsItem.TABLE_NAME,null,values);
//        System.out.println("ddd"+rowid);







}
