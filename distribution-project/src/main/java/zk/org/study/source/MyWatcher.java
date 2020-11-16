package zk.org.study.source;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.proto.WatcherEvent;

/**
 * @author chenyao
 * @date 2019/11/22 11:09
 * @description
 */
public class MyWatcher implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        String path = event.getPath();
        Event.KeeperState state = event.getState();
        Event.EventType type = event.getType();
        if(state == Event.KeeperState.SyncConnected){
        }else if(state == Event.KeeperState.AuthFailed){
        }else if(state == Event.KeeperState.ConnectedReadOnly){
        }else if(state == Event.KeeperState.Disconnected){
        }else if(state == Event.KeeperState.Expired){
        }else if(state == Event.KeeperState.SaslAuthenticated){

        }
        if(type == Event.EventType.NodeCreated){
        }else if(type == Event.EventType.NodeDataChanged){
        }else if(type == Event.EventType.NodeDeleted){
        }else if(type == Event.EventType.NodeChildrenChanged){

        }
        WatcherEvent wrapper = event.getWrapper();
    }
}
