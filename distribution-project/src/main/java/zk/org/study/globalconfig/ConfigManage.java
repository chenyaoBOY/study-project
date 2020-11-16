package zk.org.study.globalconfig;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Author: chenyao
 * Date: 2019/4/10 15:14
 * Description:管理配置信息的类
 */
public class ConfigManage {

    private static Config conf;

    static {
        ZkClient zk = new ZkClient("192.168.1.128");
        if(!zk.exists("/data/conf")){
            conf = new Config("cy", "123", "localhost", 1, 1);
            zk.createPersistent("/data");
            zk.createPersistent("/data/conf",conf);
        }else{
            conf  = zk.readData("/data/conf");
        }
        zk.subscribeDataChanges("/data/conf", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                if(data==null){
                    throw new RuntimeException("zk "+dataPath+" 无数据");
                }
                conf = (Config) data;
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                throw new RuntimeException("zk "+dataPath+"目录下的数据被删除");
            }
        });

    }

    public Config getConf() {
        return conf;
    }
}
