package com.study.jdk.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NioClient implements NioTaskInterface{

    private final Selector selector;
    private final SocketChannel client;
    {
        System.out.println("客户端初始化");
        selector = Selector.open();
        client = SocketChannel.open();
        /**
         * 设置阻塞模式为true  则当连接不到服务器时 直接抛出connect异常
         */
        client.configureBlocking(false);

    }
    public static void main(String[] args) throws IOException {
        new NioClient(8080).doConnect();
        System.out.println("客户端启动成功！");
    }

    public NioClient(int port) throws IOException {
        client.connect(new InetSocketAddress(port));
        //客户端注册 selector 和connect操作
        client.register(selector, SelectionKey.OP_CONNECT);
    }


    public void doConnect() throws IOException {

        if (client.isConnectionPending()) {
            client.finishConnect();
            client.register(selector, SelectionKey.OP_WRITE);
        }
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                while (doNioTask(selector,this,"向服务器发送心跳检测")) {}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 5, 30, TimeUnit.SECONDS);

    }
    @Override
    public boolean processNIOTask(SelectionKey key, String msg) throws IOException {
        if (key.isReadable()) {//读取服务器消息内容
            SocketChannel server = (SocketChannel) key.channel();
            NioUtil.printRemoteMsg(server, NioUtil.readSocketChannelMsg(server));
            server.register(selector, SelectionKey.OP_WRITE);
            return false;
        }
        if(key.isWritable()) {//给服务器发送消息
            SocketChannel client = (SocketChannel) key.channel();
            NioUtil.writeMsg2Remote(client, msg);
            client.register(selector, SelectionKey.OP_READ);
        }
        if(key.isAcceptable()){
            System.out.println("isAcceptable");
        }
        if(key.isConnectable()){
            System.out.println("isConnectable");
        }
        if(key.isValid()){
            System.out.println("isValid");
        }
        return true;
    }
}
