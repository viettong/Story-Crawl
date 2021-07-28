package com.spring.security.service.crawl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class TruyenFullCrawlServiceImplTest {


    @Test
    public void TestRegex(){
        StringBuilder stringBuilder = new StringBuilder();
        String title = "Chương 1";
        String regex = "([(]\\d[)])";
        String standardTitle =title.split(regex)[0];
        String[] chapNum = standardTitle.split("\\D");
        for (String num : chapNum) {
            stringBuilder.append(num);
        }
        String numchap = stringBuilder.length() == 0 ? "-1":stringBuilder.toString();
        System.out.println(numchap);

       assertEquals("111",numchap);
    }
    @Test
    public void Test_uRL(){

        String url = "https://truyenfull.vn/tac-gia/thanh-tu/";
        String[] tag =  url.split("/");

        System.out.println(tag[tag.length-1]);

    }
    @Test
    public  void  Test_local_date(){

         LocalDate date = LocalDate.now();
         int month = date.getMonthValue();
         int today = date.getDayOfMonth();

         date = date.minusDays(today - 1); // Set to start of month
         DayOfWeek weekday = date.getDayOfWeek();
         int value = weekday.getValue(); // 1 = Monday, ... 7 = Sunday

         System.out.println("Mon Tue Wed Thu Fri Sat Sun");
         for (int i = 1; i < value; i++)
             System.out.print(" ");
         while (date.getMonthValue() == month)
             {
             System.out.printf("%3d", date.getDayOfMonth());
             if (date.getDayOfMonth() == today)
                 System.out.print("*");
             else
             System.out.print(" ");
             date = date.plusDays(1);
             if (date.getDayOfWeek().getValue() == 1) System.out.println();
             }
         if (date.getDayOfWeek().getValue() != 1) System.out.println();

    }

}