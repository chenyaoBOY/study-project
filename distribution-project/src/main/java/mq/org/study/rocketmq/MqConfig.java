package mq.org.study.rocketmq;

import org.apache.rocketmq.client.ClientConfig;

public class MqConfig {
    
    public static void setConfig(ClientConfig config,String instanceName){
        /**
         * 下面是客户端的公共配置  包括consumer
         */
        //Name Server地址列表，多个NameServer地址用分号隔开
        config.setNamesrvAddr("192.168.1.129:9876");
        //客户端实例名称，客户端创建的多个producer、Consumer实际是共用一个内部实例（这个实例包含网络连接、线程资源等）
        config.setInstanceName(instanceName);
        //客户端本机IP地址，某些机器会发生无法识别客户端IP地址情况，需要应用在代码中强制指定
        config.setClientIP("localhost");
        //通信层异步回调线程数
        config.setClientCallbackExecutorThreads(4);
        //轮询Name Server间隔时间，单位毫秒
        config.setPollNameServerInterval(30000);
        //向Broker发送心跳间隔时间，单位毫秒
        config.setHeartbeatBrokerInterval(30000);
        //持久化Consumer消费进度间隔时间，单位毫秒
        config.setPersistConsumerOffsetInterval(5000);
    }
}
