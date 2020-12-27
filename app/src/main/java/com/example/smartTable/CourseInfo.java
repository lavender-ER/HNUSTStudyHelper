package com.example.smartTable;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "")
public class CourseInfo {

    @SmartColumn(id = 0, name = "周一", autoMerge = true)
    private String Monday;
    @SmartColumn(id = 1, name = "周二")
    private String Tuesday;
    @SmartColumn(id = 2, name = "周三")
    private String Wednesday;
    @SmartColumn(id = 3, name = "周四")
    private String Thursday;
    @SmartColumn(id = 4, name = "周五")
    private String Friday;
    @SmartColumn(id = 5, name = "周六")
    private String Saturday;
    @SmartColumn(id = 6, name = "周日")
    private String Sunday;


    public CourseInfo(String Monday, String Tuesday, String Wednesday, String Thursday, String Friday, String Saturday, String Sunday) {
        this.Monday = Monday;
        this.Tuesday = Tuesday;
        this.Wednesday = Wednesday;
        this.Thursday = Thursday;
        this.Friday = Friday;
        this.Saturday = Saturday;
        this.Sunday = Sunday;
    }

}
