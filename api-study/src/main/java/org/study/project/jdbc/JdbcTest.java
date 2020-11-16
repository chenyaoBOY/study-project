package org.study.project.jdbc;

import java.sql.*;

/**
 * @author chenyao
 * @Description: 最原始的数据库访问
 * @date 2018/6/24/10:49
 */
public class JdbcTest {

    public static void main(String[] args) throws Exception {

        //加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/data?characterEncoding=utf-8",
                "root",
                "root");

        connection.setAutoCommit(false);//不自动提交事务

        String sql = " SELECT o.order_sn  FROM shop.order o WHERE o.user_id = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1,"1");

        ResultSet resultSet = prepareStatement.executeQuery();



//        PreparedStatement prepareStatement2 = connection.prepareStatement("select time from time where id =2");
//        ResultSet resultSet = prepareStatement2.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("order_sn"));
        }

        resultSet.close();connection.commit();
        prepareStatement.close();
//        prepareStatement2.close();
        connection.close();



    }
}
