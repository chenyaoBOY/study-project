package com.study.jdk.io.nio.handler;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author chenyao
 * @date 2020/11/22 17:21
 * @description
 */
public class EventResource {

    public EventResource(ServerSocketChannel server, Selector selector, SelectionKey key, String msg) {
        this.server = server;
        this.selector = selector;
        this.key = key;
        this.msg = msg;
    }

    private ServerSocketChannel server;
    private Selector selector;
    private SelectionKey key;
    private String msg;

    public ServerSocketChannel getServer() {
        return server;
    }

    public void setServer(ServerSocketChannel server) {
        this.server = server;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public SelectionKey getKey() {
        return key;
    }

    public void setKey(SelectionKey key) {
        this.key = key;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
