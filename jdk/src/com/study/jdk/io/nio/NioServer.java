package com.study.jdk.io.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NioServer {

    private ServerSocketChannel server;
    private Selector selector;
    private Map<SelectionKey,String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        new NioServer(8080).doAccept();
    }

    public NioServer(int port) throws IOException {
        server = ServerSocketChannel.open();
        server.configureBlocking(false);

        server.socket().bind(new InetSocketAddress(port));
        selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("-----------服务器已经启动--------------");
    }

    public void doAccept() throws IOException {
        while(true){

            System.out.println("-----------开始遍历selector--------------");
            int select = selector.select();
            if(select==0){continue;}

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                doTask(selectionKey);
            }

        }

    }

    private void doTask(SelectionKey key) throws IOException {
        if(key.isAcceptable()){
            SocketChannel socketChannel = server.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_READ);
        }else if(key.isReadable()){
            SocketChannel client = (SocketChannel) key.channel();
            String name="";
            while(true){
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int len = client.read(buffer);
                if(len>0){
                    name += new String(buffer.array(), 0, len);
                }else{
                    map.put(key,name);
                    System.out.println("客户端数据msg="+name);
                    client.register(selector,SelectionKey.OP_WRITE);
                    break;
                }

            }
        }else if(key.isWritable()){
            if(!map.containsKey(key)){return;}
            SocketChannel client = (SocketChannel) key.channel();
            String name = map.get(key);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(("服务端反馈消息："+name).getBytes());
            buffer.flip();

            client.write(buffer);

            client.register(selector,SelectionKey.OP_READ);

        }
    }
}
