package com.Blake.Util;

import com.Blake.dto.classSchedule;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUIUtil extends Frame {
     private String myString;
     private com.Blake.dto.classSchedule classSchedule;

    public void setClassSchedule(com.Blake.dto.classSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }

    private class MyActionListener implements ActionListener{

         @Override
         public void actionPerformed(ActionEvent e) {
             TextField textField=(TextField) e.getSource();
             myString = textField.getText();
//             System.out.println(myString);
             myString=readfileUtil.delPrefix(myString);
             ArrayList<String> myArrayList=new ArrayList<>();
             String[] s=myString.split(" ");
             for (String i:s){
                 myArrayList.add(i);
             }
             /**
              * todo：在下面的setClassSchedule(new classSchedule(2021,9,1,myArrayList))；
              *       中把年月日分别改成本次课程表开始时的年月日
              *
              */
             setClassSchedule(new classSchedule(2021,9,1,myArrayList));
             try {
                 classSchedule.printSchedule(classSchedule.getDays());
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
             textField.setText("");
         }
     }
     public String getMyString(){
         return myString;
     }
     public GUIUtil(){
         TextField textField=new TextField();
         textField.setBounds(200,200,800,800);
         setVisible(true);
         textField.setBackground(Color.WHITE);
         textField.addActionListener(new MyActionListener());
         add(textField,BorderLayout.NORTH);
         exit();
         pack();
     }
    private void exit(){
         addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e) {
                 System.exit(0);
             }
         });
    }

//    public static void main(String[] args) {
//        GUIUtil guiUtil=new GUIUtil();
//        System.out.println(guiUtil.getMyString());
//    }

}
