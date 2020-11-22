package com.study.jdk.io.nio.handler;

import java.io.IOException;

/**
 * @author chenyao
 * @date 2020/11/22 17:19
 * @description 服务端 事件处理器接口
 */
public interface NIOEventHandlerInterface {

    void handlerEvent(EventResource eventResource) throws IOException;
}
