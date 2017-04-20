package com.netty.test.server;

import com.netty.test.code.NettyDecode;
import com.netty.test.code.NettyEncode;
import com.netty.test.handler.NettyInServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lenovo on 2016/8/5.
 */
@Slf4j
public class NettyServer {

    private static EventLoopGroup boosGroup;
    private static EventLoopGroup workerGroup;


    public void run() throws Exception {
        boosGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        try {
            log.info("server run started ...");
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workerGroup)//前者用来处理accept事件，后者用于处理已经建立的连接的io
                    .channel(NioServerSocketChannel.class)//用它来建立新accept的连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyEncode());
                            socketChannel.pipeline().addLast(new NettyDecode());
                            //心跳机制 10秒未接到客户端信息 默认 掉线
                            socketChannel.pipeline().addLast(new NettyInServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_KEEPALIVE, true);
            b.bind(9400).sync();
//            ChannelFuture f = b.bind(8090).sync();
//            Config.map.put(mid, f.channel());
//            f.channel().closeFuture().await();
        } finally {

        }
    }

}
