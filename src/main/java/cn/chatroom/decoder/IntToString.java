package cn.chatroom.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class IntToString extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        if(in.readableBytes() >= 4) {
             out.add(String.valueOf(in.readInt()));
        }

        System.out.println(Thread.currentThread().getName() +  "  IntegerToString");
    }
}
