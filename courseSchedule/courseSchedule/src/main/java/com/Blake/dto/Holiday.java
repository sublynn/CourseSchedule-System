package com.Blake.dto;

import java.util.ArrayList;
import java.util.Map;

public class Holiday {
    private ArrayList<String> holidays;
    private ArrayList<String> restDays;
    public Holiday() {
        this.holidays=new ArrayList<>();
        this.holidays.add("2021-09-19");
        this.holidays.add("2021-09-20");
        this.holidays.add("2021-09-21");
        this.holidays.add("2021-10-01");
        this.holidays.add("2021-10-02");
        this.holidays.add("2021-10-03");
        this.holidays.add("2021-10-04");
        this.holidays.add("2021-10-05");
        this.holidays.add("2021-10-06");
        this.holidays.add("2021-10-07");
        this.restDays=new ArrayList<>();
        restDays.add("2021-09-18");
        restDays.add("2021-09-26");
        restDays.add("2021-10-09");
    }

    public boolean isHoliday(String s){
        return this.holidays.contains(s);
    }
    public boolean isRestDay(String s){
        return this.restDays.contains(s);
    }
}
//9.18上周二的课
//9月26日（星期日）、10月9日（星期六）上班。
