package com.study.jdk.io.nio.handler.impl;

import com.study.jdk.io.nio.handler.EventResource;
import com.study.jdk.io.nio.handler.NIOEventHandlerInterface;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author chenyao
 * @date 2020/11/22 17:18
 * @description
 */
public class ConnectionEventHandler implements NIOEventHandlerInterface {
    @Override
    public void handlerEvent(EventResource eventResource) throws IOException {
        SocketChannel socketChannel = eventResource.getServer().accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(eventResource.getSelector(), SelectionKey.OP_READ);
    }
}
