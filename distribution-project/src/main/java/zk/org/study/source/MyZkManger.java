package zk.org.study.source;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author chenyao
 * @date 2019/11/22 11:14
 * @description
 */
public class MyZkManger {
    public static final String IP = "localhost:2181";
    public static final String PATH = "firstpath";
    public static final int SESSION_TIMEOUT = 3000;

    public static void main(String[] args) throws Exception{

        ZooKeeper zk = new ZooKeeper(IP,SESSION_TIMEOUT,new MyWatcher());

        zk.create(PATH,"data".getBytes(),null, CreateMode.PERSISTENT);
        byte[] data = zk.getData(PATH, false, new Stat());
        zk.getData(PATH,new MyWatcher(),new Stat());

    }
}
