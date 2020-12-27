package com.example.spider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Connection.Response;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class fat extends IOException {

//    public static String LOGIN_URL = "http://kdjw.hnust.edu.cn/";
//    public static String USER_AGENT = "User-Agent";
//    public static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36";
//
//    public static void main(String[] args) {
//        String user, password;
//        Scanner cin = new Scanner(System.in);
//        user = cin.next();
//        password = cin.next();
//        login(user, password);
//    }
//
//    public static Map<String, String> cookies = new HashMap<>();
//
//    //获得dataString
//    public static String get_dataString(Connection.Response response) throws IOException {
//
//        String s = response.header("Set-Cookie"); //获得Set-Cookie
//        String[] array = s.split(";| |,|=|/");
//        System.out.println(s);
//        String JSESSIONID = "", SERVERID = "";
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//            if (array[i].equals("SERVERID")) {
//                SERVERID = array[i + 1];
//                i++;
//            }
//            if (array[i].equals("JSESSIONID")) {
//                JSESSIONID = array[i + 1];
//                i++;
//            }
//        }
//        //存入新的cookies中
//        cookies.put("JSESSIONID", JSESSIONID);
//        cookies.put("SERVERID", SERVERID);
//        System.out.println(cookies);
//
//        Connection connection = Jsoup.connect("http://kdjw.hnust.edu.cn/Logon.do?method=logon&flag=sess");
//        connection.header(USER_AGENT, USER_AGENT_VALUE);
//        Connection.Response rs = connection.cookies(cookies).execute();
//        Document doc = Jsoup.parse(rs.body());
//        System.out.println(doc.select("body").text());
//        return doc.select("body").text();
//    }
//
//    public static String get_encoded(String dataString, String user, String password) {
//        //dataString="Ifmld8647k4JQgal5v9a628ttDx3YC85063V07#33132111112222322321";
//        //user="1805020213";
//        // password="123456";
//        String scode = dataString.split("#")[0];
//        String sxh = dataString.split("#")[1];
//        String code = user + "%%%" + password;
//        String encoded = "";
//        for (int i = 0; i < code.length(); i++) {
//            if (i < 20) {
//                String ss = sxh.substring(i, i + 1);
//                int s = Integer.parseInt(ss);
//                encoded = encoded + code.substring(i, i + 1) + scode.substring(0, s);
//                scode = scode.substring(s, scode.length());
//            } else {
//                encoded = encoded + code.substring(i, code.length());
//                i = code.length();
//            }
//            //System.out.println(i+" "+encoded);
//        }
//        return encoded;
//    }

//    public static void login(String user, String password) {
//        try {
//            /*
//             * 第一次请求
//             * grab login form page first
//             * 获取登陆提交的表单信息，及修改其提交data数据（login，password）
//             */
//            Connection connection1 = Jsoup.connect(LOGIN_URL);
//
//            //print(LOGIN_URL);
//            connection1.header(USER_AGENT, USER_AGENT_VALUE);   // 配置模拟浏览器
//            Connection.Response rs = connection1.execute();     // 获取响应
//
//            String dataString = get_dataString(rs);
//            String encoded = get_encoded(dataString, user, password);
//
//            Document d1 = Jsoup.parse(rs.body());       // 通过Jsoup将返回信息转换为Dom树
//            List<Element> eleList = d1.select("form");  // 获取提交form表单，可以通过查看页面源码代码得知
//            // 获取cooking和表单属性
//            // lets make data map containing all the parameters and its values found in the form
//            Map<String, String> datas = new HashMap<>();
//            for (int i = 0; i < eleList.size(); i++) {
//                for (Element e : eleList.get(i).getAllElements()) {
//                    // 设置用户名
//                    if (e.attr("name").equals("userAccount")) {
//                        e.attr("value", user);
//                    }
//                    // 设置用户密码
//                    if (e.attr("name").equals("userPassword")) {
//                        e.attr("value", "");
//                    }
//                    if (e.attr("name").equals("encoded")) {
//                        e.attr("value", encoded);
//                    }
//                    // 排除空值表单属性
//                    if (e.attr("name").length() > 0 && !e.attr("name").equals("loginForm")) {
//                        datas.put(e.attr("name"), e.attr("value"));
//                    }
//                }
//            }
//
//            /*
//             * 第二次请求，以post方式提交表单数据以及cookie信息
//             */
//            Connection con2 = Jsoup.connect("http://kdjw.hnust.edu.cn/Logon.do?method=logon");
//            con2.header(USER_AGENT, USER_AGENT_VALUE);
//            System.out.println(cookies);
//            // 设置cookie和post上面的map数据
//            Connection.Response login = con2.followRedirects(false)
//                    .method(Connection.Method.POST)
//                    .data(datas).cookies(cookies)
//                    .header("ContentType", "application/x-www-form-urlencoded")
//                    .execute();
//            System.out.println(login.body());
//            System.out.println(login.cookies());
//            String location = login.header("Location");//空
//            System.out.println(location);
//            Connection connection3 = Jsoup.connect(location);
//            Connection.Response cdx = connection3.followRedirects(false)
//                    .method(Connection.Method.GET)
//                    .header("ContentType", "application/x-www-form-urlencoded")
//                    .cookies(cookies)
//                    .execute();
////            Response cdx = connection3.ignoreContentType(true)
////                    .header("ContentType", "application/x-www-form-urlencoded")
////                    .followRedirects(false).method(Connection.Method.POST)
////                    .data(datas)
////                    .cookies(cookies).execute();
////            System.out.println(cookies);
////            System.out.println(cdx.body());
////            System.out.println(cdx.headers());
//            // 打印，登陆成功后的信息
//            if (cdx.statusCode() != 302)
//                System.out.println("登陆失败");
//            else {
//                System.out.println(cdx.cookies());
//                // 登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
//                Map<String, String> map = cdx.cookies();
//                for (String s : map.keySet()) {
//                    System.out.println(s + " : " + map.get(s));
//                }
//                String url7 = "http://kdjw.hnust.edu.cn/jsxsd/xskb/xskb_list.do";
//                Connection con7 = Jsoup.connect(url7);
//                cdx.cookies().put("SERVERID", cookies.get("SERVERID"));
                /* 获得个人课表
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
                System.out.println(item[6]+" "+ Ls.get(6));
                Connection.Response res7 = con7.followRedirects(false)
                        .method(Connection.Method.GET)
                        .header("ContentType", "application/x-www-form-urlencoded")
                        .cookies(cdx.cookies())
                        .data("zc", String.valueOf(item[6]))
                        .data("xnxq01id", Ls.get(6))
                        .execute();
//                System.out.println(res7.statusCode());

//                System.out.println(cookies);
                Document doc = Jsoup.parse(res7.body());
//                System.out.println(doc.select("#timetable > tbody > tr > td > div"));
                List<Element> d = doc.select("#timetable > tbody > tr > td > div");
                List<CourseInfo> L = new ArrayList<>();
                int flag = 0;
                int index = 0;
                int x = 1, y = 1;
                for (int i = 0; i < d.size(); i++) {
                    L.add(new CourseInfo(index / 7 + 1, index % 7 + 1, d.get(i).text(), d.get(i + 1).text()));
                    i += 2;
                    index++;
                }
                for (CourseInfo ci : L) {
                    System.out.println(ci.x + " " + ci.y + " " + ci.info1 + " " + ci.info2);
                }
                */

                /* 获取专业课成绩
                List<String> Ls = new ArrayList<>();
                for (int i = 2016; i <= 2022; i++) {
                    for (int j = 1; j <= 2; j++) {
                        Ls.add(i + "-" + (i + 1) + "-" + j);
                    }
                }
                String url8 = "http://kdjw.hnust.edu.cn/jsxsd/kscj/cjcx_list?=";
                Connection con8 = Jsoup.connect(url8);
                Connection.Response res8 = con8.followRedirects(false)
                        .method(Connection.Method.GET)
                        .header("ContentType", "application/x-www-form-urlencoded")
                        .cookies(cdx.cookies())
                        .data("kksj", Ls.get(6))
                        .execute();
                List<CourseGrade> Lg = new ArrayList<>();
                System.out.println(res8.statusCode());
                System.out.println(Jsoup.parse(res8.body()).select("#dataList > tbody > tr > td"));
                List<Element> d = Jsoup.parse(res8.body()).select("#dataList > tbody > tr > td");
                for (int i = 0; i < d.size(); i++) {
                    Lg.add(new CourseGrade(Integer.parseInt(d.get(i).text()),d.get(i+1).text(),Integer.parseInt(d.get(i+2).text()),d.get(i+3).text(),
                            d.get(i+4).text(),d.get(i+5).text(),Double.parseDouble(d.get(i+6).text()),Integer.parseInt(d.get(i+7).text()),
                            Double.parseDouble(d.get(i+8).text()),d.get(i+9).text(),d.get(i+10).text(),d.get(i+11).text(),d.get(i+12).text()));
                    i += 13;
                }
                for (CourseGrade cg : Lg){
                    System.out.println(cg.id + " " + cg.name);
                }
                */
                /* 获取等级考试成绩
                String url9 = "http://kdjw.hnust.edu.cn/jsxsd/kscj/djkscj_list";
                Connection con9 = Jsoup.connect(url9);
                Connection.Response res9 = con9.followRedirects(false)
                        .method(Connection.Method.GET)
                        .header("ContentType", "application/x-www-form-urlencoded")
                        .cookies(cdx.cookies())
                        .execute();
                List<TestRes> TR = new ArrayList<>();
                System.out.println(res9.statusCode());
                System.out.println(Jsoup.parse(res9.body()).select("#dataList > tbody > tr > td"));
                List<Element> d = Jsoup.parse(res9.body()).select("#dataList > tbody > tr > td");
                for (int i = 0; i < d.size(); i++) {
                    System.out.println(Integer.parseInt(d.get(i).text()) + " " + i);
                    TR.add(new TestRes(d.get(i).text(), d.get(i + 1).text(), d.get(i + 2).text(), d.get(i + 3).text(),
                            d.get(i + 4).text(), d.get(i + 5).text(), d.get(i + 6).text(), d.get(i + 7).text(), d.get(i + 8).text()));
                    i += 8;
                    System.out.println(d.get(i).text() + " " + i);

                }
                for (TestRes cg : TR) {
                    System.out.println(cg.id + " " + cg.name + " " + cg.ScoreWritten + " " + cg.ScorePC + " " + cg.ScoreTotal + " " + cg.LevelWritten + " " + cg.LevelPC + " " + cg.LevelTotal + " " + cg.date);
                }
                                 */
                /*
                 查询指定课程
                List<String> Ls = new ArrayList<>();
                for (int i = 2016; i <= 2022; i++) {
                    for (int j = 1; j <= 2; j++) {
                        Ls.add(i + "-" + (i + 1) + "-" + j);
                    }
                }
                String item = "2020-2021-1";
                String weekStart = "1";
                String weekEnd = "12";
                String dayStart = "1";
                String dayEnd = "6";
                String classStart = "01";
                String classEnd = "10";
                String courseName = "接入网技术";
                String classDepartmentId = "05";
                String openDepartmentId = "05";
                String classProperty = "1";


                String url10 = "http://kdjw.hnust.edu.cn/jsxsd/kbcx/kbxx_kc_if";
                Connection con10 = Jsoup.connect(url10);
//                Connection con11 = Jsoup.connect(url11);
                Connection.Response res10 = con10.followRedirects(false)
                        .method(Connection.Method.POST)
                        .header("ContentType", "application/x-www-form-urlencoded")
                        .cookies(cdx.cookies())
                        .data("xnxqh", "2020-2021-1")
                        .data("skyx", "05")
                        .data("kbjcmsid", "0B841F8A531A4C05BE8DB7DB4B40AEF1")
                        .data("kcid", "")
                        .data("kcmc", "接入网技术")
                        .data("kkyx", "05")
                        .data("zzdKcSX", "1")
                        .data("zc1", "1")
                        .data("zc2", "18")
                        .data("skxq1", "1")
                        .data("skxq2", "5")
                        .data("jc1", "01")
                        .data("jc2", "10")
                        .execute();

//                Connection.Response res11 = con11.followRedirects(false)
//                        .method(Connection.Method.GET)
//                        .header("ContentType", "application/x-www-form-urlencoded")
//                        .cookies(cdx.cookies()).execute();


//                System.out.println(Jsoup.parse(res10.body()).select("#timetable > tbody > tr").text());
                System.out.println(res10.headers());
                System.out.println(res10.body());

                */
                /*
                获取考试安排
                String url12 = "http://kdjw.hnust.edu.cn/jsxsd/xsks/xsksap_list";
                Connection con12 = Jsoup.connect(url12);
                Connection.Response res12 = con12.followRedirects(false)
                        .method(Connection.Method.POST)
                        .header("ContentType", "application/x-www-form-urlencoded")
                        .cookies(cdx.cookies())
                        .data("xqlbmc","期末")
                        .data("xnxqid","2020-2021-1")
                        .data("xqlb", "3")
                        .execute();
                List<Element>ExamInfo = new ArrayList<>();
                ExamInfo = Jsoup.parse(res12.body()).select("#dataList > tbody > tr > td");
                System.out.println(ExamInfo);
                List<Exam> LE = new ArrayList<>();
                System.out.println(ExamInfo.size());

                for (int i = 0;i < ExamInfo.size();){
                    LE.add(new Exam(Integer.parseInt(ExamInfo.get(i).text()),ExamInfo.get(i+1).text(),ExamInfo.get(i+2).text(),
                            ExamInfo.get(i+3).text(),ExamInfo.get(i+4).text(),ExamInfo.get(i+5).text(),ExamInfo.get(i+6).text(),
                            ExamInfo.get(i+7).text()));
                    i += 12;
                }
                for (Exam cg : LE) {
                    System.out.println(cg.id + " " + cg.name);
                }
                */
                /*
                获取本学期课程


                String url17 = "http://kdjw.hnust.edu.cn/jsxsd/framework/xsMain_new.jsp?t1=1";
                Connection con17 = Jsoup.connect(url17);
                Connection.Response res17 = con17.followRedirects(false)
                        .method(Connection.Method.POST)
                        .header("ContentType", "application/x-www-form-urlencoded")
                        .cookies(cdx.cookies())
                        .execute();

//                System.out.println(Jsoup.parse(res17.body()).select("body > div.personal-center.student-personal-center > div.personal-left-item > div:nth-child(3) > div.examlist > ul > li").text());
                List<Element> g = Jsoup.parse(res17.body()).select("body > div.personal-center.student-personal-center > div.personal-left-item > div:nth-child(3) > div.examlist > ul > li");
                List<Com> Lc = new ArrayList<>();
                for (Element e : g) {
                    Lc.add(new Com(e.text()));
                }
                for (Com e : Lc) {
                    System.out.println(e.info);
                }
                 */
//            }
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }

    /*
1805020205
L123456789
     */


    public static class Exam {
        int id;
        String place;
        String examId;
        String CourseId;
        String name;
        String teacher;
        String time;
        String room;

        Exam(int id, String place, String examId, String CourseId, String name, String teacher, String time, String room) {
            this.id = id;
            this.place = place;
            this.examId = examId;
            this.CourseId = CourseId;
            this.teacher = teacher;
            this.time = time;
            this.room = room;
            this.name = name;
        }
    }

    public static class Com {
        String info;

        public Com(String info) {
            this.info = info;
        }
    }

    public static class SingleCourse {
        String info;
        String id;
        String day;
        String cla;

        public SingleCourse(String info, String id, String day, String cla) {
            this.cla = cla;
            this.day = day;
            this.id = id;
            this.info = info;
        }
    }

