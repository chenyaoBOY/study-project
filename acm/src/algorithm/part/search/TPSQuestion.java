package algorithm.part.search;

import java.util.Arrays;

/**
 * @author chenyao
 * @date 2019/10/11 10:49
 * @description
 */
public class TPSQuestion {
    double initT = 50000;
    double floatT = 0.98D;
    double endT = Math.exp(-8);
    int L = 1000;
    double[][] cityPos = {//城市坐标
            {1304, 2312}, {3639, 1315}, {4177, 2244}, {3712, 1399}, {3488, 1535}, {3326, 1556}, {3238, 1229},
            {4196, 1004}, {4312, 790}, {4386, 570}, {3007, 1970}, {2562, 1756}, {2788, 1491}, {2381, 1676},
            {1332, 695}, {3715, 1678}, {3918, 2179}, {4061, 2370}, {3780, 2212}, {3676, 2578}, {4029, 2838},
            {4263, 2931}, {3429, 1908}, {3507, 2367}, {3394, 2643}, {3439, 3201}, {2935, 3240}, {3140, 3550},
            {2545, 2357}, {2778, 2826}, {2370, 2975}
    };
    int len = cityPos.length;
    int[] cityList = new int[len];

    double getPathLen(int[] arr) {
        double totalPath = 0D;
        for (int i = 0; i < len - 1; i++) {
            int index1 = arr[i];
            int index2 = arr[i + 1];
            totalPath += distance(cityPos[index2], cityPos[index1]);
        }
        totalPath += distance(cityPos[len - 1], cityPos[0]);
        return totalPath;
    }

    double distance(double[] c1, double[] c2) {
        double x = c1[0] - c2[0];
        double y = c1[1] - c2[1];
        // s = (x平方+y平方) 0.5次方
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    void createNew() {//函数 生成新解 这里采用随机交换两个城市位置的方式
        int index1 = (int) (Math.random() * len);
        int index2 = (int) (Math.random() * len);
        while (index1 == index2) {
            index2 = (int) (Math.random() * len);
        }
        int tm = cityList[index1];
        cityList[index1] = cityList[index2];
        cityList[index2] = tm;
    }

    void cityListInit() {//初始化一个解
        for (int i = 0; i < len; i++) {
            cityList[i] = i;
        }
    }

    public void start() {
        cityListInit();
        double T = initT;
        int count = 0;
        while (T > endT) {
            for (int i = 0; i < L; i++) {
                int[] copyList = Arrays.copyOf(cityList, len);
                createNew();//城市的位置会发生改变
                double df = getPathLen(cityList) - getPathLen(copyList);
                if (df >= 0) {//此方案应该抛弃 但是根据概率跳出范围条件 需要适当保留该方案
                    if (Math.exp(-df / T) <= Math.random()) {//概率方案
                        cityList = Arrays.copyOf(copyList, len);
                    }
                }
            }
            T *= floatT;
            count++;
        }
        System.out.println("最终城市距离：" + getPathLen(cityList));
        System.out.println("城市分布：" + Arrays.toString(cityList));
        System.out.println("降温次数：" + count);

    }


    public static void main(String[] args) {
        new TPSQuestion().start();
//        TPSQuestion tps = new TPSQuestion();
//        double pathLen = tps.getPathLen(new int[]{21, 20, 19, 25, 27, 26, 24, 23, 18, 22, 29, 30, 28, 10, 11, 13, 0, 14, 12, 9, 8, 7, 3, 1, 6, 5, 4, 15, 16, 2, 17});
//        System.out.println(pathLen);


    }
}
