package com.example.spider;


/*
考试安排
 */
public class Exam {
    public int id;
    public String place;
    public String examId;
    public String CourseId;
    public String name;
    public String teacher;
    public String time;
    public String room;

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