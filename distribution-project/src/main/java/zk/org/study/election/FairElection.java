package zk.org.study.election;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.Collections;
import java.util.List;

/**
 * @Author: chenyao
 * @Date: 2019/4/12 11:12
 * @Description:
 * 公平竞争
 * 通过创建znode节点自带序列，每次选举时采用序列号最小的
 *
 */
public class FairElection {

    static String MASTER_NODE="/master";
    public static void main(String[] args) throws InterruptedException {
        ZkClient zk = new ZkClient("192.168.1.128", 10000);

        if(zk.exists(MASTER_NODE)){
            //path = servers已创建永久节点
            //Sequential方法将创建一个以server开头以数字结尾的文件夹 example：servers/server0000000014
            zk.createEphemeralSequential("/servers/server",1);
            System.out.println("服务器沦为slave");

        }else{
            zk.createEphemeral(MASTER_NODE,1);
            System.out.println("服务器成为master");
        }
        zk.subscribeDataChanges(MASTER_NODE, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                List<String> children = zk.getChildren("/servers");
                System.out.println(children);
                Collections.sort(children);
                System.out.println(children);
                if(children!=null&&children.size()>0){
                    Object o = zk.readData("/servers/" + children.get(0));
                    try {
                        zk.createEphemeral("/master",o);
                    } catch (ZkNodeExistsException e) {
                    }
                    System.out.println("slave" + children.get(0) + "抢占master");
                }
            }
        });


        Thread.sleep(Integer.MAX_VALUE);
    }

}
