package zk.org.study.election;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.Serializable;
import java.util.Random;

/**
 * Author: chenyao
 * Date: 2019/4/10 17:48
 * Description:集群中的机器
 * 不公平竞争策略：即随机抢占，只要抢占成功即可
 * 1.当初次启动时尝试抢占master临时节点
 * 2.发现已经被占用 则作为备选机器 监听主节点删除事件
 * 3.master临时节点被删除了 则说明此时master节点上的机器已经宕机 或者出现网络故障
 * 4.集群备选节点开始抢占master
 */
public class NoFairElection implements Serializable {

    public static void main(String[] args) {
        Random random = new Random();
        int id = random.nextInt(100);
        ZkClient zk = new ZkClient("192.168.1.128", 10000);
        Server server = new Server("127.0.0." + id, id);
        //1
        if (!zk.exists("/master")) {
            zk.createEphemeral("/master", server);
            System.out.println("服务器ID=" + id + "成功抢占master");
        } else {
            if (!zk.exists("/servers")) {
                zk.createPersistent("/servers");
            }
            zk.createEphemeral("/servers/server" + id, server);
            System.out.println("服务器ID=" + id + "沦为slave");
        }
        //2

        zk.subscribeDataChanges("/master", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                zk.createEphemeral("/master", server);
                System.out.println("slave" + id + "抢占master");
            }
        });
        while (true) {
        }
    }

    static class Server implements Serializable {
        String ip;
        int id;

        public Server(String ip, int id) {
            this.ip = ip;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Server{" +
                    "ip='" + ip + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
