package acm.challenge.org.t1001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author chenyao
 * @date 2019/9/6 11:05
 * @description
 */
public class Main {
    public static void main2(String[] args) {
        List<String> list = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            list.add(sc.nextLine());
        }
        for (String s : list) {
            String numStr = s.substring(0, 6);
            while (numStr.endsWith("0")){
                numStr = numStr.substring(0,numStr.length()-1);
                if(numStr.endsWith(".")){
                    numStr = numStr.substring(0,numStr.length()-1);
                    break;
                }
            }
            BigDecimal num = new BigDecimal(numStr);
            int pow  = Integer.valueOf(s.substring(7).trim());
            BigDecimal res = num.pow(pow);
            if(res.compareTo(new BigDecimal(1)) == -1){
                System.out.println(res.toPlainString().substring(1));
            }else{
                System.out.println(res.toPlainString());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextBigDecimal()){
            BigDecimal num = sc.nextBigDecimal();
            int pow = sc.nextInt();
            BigDecimal res = num.pow(pow);
            res = res.stripTrailingZeros();
            String result = res.toPlainString();
            if(result.startsWith("0")){
                result=result.substring(1);
            }
            System.out.println(result);
        }
    }
}
