package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Fragment;

import com.example.spider.CourseGrade;
import com.example.spider.Exam;
import com.example.spider.TestRes;
import com.example.spider.cets;
import com.example.spider.examarange;
import com.example.spider.grades;
import com.example.spider.login;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import hlq.tablbeview.OnTableClick;


public class fg_content2 extends Fragment {
    private String[] names = {"1", "2", "3", "4", "5"};
    private ListView mListView;
    private String content;
    private int str;
    private String[] mlistHead = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};//声明表格表头
    private String[] mlistContent = {"1", "黄林晴\n", "20\n14\n211\n61\n7", "男", "男", "男", "男"};//对应内容
    private TextView tx1;
    private TextView tx2;
    private String username;
    private String password;
    private SharedPreferences sp;
    private Map<String ,String> cookies;

    public void set_cookies(Map<String ,String > cookies){
        this.cookies = new HashMap<String ,String >();
        this.cookies = cookies;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        View view;
        view = inflater.inflate(R.layout.fg_content2, container, false);
        Log.v("getActivity()", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
//                TextView txt_content2 = (TextView) view.findViewById(R.id.txt_content);
//                txt_content2.setText("2y");
//        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < names.length; i++) {
//            Map map = new HashMap<String, Object>();
//            map.put("name", i);
//            dataList.add(map);
//        }
//        Log.v("getActivity()","hhhhhhhh");
//        mListView = (ListView) view.findViewById(R.id.lv);
//        SimpleAdapter sa = new SimpleAdapter(getActivity(), dataList, R.layout.list_item, new String[]{"name"},new int[R.id.lv]);
//        mListView.setAdapter(sa);
//        Log.v("getActivity()","hhhhhhhh");


//        tx1 = view.findViewById(R.id.infom1);
//        tx2 = view.findViewById(R.id.infom2);

        List<TestRes> LTE = new ArrayList<>();
        List<CourseGrade> LCG = new ArrayList<>();
        List<Exam> LEX = new ArrayList<>();
        try {
            cets ct = new cets(cookies);
            LTE = ct.getData();
            grades ge = new grades(cookies);
            LCG = ge.getData("2019-2020-2");
            examarange er = new examarange(cookies);
            LEX = er.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<Map<String, Object>> listitem1 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> listitem2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> listitem3 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < LCG.size(); i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();

            showitem.put("name", LCG.get(i).name);
            showitem.put("grade", LCG.get(i).grade);
            showitem.put("attribute", LCG.get(i).attribute);
            showitem.put("score", LCG.get(i).score);
            showitem.put("point", LCG.get(i).point);
            listitem2.add(showitem);

        }
        for (int i = 0; i < LTE.size(); i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("id", LTE.get(i).id);
            showitem.put("name", LTE.get(i).name);
            showitem.put("total", LTE.get(i).ScoreTotal);
            showitem.put("date", LTE.get(i).date);
            listitem1.add(showitem);
        }
        for (int i = 0; i < LEX.size(); i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("id", LEX.get(i).id);
            showitem.put("room", LEX.get(i).room);
            showitem.put("time", LEX.get(i).time);
            showitem.put("name", LEX.get(i).name);
            System.out.println(showitem);
            listitem3.add(showitem);
        }


        System.out.println("ctyccccccccccccccccc" + LEX.size());

        SimpleAdapter myAr = new SimpleAdapter(getActivity(), listitem3, R.layout.list_item_exam, new String[]{"id", "name", "room", "time"}, new int[]{R.id.exam_id, R.id.exam_name, R.id.exam_room, R.id.exam_time});


        ListView listView_exam = (ListView) view.findViewById(R.id.lv_arrange);

        listView_exam.setAdapter(myAr);


        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getActivity(), listitem1, R.layout.list_item, new String[]{"id", "name", "total", "date"}, new int[]{R.id.item_tv_id, R.id.item_tv_name, R.id.item_tv_score_total, R.id.item_tv_date});


        ListView listView = (ListView) view.findViewById(R.id.lv);

        listView.setAdapter(myAdapter);


        SimpleAdapter adapter = new SimpleAdapter(getActivity(), listitem2, R.layout.list_item_grade, new String[]{"name", "grade", "attribute",
                "score", "point"}, new int[]{R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5});
        ListView ls = (ListView) view.findViewById(R.id.grade);
        ls.setAdapter(adapter);
        return view;
    }
}
