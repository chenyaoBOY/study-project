package mq.org.study.rocketmq.apiLearn;

import com.alibaba.fastjson.JSONObject;
import mq.org.study.util.JsonUtil;
import org.apache.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MQ {
    String topic = "TOPIC_ORDER";
//    String addr = "192.168.1.128:9876;192.168.1.129:9876;192.168.1.130:9876";
    String addr = "192.168.1.129:9876";

    @Test
    public void producer() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer("RocketMQ_Group");
        producer.setNamesrvAddr(addr);
        producer.start();
        String body = "订单确认消息!";
        for (int i = 0; i < 10; i++) {
            Message message = new Message(topic, "ORDER_CONSUME", (body + i).getBytes());
            SendResult result = producer.send(message);
            System.out.println(JsonUtil.objectToJson(result));
        }
        producer.shutdown();
    }

    @Test
    public void consumer() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1-group-order");
        consumer.setNamesrvAddr(addr);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);//消费策略
        consumer.subscribe(topic, "OrderTag");

        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            try {
                for (MessageExt messageExt : list) {
                    if (messageExt.getReconsumeTimes() >= 2) {
                        //重试次数已达上限 记录日志或者其他操作
                        System.out.println("重试次数已达上限 记录日志或者其他操作");
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    String body = new String(messageExt.getBody());
                    if (body.equals("订单确认消息!3")) {
                        int i = 1 / 0;
                    }
                    System.out.println(Thread.currentThread().getName() + " consumerMessage=" + list.toString());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                //异常 消息重试 或者睡眠
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });

        consumer.start();
        System.out.println("consumer已经启动！");
        while (true) {
            Thread.sleep(10000);
        }
    }

    /**
     * 顺序消费测试  根据订单号将消息发送至指定队列
     *
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    @Test
    public void orderConusmeSend() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("RocketMQ_Group");
        producer.setNamesrvAddr(addr);
        producer.start();
        for (int i = 0; i < 1; i++) {
            int orderId = 100000000 + i;
            Map<String, Object> map = new HashMap<>(4);
            map.put("orderId", orderId);
            map.put("orderSn", "1301259977742839" + i);
            map.put("sendTime", System.currentTimeMillis() / 1000);
            map.put("ext","ext");
            String content = JSONObject.toJSONString(map);
            Message message = new Message("TOPIC_ORDER", "ORDER_CONSUME", map.get("orderSn").toString(), content.getBytes());
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    for (MessageQueue mq : mqs) {
                        if (mq.getQueueId() % 4 == 0) {
                            return mq;
                        }
                    }
                    return mqs.get(Integer.valueOf((Integer) arg) % mqs.size());
                }
            }, orderId);
            System.out.println(sendResult);
        }

        while (true) {

        }
    }

    @Test
    public void orderConsume() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("CONSUMER_OF_TOPIC_ORDER");
        consumer.setNamesrvAddr(addr);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);//消费策略
        consumer.subscribe("TOPIC_ORDER", "ORDER_CONSUME");
        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragely() {
            //过滤队列
            @Override
            public List<MessageQueue> allocate(String consumerGroup, String currentCID, List<MessageQueue> mqAll, List<String> cidAll) {
                //不能根据
                List<MessageQueue> filter = new ArrayList<>();
                for (MessageQueue mq : mqAll) {
                    if (mq.getQueueId() == 0) {
                        filter.add(mq);
                    }
                }
                System.out.println("consumerGroup=" + consumerGroup + " currentCID=" + currentCID+" cidAll="+cidAll+" mqAll="+mqAll);
                return super.allocate(consumerGroup, currentCID, filter, cidAll);
            }

            @Override
            public String getName() {//策略名称
                return "STATEGGY_NAME_BY_ID_HASH";
            }
        });
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody())+" queueId"+msg.getQueueId());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.out.println("consumer已经启动！");
        while (true) {
        }
    }
}
