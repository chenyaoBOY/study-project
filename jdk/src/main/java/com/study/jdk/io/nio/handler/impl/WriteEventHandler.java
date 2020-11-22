package com.study.jdk.io.nio.handler.impl;

import com.study.jdk.io.nio.NioUtil;
import com.study.jdk.io.nio.handler.EventResource;
import com.study.jdk.io.nio.handler.NIOEventHandlerInterface;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * @author chenyao
 * @date 2020/11/22 17:18
 * @description
 */
public class WriteEventHandler implements NIOEventHandlerInterface {
    private final Map<SocketChannel, String> clientChannelMap;

    public WriteEventHandler(Map<SocketChannel, String> clientChannelMap) {
        this.clientChannelMap = clientChannelMap;
    }

    @Override
    public void handlerEvent(EventResource eventResource) throws IOException {
        SocketChannel client = (SocketChannel) eventResource.getKey().channel();
        if (!clientChannelMap.containsKey(client)) {
            return;
        }
        NioUtil.writeMsg2Remote(client, eventResource.getMsg());
        client.register(eventResource.getSelector(), SelectionKey.OP_READ);
    }
}
