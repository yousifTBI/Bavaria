package com.example.bavaria.network;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestDate {
    public String date(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
    //    System.out.println("Current Date Time : " + dateFormat.format(cal.getTime()));

   //   cal.add(Calendar.DATE, 1);
   //   System.out.println("Add one day to current date : " + dateFormat.format(cal.getTime()));

   //   cal = Calendar.getInstance();
   //   cal.add(Calendar.MONTH, 1);
   //   System.out.println("Add one month to current date : " + dateFormat.format(cal.getTime()));

   //   cal = Calendar.getInstance();
   //   cal.add(Calendar.YEAR, 1);
   //   System.out.println("Add one year to current date : " + dateFormat.format(cal.getTime()));

   //   cal = Calendar.getInstance();
   //   cal.add(Calendar.HOUR, 1);
   //   System.out.println("Add one hour to current date : " + dateFormat.format(cal.getTime()));

   //   cal = Calendar.getInstance();
   //   cal.add(Calendar.MINUTE, 1);
   //   System.out.println("Add one minute to current date : " + dateFormat.format(cal.getTime()));

   //   cal = Calendar.getInstance();
   //   cal.add(Calendar.SECOND, 1);
   //   System.out.println("Add one second to current date : " + dateFormat.format(cal.getTime()));

   //   cal = Calendar.getInstance();
   //   cal.add(Calendar.DATE, -1);
   //   System.out.println("Subtract one day from current date : " + dateFormat.format(cal.getTime()));

   //   cal = Calendar.getInstance();
   //   cal.add(Calendar.MONTH, -1);
   //   System.out.println("Subtract one month from current date : " + dateFormat.format(cal.getTime()));

   //   cal = Calendar.getInstance();
   //   cal.add(Calendar.YEAR, -1);
   //   System.out.println("Subtract one year from current date : " + dateFormat.format(cal.getTime()));

        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -2);
      //  System.out.println("Subtract one hour from current date : " + dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime()).toString();

     //  cal = Calendar.getInstance();
     //  cal.add(Calendar.MINUTE, -1);
     //  System.out.println("Subtract one minute from current date : " + dateFormat.format(cal.getTime()));

     //  cal = Calendar.getInstance();
     //  cal.add(Calendar.SECOND, -1);
     //  System.out.println("Subtract one second from current date : " + dateFormat.format(cal.getTime()));
    }
}
