package com.example.spider;

public  class CourseGrade {
    public int id;
    public String item;
    public int courseId;
    public String name;
    public String grade;
    public String flag;
    public double score;
    public int timeR;
    public double point;
    public String ReItem;
    public String method;
    public String property;
    public String attribute;

    public CourseGrade(int id, String item, int courseId, String name, String grade,
                       String flag, double score, int timeR, double point, String ReItem,
                       String method, String property, String attribute) {
        this.id = id;
        this.item = item;
        this.courseId = courseId;
        this.name = name;
        this.grade = grade;
        this.flag = flag;
        this.score = score;
        this.timeR = timeR;
        this.point = point;
        this.ReItem = ReItem;
        this.method = method;
        this.property = property;
        this.attribute = attribute;
    }
}
