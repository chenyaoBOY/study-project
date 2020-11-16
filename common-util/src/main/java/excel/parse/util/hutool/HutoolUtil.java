package excel.parse.util.hutool;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author chenyao
 * @date 2020/8/10 19:07
 * @description
 */
public class HutoolUtil {
    public static void main(String[] args) {

        int second = DateUtil.second(new Date());
        System.out.println(second);

        System.out.println(new Date().getTime()/1000);

    }
}
