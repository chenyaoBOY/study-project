import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.study.bean.UserBean;
import org.study.dao.mapper.UserMapper;
import spring.validate.ProxyService;
import spring.validate.transactiontest.annotation.UserService;
import spring.validate.transactiontest.xml.TransactionService;

import java.util.List;

/**
 * @author chenyao
 * @date 2019/5/9 17:36
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:mybatis/applicationContext-dao.xml"})
public class SpringJunitTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test(){
//        for (int i = 0; i < 100; i++) {
//            userMapper.insert(new UserBean(i,"瑶瑶"+i));
//        }
        PageHelper.startPage(2,10);
        List<UserBean> userList = userMapper.getUserList();
        for (UserBean userBean : userList) {
            System.out.println(userBean.getId()+ "  " +userBean.getName());
        }
    }


}
