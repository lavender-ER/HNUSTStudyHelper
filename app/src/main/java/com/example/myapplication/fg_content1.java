package com.example.myapplication;


import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;

import androidx.annotation.Nullable;

import com.example.spider.Com;
import com.example.spider.TestRes;
import com.example.spider.cets;
import com.example.spider.courseInfo;
import com.example.spider.courses;
import com.example.spider.getcourseInfo;
import com.example.spider.login;


public class fg_content1 extends Fragment {
    private String[] names = {"1", "2", "3", "4", "5"};
    private ListView mListView;
    private String content;
    private int str;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private FragmentManager fManager;
    private String username;
    private String password;
    private FragmentManager manager;
    private FragmentTransaction ft;

    private Map<String, String> cookies;

    public void set_cookies(Map<String, String> cookies) {
        this.cookies = new HashMap<String, String>();
        this.cookies = cookies;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        manager = getFragmentManager();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        View view;
        view = inflater.inflate(R.layout.fg_content1, container, false);

        TextView btn4 = (TextView) view.findViewById(R.id.tv4);
        TextView btn3 = (TextView) view.findViewById(R.id.tv3);
        TextView btn2 = (TextView) view.findViewById(R.id.tv2);
        TextView btn1 = (TextView) view.findViewById(R.id.tv1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("我giao");
//                Intent intent = new Intent(getActivity(),Login.class);
//                intent.putExtra("out","true");
//                startActivity(intent);
                fg_content3 fg_3 = new fg_content3();
                fg_3.set_cookies(cookies);
                ft = manager.beginTransaction();
                ft.add(R.id.ly_content, fg_3);
                ft.commit();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                intent.putExtra("out", "true");
                startActivity(intent);
            }
        });
//        String username ="1805020205";
//        String pwd = "L123456789";
//        login li = new login(username, pwd);

        List<Com> LC = new ArrayList<>();
        try {
//            Map cookies = li.getCookies();

            courses cr = new courses(cookies);
            LC = cr.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < LC.size(); i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("info", LC.get(i).info);
            System.out.println(LC.get(i).info);
            listitem.add(showitem);
        }


        SimpleAdapter myAdapter = new SimpleAdapter(getActivity(), listitem, R.layout.list_item_info, new String[]{"info"}, new int[]{R.id.item_tv_info});
        ListView listView = (ListView) view.findViewById(R.id.lv_con1);

        Log.v("getActivity()", "vvvvvvvv");
        listView.setAdapter(myAdapter);

        return view;
    }
}

