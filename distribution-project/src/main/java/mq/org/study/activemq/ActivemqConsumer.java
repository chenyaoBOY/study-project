package mq.org.study.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;

public class ActivemqConsumer {
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
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue("my_queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if(message!=null){
                        System.out.println(((TextMessage)message).getText());
//                            message.acknowledge();
                        throw new RuntimeException("抛出异常");
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
