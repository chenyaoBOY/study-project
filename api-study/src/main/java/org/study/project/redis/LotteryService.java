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
public class LotteryService {
    public static final Logger logger = LoggerFactory.getLogger(LotteryService.class);
    public static final String REDIS_KEY = "XCX_LOTTERY_KEY:";
    public static final String REDIS_KEY_TOTAL_COUNT = REDIS_KEY + "TOTAL_COUNT";
    public static final String REDIS_KEY_USER_LIST = REDIS_KEY + "USER_LIST";
    public static final String REDIS_KEY_188 = REDIS_KEY + "188";
    public static final String REDIS_KEY_88 = REDIS_KEY + "88";
    public static final String REDIS_KEY_18 = REDIS_KEY + "18";
    public static final String REDIS_KEY_8 = REDIS_KEY + "8";
    public static final String REDIS_KEY_3 = REDIS_KEY + "3";
    public static final String REDIS_KEY_1 = REDIS_KEY + "1";
    public static final List<String> keys = new ArrayList<>();
    static Map<String, Integer> map = new HashMap<>();

    static {
        keys.add(REDIS_KEY_1);
        keys.add(REDIS_KEY_3);
        keys.add(REDIS_KEY_8);
        keys.add(REDIS_KEY_18);
        keys.add(REDIS_KEY_88);
        keys.add(REDIS_KEY_188);
    }

    private static final String REDIS_VALUE_USERNAME = "username";
    private static final String REDIS_VALUE_VALUE = "value";
    private static final String REDIS_VALUE_TIME = "time";


    public static void main(String[] args) {
        while (true) {
            service();
        }
    }

    private static void metho() {
        Jedis jedis = JedisUtil.jedis;
        jedis.del(REDIS_KEY_1);
        jedis.del(REDIS_KEY_3);
        jedis.del(REDIS_KEY_8);
        jedis.del(REDIS_KEY_18);
        jedis.del(REDIS_KEY_88);
        jedis.del(REDIS_KEY_188);
    }

    public static String service() {
        Jedis jedis = JedisUtil.jedis;
        int one = 700;
        int two = one + 208;
        int three = two + 55;
        int four = three + 30;
        int five = four + 5;
        int six = five + 2;

        String value = "";
        int activityId = 0, drawId = 0;
        boolean stopFlag = true;
        jedis.setnx(REDIS_KEY_TOTAL_COUNT, "0");
        for (String key : keys) {
            if(!jedis.exists(key)){
                jedis.hset(key, "count", "0");
                jedis.hset(key, "shoot", "0");
            }
        }
        Random r = new Random();
        while (stopFlag) {
            value = "";
            stopFlag = false;
            int random = r.nextInt(1000) + 1;
            if (random <= one) {
//            value += 1.88;activityId=376;drawId=757;
                value += 1.88;
                activityId = 300;
                drawId = 569;
                stopFlag = ifShootEver(REDIS_KEY_1, 100, 70, jedis);
            } else if (random <= two) {
//            value += 3.88;activityId=378;drawId=759;
                value += 3.88;
                activityId = 301;
                drawId = 570;
                stopFlag = ifShootEver(REDIS_KEY_3, 125, 26, jedis);
            } else if (random <= three) {
//            value += 8.88;activityId=380;drawId=761;
                value += 8.88;
                activityId = 302;
                drawId = 571;
                stopFlag = ifShootEver(REDIS_KEY_8, 200, 11, jedis);
            } else if (random <= four) {
//            value += 18.88;activityId=382;drawId=763;
                value += 18.88;
                activityId = 303;
                drawId = 572;
                stopFlag = ifShootEver(REDIS_KEY_18, 200, 6, jedis);
            } else if (random <= five) {
//            value += 88.88;activityId=384;drawId=765;
                value += 88.88;
                activityId = 304;
                drawId = 573;
                stopFlag = ifShootEver(REDIS_KEY_88, 200, 1, jedis);
            } else if (random <= six) {
//            value += 188.88;activityId=386;drawId=767;
                value += 188.88;
                activityId = 305;
                drawId = 574;
                stopFlag = ifShootEver(REDIS_KEY_188, 500, 1, jedis);
            } else {
//                value += 0.88; activityId = 376; drawId = 757;
                value += 0.88;
                activityId = 300;
                drawId = 569;
                stopFlag = ifShootEver(REDIS_KEY_1, 100, 70, jedis);
            }
        }
        return value;
    }

    public static void print(Jedis jedis) {
        int sum = 0;
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList(set);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " 累计抽中次数:" + entry.getValue());

        }
        String s = jedis.get(REDIS_KEY_TOTAL_COUNT);
        System.out.println("总有效抽奖次数" + (Integer.valueOf(s) + 1));
        System.out.println("=================分割线=====================");

    }

    public static boolean ifShootEver(String key, int topLimit, int shootLimit, Jedis jedis) {

        int count = Integer.parseInt(jedis.hget(key, "count"));
        if (count <= topLimit) {//该优惠券在 本轮抽奖次数为count时 是否超出上限
            int shoot = Integer.parseInt(jedis.hget(key, "shoot"));
            if (shoot < shootLimit) {// 该优惠本轮已抽中次数是否超出上限
                jedis.hset(key, "shoot", Integer.toString(++shoot));
                addCountAll(jedis);
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
                return false;
            } else {// 本轮抽奖次数已满 去其他优惠券面值重试
                return true;
            }
        } else {//本轮次数在上限范围内  去其他优惠券面值重试
            return true;
        }
    }

    private static void addCountAll(Jedis jedis) {// 本次优惠券 可以抽奖
        for (String key : keys) {//遍历所有key值 增加有效抽奖次数
            int count = Integer.parseInt(jedis.hget(key, "count"));
            count++;
            jedis.hset(key, "count", Integer.toString(count));
            boolean f = false;
            if (count == 100 && key.equals(REDIS_KEY_1)) {
                f = true;
            }
            if (count == 125 && key.equals(REDIS_KEY_3)) {
                f = true;
            }
            if (count == 200 && (key.equals(REDIS_KEY_8) || key.equals(REDIS_KEY_18) || key.equals(REDIS_KEY_88))) {
                f = true;
            }
            if (count == 500 && key.equals(REDIS_KEY_188)) {
                f = true;
            }
            if (f) {
                print(jedis);
                jedis.hset(key, "shoot", "0");
                jedis.hset(key, "count", "0");
            }
        }
        String s = jedis.hget(REDIS_KEY_1, "shoot");
        if (s.equals("70")) {
            String s3 = jedis.hget(REDIS_KEY_8, "shoot");
            String s4 = jedis.hget(REDIS_KEY_18, "shoot");
            String s5 = jedis.hget(REDIS_KEY_88, "shoot");
            String s2 = jedis.hget(REDIS_KEY_3, "shoot");
            String s6 = jedis.hget(REDIS_KEY_188, "shoot");
            if (s2.equals("26") && s3.equals("11") && s4.equals("6") && s5.equals("1") && s6.equals("1")) {
                jedis.hset(REDIS_KEY_1, "shoot", "0");
                jedis.hset(REDIS_KEY_1, "count", "0");
            }
        }
        jedis.incr(REDIS_KEY_TOTAL_COUNT);//增加总有效抽奖次数
    }
}