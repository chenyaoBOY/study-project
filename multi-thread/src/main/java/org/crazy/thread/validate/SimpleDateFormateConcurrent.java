package org.crazy.thread.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenyao
 * @date 2019/11/11 10:38
 * @description
 */
public class SimpleDateFormateConcurrent {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static ExecutorService service = Executors.newFixedThreadPool(500);


    public static void main(String[] args) {
        String data = "2019-11-10 21:09:26";
        while (true){
            service.execute(()->{
                try {
                    long time = simpleDateFormat.parse(data).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
