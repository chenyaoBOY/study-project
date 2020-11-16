package mq.org.study.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;
import java.util.HashMap;

public class ActiveMqMqProducer {

    private static final String BROKER_URL = "tcp://192.168.2.129:61616";

    public static void main(String[] args) throws JMSException {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(3);
        redeliveryPolicy.setMaximumRedeliveryDelay(1000);
        redeliveryPolicy.setInitialRedeliveryDelay(1000);
        redeliveryPolicy.setBackOffMultiplier(2);
        //1.创建工厂类
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        //2.获取连接
        Connection connection = connectionFactory.createConnection();

        //3.建立连接
        connection.start();

        //4.创建session会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列
        Queue queue = session.createQueue("my_queue");
        //6.创建消息内容
        TextMessage textMessage = session.createTextMessage("消息内容content");
        //7.创建生产者
        MessageProducer producer = session.createProducer(queue);
        //8.发送消息
        producer.send(textMessage);


        //9.提交


        //关闭资源
        producer.close();
        session.close();
        connection.close();



    }
}
