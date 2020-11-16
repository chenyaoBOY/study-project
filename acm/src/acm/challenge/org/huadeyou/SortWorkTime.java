package acm.challenge.org.huadeyou;

import java.util.*;

/**
 * @author chenyao
 * @date 2019/10/16 11:01
 * @description 1上班时间 早 8点到晚10点
 * 2 每段工时 [1.75,5]
 * 3 总工时 位于[8,10]个小时
 * 4 午餐 [10:40,13:10] 晚餐 [16:40,18:30]
 * 5 午餐就餐时间 至少一小时  晚餐就餐时间 至少半小时
 * 6 班次最小粒度15分钟
 */
public class SortWorkTime {
    int MODEL = 60 / 15;
    int len = (22 - 8) * MODEL+1;
    int[] arr = new int[len];

    int MIN_T = (int) (1.75 * MODEL);//每段工时最短时间
    int MAX_T = 5 * MODEL;//每段工时最长时间
    int TOTAL_MIN_T = 8 * MODEL;//总工时下限
    int TOTAL_MAX_T = 10 * MODEL;//总工时上限

    int LUNCH_MIN_T = 1 * MODEL;//中餐最少时间
    int DINNER_MIN_T = (int) (0.5 * MODEL);//晚餐最少时间

    int TOTAL_LUNCH_TIME = 10;
    double TOTAL_DINNER_TIME = 7.3D;

    double BEGIN_LUNCH = 10.67D;
    double END_LUNCH = 20.07D;
    double BEGIN_DINNER = 34.67D;
    double END_DINNER = 42D;

    int minWorkNum = TOTAL_MIN_T / MAX_T + (TOTAL_MIN_T % MAX_T == 0 ? 0 : 1);
    int maxMaxWorkNum = TOTAL_MAX_T / MIN_T;


    List<Integer> list = new ArrayList<>();
    List<List<Integer>> totalList = new ArrayList<>();
    List<List<Integer>> resultList = new ArrayList<>();


    private void start() {
        init();

        int minPointNum = minWorkNum * 2;
//        int maxPointNum = maxMaxWorkNum * 2;
        int maxPointNum = 6;

        while (minPointNum <= maxPointNum) {
            System.out.println("计算班次：" + minPointNum);
            getPoint(minPointNum, 0);
            System.out.println("班次组合数量：" + totalList.size());
            //取出所有的班次点
            for (List<Integer> point : totalList) {//计算此班次方案是否满足各种条件
                //point集合 最低是minPointNum个 最多是maxPointNum个
//                point = new ArrayList<>(Arrays.asList(21, 40, 43, 56));
                int totalTimeLength = 0;
                int i = 0;
                boolean ifHasLunch = false;
                boolean ifHasDinner = false;
                boolean ifContinue = false;
                while (i < point.size() - 1) {
                    int timeLength = point.get(i + 1) - point.get(i);//本班次工作时长
                    if (timeLength < MIN_T || timeLength > MAX_T) {
                        //本班次工作时长不在范围内[MIN_T,MAX_T]  此方案不符合规则
                        ifContinue = true;
                        break;
                    }
                    if (!ifHasLunch && point.get(i) < BEGIN_LUNCH) ifHasLunch = true;
                    totalTimeLength += timeLength;
                    i += 2;
                }
                if (ifContinue) continue;

                if (point.get(point.size() - 1) > END_DINNER) ifHasDinner = true;

                if (totalTimeLength < TOTAL_MIN_T || totalTimeLength > TOTAL_MAX_T) {//总工时 不在范围内
                    continue;
                }
                /**
                 * 中餐时间计算
                 */
                if (ifHasLunch && !ifHasTimeToEat(point, BEGIN_LUNCH, END_LUNCH, LUNCH_MIN_T)) {
                    continue;
                }
                /**
                 * 晚餐时间计算
                 */
                if (ifHasDinner && !ifHasTimeToEat(point, BEGIN_DINNER, END_DINNER, DINNER_MIN_T)) {
                    continue;
                }
                resultList.add(point);
                print(point);
            }
            minPointNum += 2;
            list.clear();
            totalList.clear();
        }


    }

    private void print(List<Integer> point) {
        for (int i = 0; i < point.size(); i+=2) {
            System.out.print(printf(point.get(i))+"——"+printf(point.get(i+1))+"    ");

        }
        System.out.print(point.toString());
        System.out.println("");
    }

    private String printf(int num) {
        int begin = 8;
        int totalMinute = 15 * num;
        int hour = totalMinute / 60 + begin;
        int minute = 0;
        if (totalMinute % 60 > 0) {
            minute = totalMinute % 60;
        }
        return ( hour > 9 ? hour :"0" + hour)  + ":" + (minute == 0 ? "00" : minute + "");
    }

    private boolean ifHasTimeToEat(List<Integer> point, double BEGIN, double END, double MIN_T) {
        int j = 0;
        while (j < point.size() - 2) {
            int workTimeEnd = point.get(j + 1);
            int nextWorkBegin = point.get(j + 2);
            if (workTimeEnd >= BEGIN && nextWorkBegin <= END && nextWorkBegin - workTimeEnd >= MIN_T) {//头尾都在吃饭时间内
                return true;
            }
            if (workTimeEnd >= BEGIN && nextWorkBegin >= END && END - workTimeEnd >= MIN_T) {//只有头在吃饭时间内
                return true;
            }
            if (workTimeEnd <= BEGIN && nextWorkBegin > BEGIN && nextWorkBegin - BEGIN >= MIN_T) {//只有尾在吃饭时间内
                return true;
            }
            if (workTimeEnd <= BEGIN && nextWorkBegin >= END && END - BEGIN >= MIN_T) {//头尾都不在吃饭时间内
                return true;
            }
            j += 2;
        }
        return false;
    }

    /**
     * 排列组合  从数组中取出pointNum个不重复的组合 作为班次的起始位置
     * totalList里记录了所有的可能组合数字
     */
    public void getPoint(int pointNum, int index) {
        if (pointNum == index) {
            List<Integer> l = new ArrayList<>(pointNum);
            l.addAll(list);
            totalList.add(l);
            return;
        }
        for (int i = 0; i < len; i++) {
            if (index > 0 && list.get(index - 1) >= arr[i]) {
                continue;
            }
            list.add(i);
            getPoint(pointNum, index + 1);
            list.remove(index);
        }
    }


    public static void main(String[] args) {

        SortWorkTime time = new SortWorkTime();
        time.start();
        System.out.println(time.resultList.size());
    }

    private void init() {
        for (int i = 0; i < len; i++) {
            arr[i] = i;
        }
    }
}
