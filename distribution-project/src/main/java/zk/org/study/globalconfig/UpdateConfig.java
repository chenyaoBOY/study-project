package zk.org.study.globalconfig;

import org.I0Itec.zkclient.ZkClient;

/**
 * Author: chenyao
 * Date: 2019/4/10 15:24
 * Description:更新zookeeper的配置
 */
public class UpdateConfig {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config("cy", "123", "localhost", 1, 1);
        ZkClient zk = new ZkClient("192.168.1.128");



        config.setUsername("zhangjing");
        config.setCoreSize(2);
        config.setIp("127.0.0.1");
        zk.writeData("/data/conf",config);
    }
}
