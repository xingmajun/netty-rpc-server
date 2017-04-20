package com.netty.test.code;

import com.google.gson.Gson;
import com.netty.test.utils.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by lenovo on 2016/8/11.
 */
public class NettyEncode extends MessageToByteEncoder<Object> {

    private Gson gson =new Gson();
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object obj, ByteBuf out) throws Exception {
        //报文总长度
        byte[] bytes = Utils.stringToBytes(gson.toJson(obj));

        out.writeBytes(bytes);
    }
}
