//package com.study.jdk.io.netty;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.socket.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//
//public class NettyServer {
//
//    public NettyServer(int port){
//
//        ServerBootstrap sbt = new ServerBootstrap();
//        NioEventLoopGroup group = new NioEventLoopGroup();
//
//        sbt.group(group);
//        sbt.channel(NioServerSocketChannel.class);
//        sbt.localAddress("localhost",port);
//
//
//    }
//}