//    public static class TestRes {
//        String id;
//        String name;
//        String ScoreWritten;
//        String ScorePC;
//        String ScoreTotal;
//        String LevelWritten;
//        String LevelPC;
//        String LevelTotal;
//        String date;
//
//        TestRes(String id, String name, String ScoreWritten, String ScorePC, String ScoreTotal,
//                String LevelWritten, String LevelPC, String LevelTotal, String date) {
//            this.id = id;
//            this.date = date;
//            this.LevelPC = LevelPC;
//            this.ScorePC = ScorePC;
//            this.LevelTotal = LevelTotal;
//            this.ScoreTotal = ScoreTotal;
//            this.LevelWritten = LevelWritten;
//            this.ScoreWritten = ScoreWritten;
//            this.name = name;
//        }
//    }


//    public static class CourseGrade {
//        int id;
//        String item;
//        int courseId;
//        String name;
//        String grade;
//        String flag;
//        double score;
//        int timeR;
//        double point;
//        String ReItem;
//        String method;
//        String property;
//        String attribute;
//
//        public CourseGrade(int id, String item, int courseId, String name, String grade,
//                           String flag, double score, int timeR, double point, String ReItem,
//                           String method, String property, String attribute) {
//            this.id = id;
//            this.item = item;
//            this.courseId = courseId;
//            this.name = name;
//            this.grade = grade;
//            this.flag = flag;
//            this.score = score;
//            this.timeR = timeR;
//            this.point = point;
//            this.ReItem = ReItem;
//            this.method = method;
//            this.property = property;
//            this.attribute = attribute;
//        }
//    }

//    public static class CourseInfo {
//        int x;
//        int y;
//        String info1;
//        String info2;
//
//        CourseInfo(int x, int y, String info1, String info2) {
//            this.x = x;
//            this.y = y;
//            this.info1 = info1;
//            this.info2 = info2;
//
//        }
//    }
}

