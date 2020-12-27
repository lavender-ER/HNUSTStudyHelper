package com.example.spider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class getcourseInfo {


    private Map<String, String> cookies;

    public getcourseInfo(Map cookies) {
        this.cookies = cookies;
    }


    public List<courseInfo> getData(String week,String it) throws IOException {

        String url7 = "http://kdjw.hnust.edu.cn/jsxsd/xskb/xskb_list.do";
        Connection con7 = Jsoup.connect(url7);
        List<String> Ls = new ArrayList<>();
        for (int i = 2016; i <= 2022; i++) {
            for (int j = 1; j <= 2; j++) {
                Ls.add(i + "-" + (i + 1) + "-" + j);
            }
        }
        int[] item = new int[25];
        for (int i = 0; i < 25; i++) {
            item[i] = i;
        }
        System.out.println(item[6] + " " + Ls.get(6));
        Connection.Response res7 = con7.followRedirects(false)
                .method(Connection.Method.GET)
                .header("ContentType", "application/x-www-form-urlencoded")
                .cookies(cookies)
                .data("zc", week)
                .data("xnxq01id", it)
                .execute();
//                System.out.println(res7.statusCode());

        //                System.out.println(cookies);
        Document doc = Jsoup.parse(res7.body());
        //                System.out.println(doc.select("#timetable > tbody > tr > td > div"));
        List<Element> d = doc.select("#timetable > tbody > tr > td > div");
        List<courseInfo> L = new ArrayList<>();
        int flag = 0;
        int index = 0;
        int x = 1, y = 1;
        for (int i = 0; i < d.size(); i++) {
            L.add(new courseInfo(index / 7 + 1, index % 7 + 1, spl(d.get(i).text()), spl(d.get(i + 1).text())));
            i += 2;
            index++;
        }
        for (courseInfo ci : L) {
            System.out.println(ci.x + " " + ci.y + " " + ci.info1 + " " + ci.info2);
        }
        return L;
    }
    public String spl(String str) {
        StringBuffer s = new StringBuffer(str);
        for (int index = 0; index < s.length(); index++) {
            if (index % 5 == 0) {
                s.insert(index, "\n");
            }
        }
        return s.toString();
    }
}
