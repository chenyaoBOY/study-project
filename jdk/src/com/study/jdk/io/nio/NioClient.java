package com.study.jdk.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NioClient {

    private Selector selector;
    private SocketChannel client;

    public static void main(String[] args) throws IOException {

        new NioClient(8080).doConnect();
    }

    public NioClient(int port)throws IOException {
        client =  SocketChannel.open();
        client.configureBlocking(false);
        client.connect(new InetSocketAddress(port));
        selector=Selector.open();
        client.register(selector, SelectionKey.OP_CONNECT);
    }


    public void doConnect() throws IOException {

        if(client.isConnectionPending()){
            client.finishConnect();
            client.register(selector,SelectionKey.OP_WRITE);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("输入-----");
        while(scanner.hasNextLine()) {
            System.out.println("输入-----");
            String msg = scanner.nextLine();
            if("".equals(msg)) {
                continue;
            }
            process(msg);
        }

    }

    private void process(String msg) throws IOException {
        boolean b=true;
        while(b){
            int select = selector.select();
            if(select==0){
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                b =doTask(key,msg);
            }
        }


    }

    private boolean doTask(SelectionKey key,String msg) throws IOException {
        if(key.isReadable()){//读取服务器消息内容
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len = client.read(buffer);
            buffer.flip();
            if(len>0){
                System.out.println(new String(buffer.array(),0,len));
            }
            client.register(selector,SelectionKey.OP_WRITE);
            return false;

        }else if(key.isWritable()){//给服务器发送消息
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(10224);
            buffer.put(new String(msg.getBytes(),"utf-8").getBytes());
            buffer.flip();
            client.write(buffer);
            client.register(selector,SelectionKey.OP_READ);
            return true;
        }


        return true;
    }

}
