package com.example.spider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
等级考试
 */
public class cets {

    private static Map <String ,String > cookies;

    public cets(Map cookies){
        this.cookies = cookies;
    }


    public List<TestRes> getData() throws IOException {
        String url9 = "http://kdjw.hnust.edu.cn/jsxsd/kscj/djkscj_list";
        Connection con9 = Jsoup.connect(url9);
        Connection.Response res9 = con9.followRedirects(false)
                .method(Connection.Method.GET)
                .header("ContentType", "application/x-www-form-urlencoded")
                .cookies(this.cookies)
                .execute();
        List<TestRes> TR = new ArrayList<>();
//        System.out.println(res9.statusCode());
//        System.out.println(Jsoup.parse(res9.body()).select("#dataList > tbody > tr > td"));
        List<Element> d = Jsoup.parse(res9.body()).select("#dataList > tbody > tr > td");
        for (int i = 0; i < d.size(); i++) {
            System.out.println(Integer.parseInt(d.get(i).text()) + " " + i);
            TR.add(new TestRes(d.get(i).text(), d.get(i + 1).text().substring(d.get(i + 1).text().length()-5,d.get(i + 1).text().length()-1), d.get(i + 2).text(), d.get(i + 3).text(),
                    d.get(i + 4).text(), d.get(i + 5).text(), d.get(i + 6).text(), d.get(i + 7).text(), d.get(i + 8).text()));
            i += 8;
//            System.out.println(d.get(i).text() + " " + i);

        }
        for (TestRes cg : TR) {
//            System.out.println(cg.id + " " + cg.name + " " + cg.ScoreWritten + " " + cg.ScorePC + " " + cg.ScoreTotal + " " + cg.LevelWritten + " " + cg.LevelPC + " " + cg.LevelTotal + " " + cg.date);
        }
        return TR;
    }
}
