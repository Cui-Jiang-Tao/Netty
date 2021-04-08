package pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class InHandler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InHandler2 ：" + msg);

        //传递给下一个inChannel
        ctx.fireChannelRead(msg);
    }

    /*@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("InHandler2  channelActive");
    }*/
}