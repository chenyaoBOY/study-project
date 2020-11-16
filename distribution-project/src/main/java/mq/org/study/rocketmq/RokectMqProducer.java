package mq.org.study.rocketmq;

import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RokectMqProducer {
    /**
     * RocketMQ有多种配置方式可以令客户端找到Name Server, 然后通过Name Server再找到Broker
     *
     *      1. producer.setNamesrvAddr("192.168.0.1:9876;192.168.0.2:9876");
     *         consumer.setNamesrvAddr("192.168.0.1:9876;192.168.0.2:9876")
     *      2. Java启动参数中指定Name Server地址
     *          -Drocketmq.namesrv.addr=192.168.0.1:9876;192.168.0.2:9876
     *      3.环境变量指定Name Server地址
     *          export NAMESRV_ADDR=192.168.0.1:9876;192.168.0.2:9876
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("test");

        MqConfig.setConfig(producer,"producerDemo-instance");
        /**
         * producer的配置
         */
        //Producer组名，多个Producer如果属于一个应用，发送同样的消息，则应该将它们归为同一组
        producer.setProducerGroup("TEST_GROUP");
        //在发送消息时，自动创建服务器不存在的topic，需要指定Key
        producer.setCreateTopicKey("TBW102");
        //在发送消息时，自动创建服务器不存在的topic，默认创建的队列数
        producer.setDefaultTopicQueueNums(4);
        //发送消息超时时间，单位毫秒
        producer.setSendMsgTimeout(1000);
        //消息Body超过多大开始压缩（Consumer收到消息会自动解压缩），单位字节
        producer.setCompressMsgBodyOverHowmuch(4096);
        //如果发送消息返回sendResult，但是sendStatus!=SEND_OK，是否重试发送
        producer.setRetryAnotherBrokerWhenNotStoreOK(false);
        //客户端限制的消息大小，超过报错，同时服务端也会限制
        producer.setMaxMessageSize(131072);
        producer.setRetryTimesWhenSendFailed(3);
        //事务消息回查监听器，如果发送事务消息，必须设置
        //Broker回查Producer事务状态时，线程池大小
        //Broker回查Producer事务状态时，线程池大小
        //Broker回查Producer事务状态时，Producer本地缓冲请求队列大小

        producer.start();

        /**
         * Producer message 数据结构
         *  topic 必填 默认null
         *  body  必填，二进制形式，序列化由应用决定，Producer与Consumer要协商好序列化形式。
         *  tags  选填，类似于Gmail为每封邮件设置的标签，方便服务器过滤使用。目前只支持每个消息设置一个tag，所以也可以类比为Notify的MessageType概念
         *  keys  选填，代表这条消息的业务关键词，服务器会根据keys创建哈希索引，设置后，可以在Console系统根据Topic、Keys来查询消息，由于是哈希索引，请尽可能保证key唯一，例如订单号，商品Id等
         *  flag  选填，完全由应用来设置，RocketMQ不做干预 默认0
         *  DelayTimeLevel 选填，消息延时级别，0表示不延时，大于0会延时特定的时间才会被消费 默认0
         *  WaitStoreMsgOK 选填，表示消息是否在服务器落盘后才返回应答 默认true
         */
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setTopic("TopicTest");
            message.setBody(("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.setTags("TopicA");
            message.setKeys("hello-key");
            message.setFlag(0);
            //延迟时间仅支持 1s/5/10/30s/1~10m/20m/30m/1h/2h  要设置10s延迟 就要setDelayTimeLevel(3)
            message.setDelayTimeLevel(0);//延迟消息再次设置
            message.setWaitStoreMsgOK(true);
            /**
             * 同步发送
             */
            SendResult sendResult = producer.send(message);

            /**
             * 异步发送
             */
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult.getSendStatus());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println(e.getMessage());
                }
            });
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();



    }





}


