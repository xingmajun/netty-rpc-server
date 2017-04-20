package com.netty.test.code;

import com.google.gson.Gson;
import com.netty.test.model.Message;
import com.netty.test.model.MethodRequest;
import com.netty.test.utils.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by lenovo on 2016/8/11.
 */
@Slf4j
public class NettyDecode extends  ByteToMessageDecoder{

    private Gson gson = new Gson();
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
//        in.readBytes(in.readableBytes());
//        if(in.readableBytes() < 4){
//            log.info("netty decode err: 报文长度小于4字节" );
//            return;
//        }
//        in.markReaderIndex();
//
//        int len = in.readInt();
//
//        if(in.readableBytes() < len){
//            in.resetReaderIndex();
//            log.info("netty decode err:丢包或粘包情况发生,返回重新获取,ip = "+
//                    channelHandlerContext.channel().remoteAddress().toString());
//            return;
//        }
        int len = in.readableBytes();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);

        String str = Utils.bytesToString(bytes);
//        Object obj = Utils.bytesToObject(bytes);
        list.add(gson.fromJson(str, MethodRequest.class));

    }
}
