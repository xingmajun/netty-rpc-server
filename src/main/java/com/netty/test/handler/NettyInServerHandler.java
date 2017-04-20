package com.netty.test.handler;

import com.netty.test.model.Message;
import com.netty.test.model.MethodRequest;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by lenovo on 2016/11/3.
 */
@Slf4j
public class NettyInServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        try{
            MethodRequest methodRequest = (MethodRequest) msg;
            log.info("服务端收到客户端请求数据");
            Message message = new Message();
            if("sayHelloRpc".equals(methodRequest.getServiceName())){
                String mes  = methodRequest.getParameters()[0] + ", hello world!";
                message.setBody(mes);
            }else if("sayGoodBye".equals(methodRequest.getServiceName())){
                List<String> list = (List)methodRequest.getParameters()[0];
                String mes  = list.get(0) + " , " +list.get(1) +" good bye !";
                message.setBody(mes);
            }
            ctx.channel().writeAndFlush(message).addListener(ChannelFutureListener.CLOSE);
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接到服务端");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端与服务端断开连接");
        super.channelInactive(ctx);
    }

}
