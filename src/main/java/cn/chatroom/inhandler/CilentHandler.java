package cn.chatroom.inhandler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable                                //1
public class CilentHandler extends
        SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("cilent send");
        ctx.writeAndFlush("23");
        ctx.writeAndFlush("45");
    }

    @Override       //acceptInboundMessage
    public void channelRead0(ChannelHandlerContext ctx, String msg) {

        System.out.println("Client received: " + msg);    //3
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {                    //4
        cause.printStackTrace();
        ctx.close();
    }
}
