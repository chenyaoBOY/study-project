package com.study.jdk.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * @author chenyao
 * @date 2020/11/22 16:48
 * @description
 */
public interface NioTaskInterface {
    boolean processNIOTask(SelectionKey selectionKey, String msg) throws IOException;

    default boolean doNioTask(Selector selector, NioTaskInterface taskInterface, String msg) throws IOException {
        int select = selector.select();//服务器端会在此阻塞
        if (select == 0) {
            return true;
        }
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            /**
             * 向服务器发送消息 返回true 可监听服务器返回的消息
             * 监听完服务器发送的消息以后 返回false 不再进行与服务器通信
             */
            return taskInterface.processNIOTask(key, msg);
        }
        return true;
    }

}
