package com.example.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.widget.TextView;

import com.bin.david.form.data.style.FontStyle;
import com.example.smartTable.CourseInfo;
import com.example.smartTable.UserInfo;
import com.example.spider.CourseGrade;
import com.example.spider.courseInfo;
import com.example.spider.getcourseInfo;
import com.example.spider.grades;
import com.example.spider.login;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;


public class fg_content3 extends Fragment {

    private View view;
    private String info;
    private TextView v1;
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
        view = inflater.inflate(R.layout.fg_content3, container, false);


        v1 = view.findViewById(R.id.infom3);

        NiceSpinner niceSpinner1 = (NiceSpinner) view.findViewById(R.id.nice_spinner3);
        List<String> dataset1 = new LinkedList<>(Arrays.asList("2018-2019-1","2018-2019-2","2019-2020-1", "2019-2020-2"));
        niceSpinner1.setTextSize(13);
        niceSpinner1.attachDataSource(dataset1);
        niceSpinner1.setTextColor(Color.BLACK);


        niceSpinner1.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                // This example uses String, but your type can be any
                String item = (String) parent.getItemAtPosition(position);
                v1.setText(item);
                System.out.println(item);
            }
        });


//
//        String username = "1805020211";
//        String pwd = "20001212a";



        Button btn = (Button) view.findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info = v1.getText().toString();
//
//                login li = new login(username, pwd);
                List<CourseGrade> LCG = new ArrayList<>();
                try {
//                    Map cookies = li.getCookies();
                    grades ge = new grades(cookies);
                    LCG = ge.getData(info);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("nioniobnik" + LCG.size());
                List<Map<String, Object>> listitem2 = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < LCG.size(); i++) {
                    Map<String, Object> showitem = new HashMap<String, Object>();
                    showitem.put("name", LCG.get(i).name);
                    showitem.put("grade", LCG.get(i).grade);
                    showitem.put("attribute", LCG.get(i).attribute);
                    showitem.put("score", LCG.get(i).score);
                    showitem.put("point", LCG.get(i).point);
                    listitem2.add(showitem);

                }

                SimpleAdapter adapter = new SimpleAdapter(getActivity(), listitem2, R.layout.list_item_grade, new String[]{"name", "grade", "attribute",
                        "score", "point"}, new int[]{R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5});
                ListView ls = (ListView) view.findViewById(R.id.grade1);
                ls.setAdapter(adapter);
            }
        });




//        login li = new login(username, pwd);
        List<CourseGrade> LCG = new ArrayList<>();
        try {
//            Map cookies = li.getCookies();
            grades ge = new grades(cookies);
            LCG = ge.getData("2019-2020-2");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("nioniobnik" + LCG.size());
        List<Map<String, Object>> listitem2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < LCG.size(); i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("name", LCG.get(i).name);
            showitem.put("grade", LCG.get(i).grade);
            showitem.put("attribute", LCG.get(i).attribute);
            showitem.put("score", LCG.get(i).score);
            showitem.put("point", LCG.get(i).point);
            listitem2.add(showitem);

        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), listitem2, R.layout.list_item_grade, new String[]{"name", "grade", "attribute",
                "score", "point"}, new int[]{R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5});
        ListView ls = (ListView) view.findViewById(R.id.grade1);
        ls.setAdapter(adapter);

        return view;
    }
}

