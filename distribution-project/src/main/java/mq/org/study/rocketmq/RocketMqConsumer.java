package mq.org.study.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * consumerGroup
 *      DEFAULT_CONSUMER
 *      Consumer组名，多个Consumer如果属于一个应用，订阅同样的消息，且消费逻辑一致，则应该将它们归为同一组
 * messageModel
 *      CLUSTERING
 *      消息模型，支持以下两种 1、集群消费 2、广播消费
 * consumeFromWhere
 *      CONSUME_FROM_LAST_OFFSET
 *      Consumer启动后，默认从什么位置开始消费
 * allocateMessageQueueStrategy
 *      AllocateMessageQueueAveragely
 *      Rebalance算法实现策略
 * subscription
 *      {}
 *      订阅关系
 * messageListener
 *      消息监听器
 * offsetStore
 *      消费进度存储
 * consumeThreadMin
 *      10
 *      消费线程池数量
 * consumeThreadMax
 *      20
 *      消费线程池数量
 * consumeConcurrentlyMaxSpan
 *      2000
 *      单队列并行消费允许的最大跨度
 * pullThresholdForQueue
 *      1000
 *      拉消息本地队列缓存消息最大数
 * pullInterval
 *      0
 *      拉消息间隔，由于是长轮询，所以为0，但是如果应用为了流控，也可以设置大于0的值，单位毫秒
 * consumeMessageBatchMaxSize
 *      1
 *      批量消费，一次消费多少条消息
 * pullBatchSize
 *      32
 *      批量拉消息，一次最多拉多少条
 */
public class RocketMqConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-group");
        MqConfig.setConfig(consumer, "consumerDemo-instance");
        //这里设置的是一个consumer的消费策略
        //CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
        //CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
        //CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.subscribe("TopicTest", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
//                System.out.println("ConsumerMessageContent:" + msgs);
                for (MessageExt msg : msgs) {
                    msg.getStoreSize();
                    try {
                        System.out.println(new String(msg.getBody(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                //返回消费状态
                //CONSUME_SUCCESS 消费成功
                //RECONSUME_LATER 消费失败，需要稍后重新消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //调用start()方法启动consumer
        consumer.start();

        System.out.println("Consumer Started.");


    }
}
