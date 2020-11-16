package org.study.project.redis;
import java.util.*;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.*;

/**
 * @author chenyao
 * @date 2019/8/1 16:03
 * @description
 */
public class JedisUtil {
    public static final JedisPool pool ;
    public static final Jedis jedis;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 1.1 最大连接数
        config.setMaxTotal(30);
        //1.2 最大空闲连接数
        config.setMaxIdle(10);
        pool = new JedisPool(config, "10.103.16.184",16379,10000000,"dddddd");
//        pool = new JedisPool(config, "192.168.1.131",6379,100000);
        jedis = pool.getResource();
    }
    public static void main(String[] args) {
        jedis.set("key","value","nxxxx","expx",1);
    }

    @Test
    public void test(){
        String nx = jedis.set("distribution_lock", UUID.randomUUID().toString(), "NX", "EX", 3);
        System.out.println(nx);
    }
    @Test
    public void group() {
        jedis.set("COMPANY:DEPT:EMPLOYEE:name","xiangbi");
    }

    /**
     * 排序
     * @return
     */
    @Test
    public void sortListByRedis(){
//        List<Integer> list = Arrays.asList(1,11,2,23,12,14);
//        jedis.rpush("numberList","1","11","2","23","12","14","4");
        List<String> lrange = jedis.lrange("numberList", 0, jedis.llen("numberList"));
        System.out.println(lrange);
        List<String> numberList = jedis.sort("numberList");
        System.out.println(numberList);
        SortingParams sort = new SortingParams();
        List<String> numberList1 = jedis.sort("numberList", sort.desc());
        System.out.println(numberList1);

    }


    public static Jedis getJedis(){
        return pool.getResource();
    }
    /**
     * 获取指定key的value
     * @param key
     * @return
     */
    public static String get(String key){
        String s = jedis.get(key);
        System.out.println(s);
        return s;
    }
    /**
     * 关闭连接
     * @return
     */
    public static boolean close(){
        try {
            jedis.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    public void publish(){
        jedis.publish("channel_1","hello everyone");
    }

    @Test
    public void subscribe() {
        JedisPubSub sub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("message="+message+" channel="+channel);

            }
        };
        sub.proceed(new Client("192.168.1.131",6379),"channel_1");
        jedis.subscribe(sub,"channel_1");
        while (true){

        }
    }
}
