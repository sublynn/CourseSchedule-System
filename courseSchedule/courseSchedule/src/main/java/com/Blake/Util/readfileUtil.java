package com.Blake.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readfileUtil {
    public static String readFile(String filepath) throws IOException {
        FileReader fr=new FileReader(filepath);
        BufferedReader in=new BufferedReader(fr);
        StringBuilder res=new StringBuilder();
        String s=in.readLine();
        while (s!=null){
            res.append(s).append(System.lineSeparator());
            s=in.readLine();
        }
        return res.toString();
    }
    public static String delPrefix(String s){
        String[] lines=s.split(System.lineSeparator());
        StringBuilder res=new StringBuilder();
        for(String line:lines){
           if(line.contains(".")){
               int m=line.indexOf('.');
               res.append(line.substring(m+1));
           }
           else {
               res.append(line);
           }
            res.append(System.lineSeparator());
        }
        return res.toString();
    }

//    public static void main(String[] args){
//        readfileUtil test=new readfileUtil();
//        try {
//            System.out.println(test.readFile("/Users/blake/IdeaProjects/poilearn/poiLearn/src/main/resources/副本1、8.31-9.30市厅级干部进修班 (第 44 期）习近平新时代中国特色社会主义思想专题)教学计划.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
