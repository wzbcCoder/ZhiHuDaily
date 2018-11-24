package com.example.administrator.zhihunews;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.zhihunews.db.until.DatabaseHelper;
import com.example.administrator.zhihunews.db.until.DbManger;
import com.example.administrator.zhihunews.fragment.NewsListFragment;
import com.example.administrator.zhihunews.fragment.ViewPageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.data;
import static android.support.v4.view.ViewPager.*;

public class MainActivity extends AppCompatActivity {
    FragmentManager mFragmentManger;
    // Fragment 事务
    FragmentTransaction mFragmentTransaction;
    DatabaseHelper mMySqliteHelper;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_layout);
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.tab_listview_item,
                new String[]{"img", "title", "body"},
                new int[]{R.id.itemimg, R.id.itemtitle, R.id.itembody});
        ListView listView = (ListView) findViewById(R.id.tab_listview);
        listView.setAdapter(adapter);

    }

    private List<? extends Map<String, ?>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    
//         setContentView(R.layout.activity_main);
//         prepareDatabase();
//         prepareFragment();
//     }

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

    // 创建Fragment之前的操作
//     private void prepareFragment(){
//         // 通用的FragmentManager
//         mFragmentManger = getSupportFragmentManager();
//         mFragmentTransaction  = getFragmentManager().beginTransaction();
//         NewsListFragment newsListFragment = NewsListFragment.newInstance();
//         mFragmentTransaction.replace(R.id.NewsListFragment,newsListFragment);
//         // 提交
//         mFragmentTransaction.commit();


//将需要的值传入map中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "软院最新公告事项");
        map.put("body", "不知道未来几天有什么最新消息？那就点我查看查看呗");
        map.put("img", R.drawable.zzf2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "校内最新消息通知");
        map.put("body", "校级活动，化工电影本周放啥？艺设妹子有什么动向？点我查看");
        map.put("img", R.drawable.zzf3);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "圈内交流园地");
        map.put("body", "来都来了，何不进来说几句？");
        map.put("img", R.drawable.zzf4);
        list.add(map);

        return list;
    }

}
