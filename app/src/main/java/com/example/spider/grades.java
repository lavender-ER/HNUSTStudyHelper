package com.example.spider;

import android.widget.ListView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class grades {

    private static Map<String, String> cookies;


    public grades(Map<String, String> cookies) {
        grades.cookies = cookies;
    }

    public List<CourseGrade> getData(String item) throws IOException {
        List<String> Ls = new ArrayList<>();
        for (int i = 2016; i <= 2022; i++) {
            for (int j = 1; j <= 2; j++) {
                Ls.add(i + "-" + (i + 1) + "-" + j);
            }
        }
        System.out.println("cfyuyvibniioyuxcvbn" + item);
        String url8 = "http://kdjw.hnust.edu.cn/jsxsd/kscj/cjcx_list?=";
        Connection con8 = Jsoup.connect(url8);
        Connection.Response res8 = con8.followRedirects(false)
                .method(Connection.Method.GET)
                .header("ContentType", "application/x-www-form-urlencoded")
                .cookies(cookies)
                .data("kksj", item)
                .execute();
        List<CourseGrade> Lg = new ArrayList<>();
        System.out.println(res8.statusCode());
        System.out.println(Jsoup.parse(res8.body()).select("#dataList > tbody > tr > td"));
        List<Element> d = Jsoup.parse(res8.body()).select("#dataList > tbody > tr > td");
        for (int i = 0; i < d.size(); i++) {
            Lg.add(new CourseGrade(Integer.parseInt(d.get(i).text()), d.get(i + 1).text(), Integer.parseInt(d.get(i + 2).text()), d.get(i + 3).text(),
                    d.get(i + 4).text(), d.get(i + 5).text(), Double.parseDouble(d.get(i + 6).text()), Integer.parseInt(d.get(i + 7).text()),
                    Double.parseDouble(d.get(i + 8).text()), d.get(i + 9).text(), d.get(i + 10).text(), d.get(i + 11).text(), d.get(i + 12).text()));
            i += 13;
        }
        for (CourseGrade cg : Lg) {
            System.out.println(cg.id + " " + cg.name);
        }
        return Lg;
    }

}

