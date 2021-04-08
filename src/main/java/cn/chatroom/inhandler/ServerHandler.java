package cn.chatroom.inhandler;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

@ChannelHandler.Sharable
public class ServerHandler extends
        SimpleChannelInboundHandler<String> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server receive：" + msg);
        ctx.writeAndFlush(msg);
    }
}
