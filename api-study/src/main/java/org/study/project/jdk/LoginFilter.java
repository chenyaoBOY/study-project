package org.study.project.jdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author chenyao
 * @date 2020/3/20 10:10
 * @description 登陆安全的题目，如果你的系统登陆接口在被刷。我们要建立一个防刷系统。
 * 根据登陆ip，30分钟之内，只能请求30次登陆请求，如果超过这个限制，则整个ip现在登陆请求30分钟
 */
public class LoginFilter {

    private static ConcurrentHashMap<String, LoginInfo> ipCache = new ConcurrentHashMap();
    private static int timeLimit = 30 * 60;
    private static int maxLoginCnt = 30;

    public static void main(String[] args) {
        check("127.0.0.1");//入口
    }


    public static boolean check(String ip) {
        int now = (int) (System.currentTimeMillis() / 1000);

        if (ipCache.containsKey(ip)) {
            LoginInfo info = ipCache.get(ip);
            if (info.getAbleLoginTime() != 0 && now <= info.getAbleLoginTime()) {//判断是否可以登录
                return false;
            }
            if (info.getAbleLoginTime() != 0 && now > info.getAbleLoginTime()) {
                putLoginInfo(ipCache,now,ip);
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
            if(historyLoginTimeList.size() == maxLoginCnt){
                info.setAbleLoginTime(now + timeLimit);
            }

        } else {
            putLoginInfo(ipCache, now, ip);
        }
        return true;
    }

    private static void putLoginInfo(ConcurrentHashMap<String, LoginInfo> ipCache, int now, String ip) {
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
