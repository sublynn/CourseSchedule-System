package com.Blake.dto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.Blake.Util.DateUtil;
import com.Blake.Util.readfileUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.DateTime;

public class classSchedule {
    private int year;
    private int month;
    private int day;
    private  ArrayList<Course> courses;
    /**
     * todo：
     * 将下面的private String PATH="/Users/blake/IdeaProjects/CourseSchedule-System/courseSchedule/courseSchedule";
     * 中双引号里的内容改为想要保存课程表文件的路径地址
     */
    private String PATH="/Users/blake/IdeaProjects/CourseSchedule-System/courseSchedule/courseSchedule";

    public classSchedule(int year,int month,int day, String pathName) {
        courses=new ArrayList<>();
        this.year=year;
        this.month=month;
        this.day=day;
        try {
            String readCourses=readfileUtil.delPrefix(readfileUtil.readFile(pathName));
            String[] Courses=readCourses.split("\n");
            for(int i=0;i<Courses.length;i++){
                courses.add(new Course(Courses[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public classSchedule(int year,int month,int day, ArrayList<String> s){
        courses=new ArrayList<>();
        this.year=year;
        this.month=month;
        this.day=day;
        for(int i=0;i<s.size();i++){
            courses.add(new Course(s.get(i)));
        }
    }
    public ArrayList<String> getDays(){
        DateTime today=new DateTime(year,month,day,0,0);
        int index=0;
        ArrayList<String> res=new ArrayList<>();
        Holiday holiday=new Holiday();
        while (index<courses.size()){
            if(holiday.isHoliday(today.toString("yyyy-MM-dd"))) {}
            else if(holiday.isRestDay(today.toString("yyyy-MM-dd"))){
                res.add(today.toString("MM-dd")+" "+"上午"+" "+courses.get(index).getContent());
                index+=1;
                if(index==courses.size()-1){break;}
                res.add(today.toString("MM-dd")+" "+"下午"+" "+courses.get(index).getContent());
                index+=1;
                if(index==courses.size()-1) {
                    break;
                }
            }
            else if(DateUtil.getDayofweek(today.toString("yyyy-MM-dd"))==2){
                res.add(today.toString("MM-dd")+" "+"上午"+" "+courses.get(index).getContent());
                index+=1;
                if(index==courses.size()-1){break;}
                res.add(today.toString("MM-dd")+" "+"下午"+" "+"党日活动（班务活动）");
            }
            else if(DateUtil.getDayofweek(today.toString("yyyy-MM-dd"))==3){
                res.add(today.toString("MM-dd")+" "+"上午"+" "+courses.get(index).getContent());
                index+=1;
                if(index==courses.size()-1){break;}
                res.add(today.toString("MM-dd")+" "+"下午"+" "+"形势政策知识讲堂");
            }
            else if(DateUtil.getDayofweek(today.toString("yyyy-MM-dd"))==4){
                res.add(today.toString("MM-dd")+" "+"上午"+" "+courses.get(index).getContent());
                index+=1;
                if(index==courses.size()-1){break;}
                res.add(today.toString("MM-dd")+" "+"下午"+" "+courses.get(index).getContent());
                index+=1;
                if(index==courses.size()-1){break;}
            }
            else if(DateUtil.getDayofweek(today.toString("yyyy-MM-dd"))==5){
                if(today.toString("yyyy-MM-dd").equals("2021-09-02")){
                    res.add(today.toString("MM-dd")+" "+"上午"+" "+courses.get(index).getContent());
                    index+=1;
                    if(index==courses.size()-1){break;}
                }
                else {res.add(today.toString("MM-dd")+" "+"上午"+" "+"专题报告");}
                res.add(today.toString("MM-dd")+" "+"下午"+" "+courses.get(index).getContent());
                index+=1;
                if(index==courses.size()-1){break;}
            }
            else if(DateUtil.getDayofweek(today.toString("yyyy-MM-dd"))==6){
                res.add(today.toString("MM-dd")+" "+"上午"+" "+courses.get(index).getContent());
                index+=1;
                if(index==courses.size()-1){break;}
                res.add(today.toString("MM-dd")+" "+"下午"+" "+"自学");
            }
            today=today.plusDays(1);
        }
        today=today.plusDays(1);
        res.add(today.toString("MM-dd")+" "+"上午"+" "+courses.get(index).getContent());
        res.add(today.toString("MM-dd")+" "+"下午"+" "+"离校");
        return res;
    }

    public void printSchedule(ArrayList<String> s) throws Exception {

        Workbook workbook=new HSSFWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        //创建有个工作表
        Sheet sheet=workbook.createSheet("课程表");
        //设计表头

        Row row1=sheet.createRow(0);
        Cell cell1=row1.createCell(0);
        Cell cell11=row1.createCell(0);
        cell1.setCellStyle(style);
        cell1.setCellValue("时间");
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
        Cell cell2=row1.createCell(2);
        cell2.setCellValue("教学内容");

        Cell cell3=row1.createCell(3);
        cell3.setCellValue("组织与授课");
        int n=s.size();
        int rowIndex=1;
        Row[] rows=new Row[n];
        //填充表单
        for(String x:s){
            String[] var=x.split(" ");
            Row row=sheet.createRow(rowIndex);
            for(int j=0;j<3;j++){
                Cell cell=row.createCell(j);
                if(rowIndex%2==1){
                    cell.setCellStyle(style);
                    cell.setCellValue(var[j]);
                }
                else {
                    if(j!=0){
                        cell.setCellStyle(style);
                        cell.setCellValue(var[j]);
                    }

                    //合并单元格
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex-1,rowIndex,0,0));
                }
            }
            rowIndex++;
        }



        //生成一张表（IO）
        FileOutputStream fileOutputStream=new FileOutputStream(PATH+"课程表.xls");
        workbook.write(fileOutputStream);
        //关闭流
        fileOutputStream.close();
        //输出
        System.out.println("课程表.xls 成功生成！");
    }
//    public static void main(String[] args){
//        classSchedule test=new classSchedule(2021,9,1,"/Users/blake/IdeaProjects/poilearn/poiLearn/src/main/resources/副本1、8.31-9.30市厅级干部进修班 (第 44 期）习近平新时代中国特色社会主义思想专题)教学计划.txt");
//        ArrayList<String> res=test.getDays();
//        for (String re : res) {
//            System.out.println(re);
//        }
//
//    }

}
