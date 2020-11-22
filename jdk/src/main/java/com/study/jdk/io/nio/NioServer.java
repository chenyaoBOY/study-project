package com.study.jdk.io.nio;


import com.study.jdk.io.nio.handler.*;
import com.study.jdk.io.nio.handler.impl.ConnectionEventHandler;
import com.study.jdk.io.nio.handler.impl.ReadEventHandler;
import com.study.jdk.io.nio.handler.impl.WriteEventHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NioServer implements NioTaskInterface {

    private ServerSocketChannel server;
    private Selector selector;
    /**
     * 用于存储所有的客户端的socket连接
     */
    private final Map<SocketChannel, String> clientChannelMap = new ConcurrentHashMap<>();

    {
        System.out.println("服务器初始化");
        try {
            selector = Selector.open();
            server = ServerSocketChannel.open();
            server.configureBlocking(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new NioServer(8080).doAccept();
    }

    public NioServer(int port) throws IOException {
        server.socket().bind(new InetSocketAddress(port));
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("-----------服务器已经启动--------------");
    }

    public void doAccept() throws IOException {
        while (true) {
            doNioTask(selector, this, "服务器回复消息");
        }

    }

    @Override
    public boolean processNIOTask(SelectionKey key, String msg) throws IOException {
        EventResource eventResource = new EventResource(server, selector, key, msg);
        if (key.isAcceptable()) {
            new ConnectionEventHandler().handlerEvent(eventResource);
        }
        if (key.isReadable()) {//读取客户端发来的数据
            new ReadEventHandler(clientChannelMap).handlerEvent(eventResource);
        }
        if (key.isWritable()) {
            new WriteEventHandler(clientChannelMap).handlerEvent(eventResource);
        }
        return true;
    }
}
