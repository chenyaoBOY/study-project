package org.study.project.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author chenyao
 * @date 2019/12/3 17:56
 * @description
 */
@SuppressWarnings("all")
public class LotteryService2 {
    public static final Logger logger = LoggerFactory.getLogger(LotteryService2.class);
    public static final String REDIS_KEY = "XCX_LOTTERY_KEY:";
    public static final String REDIS_KEY_TOTAL_COUNT = REDIS_KEY + "TOTAL_COUNT";
    public static final String REDIS_KEY_USER_LIST = REDIS_KEY + "USER_LIST";
    public static final String REDIS_KEY_188 = REDIS_KEY + "188";
    public static final String REDIS_KEY_88 = REDIS_KEY + "88";
    public static final String REDIS_KEY_18 = REDIS_KEY + "18";
    public static final String REDIS_KEY_8 = REDIS_KEY + "8";
    public static final String REDIS_KEY_3 = REDIS_KEY + "3";
    public static final String REDIS_KEY_1 = REDIS_KEY + "1";
    static Map<String, Integer> map = new HashMap<>();


    private static final String REDIS_VALUE_USERNAME = "username";
    private static final String REDIS_VALUE_VALUE = "value";
    private static final String REDIS_VALUE_TIME = "time";


    public static void main(String[] args) {
        while (true) {
            service();
        }
    }
    private static final String REDIS_KEY_ALL_CARD = REDIS_KEY+"ALL_CARD";
    public static  int count = 0;


    public static String service() {
        Jedis jedis= JedisUtil.jedis;
        String value = jedis.lpop(REDIS_KEY_ALL_CARD);
        if (value == null) {
            initPool(jedis);
            value = jedis.lpop(REDIS_KEY_ALL_CARD);
        }
        if(map.containsKey(value)){
            map.put(value,map.get(value)+1);
        }else{
            map.put(value,1);
        }
        return value;
    }
    public static void initPool(Jedis jedis){
        int MAX=500;
        List<String> list = new ArrayList<>(MAX);
        for (int i = 0; i < 350; i++) {
            list.add("1.88");
        }
        for (int i = 0; i < 104; i++) {
            list.add("3.88");
        }
        for (int i = 0; i < 28; i++) {
            list.add("8.88");
        }
        for (int i = 0; i < 15; i++) {
            list.add("18.88");
        }
        for (int i = 0; i < 2; i++) {
            list.add("88.88");
        }
        list.add("188.88");
        Collections.shuffle(list);
        String[] s= new String[MAX];
        jedis.lpush(REDIS_KEY_ALL_CARD,list.toArray(s));
        System.out.println(map.toString());
    }




}