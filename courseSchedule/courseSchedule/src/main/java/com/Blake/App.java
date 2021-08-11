package com.Blake;

import com.Blake.dto.classSchedule;

public class App {
    public static void main(String[] args){
        classSchedule classSchedule=new classSchedule(2021,9,1,"/Users/blake/IdeaProjects/CourseSchedule-System/courseSchedule/courseSchedule/src/main/resources/副本1、8.31-9.30市厅级干部进修班 (第 44 期）习近平新时代中国特色社会主义思想专题)教学计划.txt");
        try {
            classSchedule.printSchedule(classSchedule.getDays());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
