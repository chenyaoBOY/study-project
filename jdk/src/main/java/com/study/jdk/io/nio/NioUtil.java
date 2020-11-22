package com.study.jdk.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenyao
 * @date 2020/11/22 17:30
 * @description
 */
public class NioUtil {

    public static String readSocketChannelMsg(SocketChannel channel) throws IOException {
        StringBuilder sb = new StringBuilder();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len;
        while ((len = channel.read(buffer)) > 0) {
            sb.append(new String(buffer.array(), 0, len));
        }
        buffer.flip();
        return sb.toString();
    }

    public static void printRemoteMsg(SocketChannel channel, String serverMsg) throws IOException {
        String remoteAddress = channel.getRemoteAddress().toString();
        String localAddress = channel.getLocalAddress().toString();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        System.out.println(sf.format(new Date())+":remoteAddress:" + remoteAddress + " localAddress" + localAddress + " 收到消息：" + serverMsg);
    }

    public static void writeMsg2Remote(SocketChannel channel, String msg) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(new String(msg.getBytes(), StandardCharsets.UTF_8).getBytes());
        buffer.flip();
        channel.write(buffer);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        System.out.println(sf.format(new Date())+" 发送消息："+msg);
    }
}
