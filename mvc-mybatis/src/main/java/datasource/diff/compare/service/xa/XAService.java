package datasource.diff.compare.service.xa;

import com.alibaba.fastjson.JSONObject;
import datasource.diff.compare.db.one.DB01Mapper;
import datasource.diff.compare.db.two.DB02Mapper;
import datasource.diff.compare.mapper.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2020/1/2 15:40
 * @description
 */
@Service
public class XAService {
    @Autowired
    DB01Mapper db01Mapper;
    @Autowired
    DB02Mapper db02Mapper;
    @Autowired
    TestService testService;

    public void update(){
        db01Mapper.updateUser();
        db02Mapper.update();
    }
    public String get(){
        User user = db01Mapper.selectData();
        return JSONObject.toJSONString(user);
    }


    public void testTryCatchFinally(){
        try {
            db01Mapper.updateUser();
            int i=1/0;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("发生异常");
        }finally {
            try {
                new Thread(()->{
                    try {
                        testService.update();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }).start();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("1.14");
        BigDecimal multiply = bd.multiply(new BigDecimal("100"));
        System.out.println(multiply.intValue());
        System.out.println(bd.divide(new BigDecimal("100")));
    }

}
