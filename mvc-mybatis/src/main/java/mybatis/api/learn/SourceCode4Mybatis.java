package mybatis.api.learn;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


/**
 * @author chenyao
 * @date 2019/5/14 eight:01
 * @description
 *      mybatis源码学习
 */
public class SourceCode4Mybatis {

    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis/api/learn/configure.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectData();

        User user2 =  sqlSession.selectOne("mybatis.api.learn.UserMapper.selectData");
        System.out.println(user);
        System.out.println(user2);
    }
}
