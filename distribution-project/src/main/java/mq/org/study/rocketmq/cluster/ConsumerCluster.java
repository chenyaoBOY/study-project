package mq.org.study.rocketmq.cluster;

import com.alibaba.fastjson.JSONObject;
import mq.org.study.util.JsonUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

public class ConsumerCluster {
    String topic = "SendOrderConfirm";
    String addr = "192.168.1.128:9876;192.168.1.129:9876";

    @Test
    public void producer() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer("producer1-group-order");
        producer.setInstanceName("demo1-instance-producer");
        System.out.println(123);
        producer.setNamesrvAddr(addr);
        producer.start();
        String body = "订单确认消息!";
        for (int i = 0; i < 5; i++) {
            Message message = new Message(topic, "OrderTag", (body + i).getBytes());
            SendResult result = producer.send(message);
            System.out.println(JsonUtil.objectToJson(result));
        }
        producer.shutdown();
    }

    @Test
    public void consumer1() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1-group-order");
        consumer.setNamesrvAddr(addr);
        System.out.println(1234);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);//消费策略
        consumer.subscribe(topic, "OrderTag");

        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            MessageExt messageExt = list.get(0);
            System.out.println(JSONObject.toJSONString(messageExt));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.println("consumer已经启动！");
        while (true) {
            Thread.sleep(10000);
        }
    }
    @Test
    public void consumer2() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer2 = new DefaultMQPushConsumer("consumer1-group-order");
        consumer2.setNamesrvAddr(addr);
        consumer2.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);//消费策略
        consumer2.subscribe(topic, "OrderTag");
        consumer2.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            MessageExt messageExt = list.get(0);
            System.out.println(JSONObject.toJSONString(messageExt));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer2.start();
        System.out.println("consumer已经启动！");
        while (true) {
            Thread.sleep(10000);
        }
    }
}
