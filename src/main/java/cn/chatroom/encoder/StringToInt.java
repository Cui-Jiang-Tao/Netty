package cn.chatroom.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;

public class StringToInt extends MessageToByteEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String msg, ByteBuf byteBuf) throws Exception {
        System.out.println(Thread.currentThread().getName() + "  StringToInt");
        byteBuf.writeInt(Integer.parseInt(msg));
    }

    /*@Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println(Thread.currentThread().getName() +  "  StringToInt");

        int val = Integer.parseInt((String) msg);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(val);
        *//*byte[] b = new byte[4];
        buf.readBytes(b);*//*

        ctx.write(buf, promise);
    }*/
}
