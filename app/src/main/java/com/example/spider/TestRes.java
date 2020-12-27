package com.example.spider;
/*
考试安排
 */
public class TestRes {
    public String id;
    public String name;
    public String ScoreWritten;
    public String ScorePC;
    public String ScoreTotal;
    public String LevelWritten;
    public String LevelPC;
    public String LevelTotal;
    public String date;

    TestRes(String id, String name, String ScoreWritten, String ScorePC, String ScoreTotal,
            String LevelWritten, String LevelPC, String LevelTotal, String date) {
        this.id = id;
        this.date = date;
        this.LevelPC = LevelPC;
        this.ScorePC = ScorePC;
        this.LevelTotal = LevelTotal;
        this.ScoreTotal = ScoreTotal;
        this.LevelWritten = LevelWritten;
        this.ScoreWritten = ScoreWritten;
        this.name = name;
    }
}