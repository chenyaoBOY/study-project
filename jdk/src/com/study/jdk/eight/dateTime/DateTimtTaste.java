package com.study.jdk.eight.dateTime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author chenyao
 * @date 2019/12/9 17:34
 * @description  Date Time API。加强对日期和时间的处理。
 */
public class DateTimtTaste {
    public static void main(String[] args) {

        Instant now = Instant.now();

        System.out.println(System.currentTimeMillis());
        System.out.println(now.getEpochSecond());
        System.out.println(Instant.EPOCH.getEpochSecond());
        System.out.println(now.getNano());

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear()+""+
                localDate.getMonthValue()+
                localDate.getDayOfMonth());

        System.out.println(LocalDateTime.now());

    }
}
