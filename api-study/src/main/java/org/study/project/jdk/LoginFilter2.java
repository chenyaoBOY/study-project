package org.study.project.jdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenyao
 * @date 2020/3/20 10:50
 * @description
 */
public class LoginFilter2 {
    private static ConcurrentHashMap<String, LoginInfo> ipCache = new ConcurrentHashMap();
    private static int timeLimit = 30 * 60;
    private static int maxLoginCnt = 30;

    private static ConcurrentHashMap<String, Object> lockMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        check("127.0.0.1");//入口
    }


    public static boolean check(String ip) {
        int now = (int) (System.currentTimeMillis() / 1000);

        synchronized (getLock(ip)) {//不同ip获取不同的锁
            if (ipCache.containsKey(ip)) {
                LoginInfo info = ipCache.get(ip);
                if (info.getAbleLoginTime() != 0 && now <= info.getAbleLoginTime()) {//判断是否可以登录
                    return false;
                }
                if (info.getAbleLoginTime() != 0 && now > info.getAbleLoginTime()) {
                    putLoginInfo(now, ip);
                    return true;
                }

                int beforeThirtyMinuteTime = now - timeLimit;
                List<Integer> historyLoginTimeList = info.getHistoryLoginTime();

                Iterator<Integer> iterator = historyLoginTimeList.iterator();
                //此次登陆前30分钟的所有登陆次数清空
                while (iterator.hasNext()) {
                    Integer loginTime = iterator.next();
                    if (loginTime < beforeThirtyMinuteTime) {
                        iterator.remove();
                    }
                }
                historyLoginTimeList.add(now);
                if (historyLoginTimeList.size() == maxLoginCnt) {
                    info.setAbleLoginTime(now + timeLimit);
                }

            } else {
                putLoginInfo(now, ip);
            }
        }

        return true;
    }

    private static Object getLock(String ip) {
        Object newLock = new Object();
        Object lock = lockMap.putIfAbsent(ip, newLock);
        if (lock == null) {
            lock = newLock;
        }
        return lock;
    }

    private static void putLoginInfo(int now, String ip) {
        List<Integer> list = new ArrayList<>();
        list.add(now);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setHistoryLoginTime(list);
        ipCache.put(ip, loginInfo);
    }

    static class LoginInfo {
        int ableLoginTime;
        List<Integer> historyLoginTime;

        public int getAbleLoginTime() {
            return ableLoginTime;
        }

        public void setAbleLoginTime(int ableLoginTime) {
            this.ableLoginTime = ableLoginTime;
        }

        public List<Integer> getHistoryLoginTime() {
            return historyLoginTime;
        }

        public void setHistoryLoginTime(List<Integer> historyLoginTime) {
            this.historyLoginTime = historyLoginTime;
        }
    }
}
