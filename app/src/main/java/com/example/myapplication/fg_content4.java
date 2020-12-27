package com.example.myapplication;


import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import android.widget.Toast;
import android.widget.Toolbar;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.draw.IDrawFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.ArrayTableData;
import com.bin.david.form.utils.DensityUtils;
import com.example.smartTable.CourseInfo;
import com.example.smartTable.UserInfo;
import com.example.spider.Com;
import com.example.spider.TestRes;
import com.example.spider.cets;
import com.example.spider.courseInfo;
import com.example.spider.courses;
import com.example.spider.getcourseInfo;
import com.example.spider.login;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;


public class fg_content4 extends Fragment {

    private SmartTable table;
    private String info1;
    private String info2;
    private String username;
    private String password;

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
        view = inflater.inflate(R.layout.fg_content4, container, false);


//        List<UserInfo> list = new ArrayList<>();
        table = view.findViewById(R.id.table);
//        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("乌鲁木齐", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("乌鲁木齐", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("乌鲁木齐", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("乌鲁木齐", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458));
//

//
        EditText edt = view.findViewById(R.id.edHint); // 周
        TextView tx2 = view.findViewById(R.id.infom2); // 学期


//        NiceSpinner niceSpinner = (NiceSpinner) view.findViewById(R.id.nice_spinner);
//        List<String> dataset = new LinkedList<>(Arrays.asList("全部", "第一周", "第二周", "第三周", "第四周"
//                , "第五周", "第四周", "第四周", "第四周", "第四周", "第四周", "第四周"
//                , "第四周", "第四周", "第四周", "第四周", "第四周", "第四周", "第四周", "第四周"));
//        niceSpinner.attachDataSource(dataset);

//        textView.setText(getItem(position).toString());
//        niceSpinner.setTextColor(Color.BLACK);


        NiceSpinner niceSpinner1 = (NiceSpinner) view.findViewById(R.id.nice_spinner1);
        List<String> dataset1 = new LinkedList<>(Arrays.asList("2018-2019-1","2018-2019-2","2019-2020-1", "2019-2020-2", "2020-2021-1"));        niceSpinner1.attachDataSource(dataset1);
        niceSpinner1.setTextColor(Color.BLACK);

//        niceSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
//            @Override
//            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
//                // This example uses String, but your type can be any
//                String item = (String) parent.getItemAtPosition(position);
//                System.out.println(item);
//                tx1.setText(item);
//
//            }
//        });

        niceSpinner1.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                // This example uses String, but your type can be any
                String item = (String) parent.getItemAtPosition(position);
                tx2.setText(item);
                System.out.println(item);
            }
        });


        Button btn = (Button) view.findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info1 = edt.getText().toString();
                info2 = tx2.getText().toString();
                System.out.println(info1);
                System.out.println(info2);

//                login li = new login("1805020205", "L123456789");

                List<courseInfo> LC = new ArrayList<>();
                try {
//                    Map cookies = li.getCookies();

                    getcourseInfo ce = new getcourseInfo(cookies);
                    LC = ce.getData(info1, info2);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                List<UserInfo> list = new ArrayList<>();
                table = view.findViewById(R.id.table);


                List<CourseInfo> list1 = new ArrayList<>();

                for (int i = 0; i < LC.size(); ) {
                    list1.add(new CourseInfo(LC.get(i).info1,
                            LC.get(i + 1).info1,
                            LC.get(i + 2).info1,
                            LC.get(i + 3).info1,
                            LC.get(i + 4).info1,
                            LC.get(i + 5).info1,
                            LC.get(i + 6).info1));
                    i += 7;
                }
                System.out.println(LC.size() + "tttttttttttttttttttttttt");
                for (CourseInfo e : list1) {
                    System.out.println(e);
                }

                table.setData(list1);
                table.getConfig().setContentStyle(new FontStyle(16, Color.BLACK));

            }
        });


        info1 = "6";
        info2 = "2019-2020-1";


//        login li = new login("1805020205", "L123456789");

        List<courseInfo> LC = new ArrayList<>();
        try {
//            Map cookies = li.getCookies();

            getcourseInfo ce = new getcourseInfo(cookies);
            LC = ce.getData(info1, info2);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<UserInfo> list = new ArrayList<>();
        table = view.findViewById(R.id.table);


        List<CourseInfo> list1 = new ArrayList<>();

        for (int i = 0; i < LC.size(); ) {
            list1.add(new CourseInfo(LC.get(i).info1,
                    LC.get(i + 1).info1,
                    LC.get(i + 2).info1,
                    LC.get(i + 3).info1,
                    LC.get(i + 4).info1,
                    LC.get(i + 5).info1,
                    LC.get(i + 6).info1));
            i += 7;
        }
//        System.out.println(LC.size() + "tttttttttttttttttttttttt");
//        for (CourseInfo e : list1) {
//            System.out.println(e);
//        }

        table.setData(list1);
        table.getConfig().setContentStyle(new FontStyle(17, Color.BLACK));
//        table.setZoom(true,1000,500);

        return view;
    }

    public String spl(String str) {
        StringBuffer s = new StringBuffer(str);
        for (int index = 0; index < s.length(); index++) {
            if (index % 7 == 0) {
                s.insert(index, "\n");
            }
        }
        return s.toString();
    }
}
