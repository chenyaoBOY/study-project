package datasource.diff.compare.service.c3p0;

import com.alibaba.fastjson.JSONObject;
import datasource.diff.compare.mapper.UserMapper;
import datasource.diff.compare.mapper.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @author chenyao
 * @date 2020/1/2 10:35
 * @description
 */
@Service
@Transactional
public class DataService {

    @Autowired
    UserMapper userMapper;

    public String getUser() {
        User user = userMapper.selectData();
//        userMapper.updateUser();
//        int i = 1/0;
        return JSONObject.toJSONString(user);
    }

    public String insert1(){
        User user = new User();
        user.setAge(18+new Random().nextInt());
        user.setName("cy"+new Random().nextInt());
        userMapper.insert(user);
        try {
            Thread.sleep(50*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }
    public String insert2(){
        User user = new User();
        user.setAge(101+new Random().nextInt());
        user.setName("zd"+new Random().nextInt());
        userMapper.insert(user);
        return "ok";
    }

}
