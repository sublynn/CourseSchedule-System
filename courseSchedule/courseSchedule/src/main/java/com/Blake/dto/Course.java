package com.Blake.dto;


public class Course {

    private String Content;

    private String Sponsor;

    public Course(String content) {
        Content = content;
    }
    public String getContent(){
        return this.Content;
    }
}
