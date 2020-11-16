package zk.org.study;

import org.I0Itec.zkclient.ZkClient;

import java.io.Serializable;

/**
 * Author: chenyao
 * Date: 2019/4/10 17:09
 * Description:用于验证临时节点 在关闭程序时 节点是否还存在
 *              测试结果显示 临时节点在程序关闭时会消失
 */
public class TempMachine implements Serializable {


    public static void main(String[] args) {
        ZkClient zk = ZkClientTest.zk;
        //创建master节点 用于主服务器的IP的获取
        if(!zk.exists("/master")){
            zk.createPersistent("/master");
        }
        //配置集群节点 用于存储所有集群机器的IP信息
        if(!zk.exists("/servers")){
            zk.createPersistent("/servers");
        }

        zk.createEphemeral("/servers/server1",new Server("localhost","bj"));

        Object o = zk.readData("/servers/server1");
        System.out.println(o);
        zk.close();
    }

   static class Server implements Serializable{
        String ip;
        String address;

        public Server(String ip, String address) {
            this.ip = ip;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Server{" +
                    "ip='" + ip + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
