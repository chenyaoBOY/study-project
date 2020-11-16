package zk.org.study.globalconfig;

/**
 * Author: chenyao
 * Date: 2019/4/10 15:21
 * Description:服务获取配置信息
 *              每次需要配置信息时，主动调用本地缓存获取配置信息
 *              由于配置信息维护在zookeeper上，集群可以保证信息同步
 *              和Redis memcache区别在于每次获取不用每次都连接Redis memcache
 *              这样就减少了网络通信
 */
public class ProjectService {

    public static void main(String[] args) throws InterruptedException {
        while (true){
            ConfigManage cf = new ConfigManage();
            Config conf = cf.getConf();
            System.out.println(conf);
            Thread.sleep(2000);
        }
    }
}
