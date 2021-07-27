package com.spring.security.service.crawl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}