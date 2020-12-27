package com.example.spider;

import android.widget.ListView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
本学期需要上的课
 */

public class courses {


    private static Map<String, String> cookies;

    public courses(Map cookies) throws IOException {
        this.cookies = cookies;
    }

    public List<Com> getData() throws IOException {
        String url17 = "http://kdjw.hnust.edu.cn/jsxsd/framework/main_index_loadwdkc.jsp?xnxq01id=2020-2021-1";
        Connection con17 = Jsoup.connect(url17);
        Connection.Response res17 = con17.followRedirects(false)
                .method(Connection.Method.POST)
                .header("ContentType", "application/x-www-form-urlencoded")
                .cookies(cookies)
                .execute();

        System.out.println(Jsoup.parse(res17.body()));
        List<Element> g = Jsoup.parse(res17.body()).select("body > ul > li > ul > li");

//        String cssSelector = "#tablecs > tbody > tr";
//        :nth-child(" + "2" + ") > td:nth-child("+"2"+")
//        System.out.println(Jsoup.parse(res17.body()));
//        List<Element> g1 = Jsoup.parse(res17.body()).select(cssSelector);
//        for (Element e : g1){
//            System.out.println(e.text());
//        }
//        System.out.println("vyuuuuuuuuuuuuuuuuuuuu" + g1.size());

//        #tablecs > tbody > tr:nth-child(1) > td:nth-child(2)

        List<Com> Lc = new ArrayList<>();

        for (Element e : g) {
            Lc.add(new Com(spl(e.text())));
        }


        System.out.println("bvvvvvvvvvvvvvvvvvvvv" + Lc.size());
        for (Com e : Lc) {
            System.out.println(e.info);
        }
        return Lc;
    }


    public String spl(String str) {
        StringBuffer s = new StringBuffer(str);
        for (int index = 0; index < s.length(); index++) {
            if (index % 32 == 0) {
                s.insert(index, "\n");
            }
        }
        return s.toString();
    }
}
