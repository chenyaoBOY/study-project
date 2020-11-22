package com.study.jdk.io.nio.handler.impl;

import com.study.jdk.io.nio.NioUtil;
import com.study.jdk.io.nio.handler.EventResource;
import com.study.jdk.io.nio.handler.NIOEventHandlerInterface;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * @author chenyao
 * @date 2020/11/22 17:17
 * @description
 */
public class ReadEventHandler implements NIOEventHandlerInterface {
    private Map<SocketChannel, String> clientChannelMap;

    public ReadEventHandler(Map<SocketChannel, String> clientChannelMap) {
        this.clientChannelMap = clientChannelMap;
    }

    @Override
    public void handlerEvent(EventResource eventResource) throws IOException {
        SocketChannel client = (SocketChannel) eventResource.getKey().channel();
        String remoteMsg = NioUtil.readSocketChannelMsg(client);
        NioUtil.printRemoteMsg(client, remoteMsg);
        clientChannelMap.put(client, remoteMsg);
        client.register(eventResource.getSelector(), SelectionKey.OP_WRITE);
    }
}
