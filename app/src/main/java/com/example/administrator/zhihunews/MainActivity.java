package com.example.administrator.zhihunews;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import com.example.administrator.zhihunews.db.until.DatabaseHelper;
import com.example.administrator.zhihunews.db.until.MyDatabaseHelper;
import com.example.administrator.zhihunews.fragment.NewsListFragment;


public class MainActivity extends AppCompatActivity {
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
