package com.example.administrator.zhihunews.fragment;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.zhihunews.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabFragment extends Fragment {
    private ListView lv;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    private LinearLayout head;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);
        head = (LinearLayout) inflater.inflate(R.layout.lunbo, container, false);
        lv = (ListView) view.findViewById(R.id.tab_listview);	//实例化
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new SimpleAdapter(getActivity(), getData(), R.layout.tab_listview_item,
                new String[]{"img", "title", "body"},
                new int[]{R.id.itemimg, R.id.itemtitle, R.id.itembody});      //配置适配器，并获取对应Item中的ID
        lv.setAdapter(adapter);
    }
    //数据的获取@！
    private List<? extends Map<String, ?>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

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

