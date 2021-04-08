package pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class InHandler1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println("InHandler1 ：" + msg);

        //传递给下一个inChannel
        ctx.fireChannelRead(msg);
    }

   /* @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("InHandler1  channelActive");
        ctx.channel().pipeline().fireChannelRead(1);
    }*/
}