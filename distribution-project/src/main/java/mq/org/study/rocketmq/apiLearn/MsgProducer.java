package mq.org.study.rocketmq.apiLearn;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author chenyao
 * @date 2019/12/24 17:31
 * @description
 */
public class MsgProducer {

    private static String cluster="192.168.1.131:9876;192.168.1.133:9876";
    private static String single="192.168.1.131:9876";
    private static String topic="broker-store-location-cluster";

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer();

        producer.setNamesrvAddr(cluster);
        producer.setProducerGroup(MsgProducer.class.getSimpleName());
        producer.start();
//        producer.setCreateTopicKey(topic);
        Message message = new Message();
        int i=0;
       while ( i < 30){
            message.setTopic(topic);
            message.setTags("singel");
            message.setBody("this is body msg".getBytes());
            SendResult send = producer.send(message);
            System.out.println(send);
            i++;
        }


        producer.shutdown();


    }
}
