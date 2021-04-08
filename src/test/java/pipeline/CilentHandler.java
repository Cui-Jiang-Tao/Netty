package pipeline;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

@ChannelHandler.Sharable                                //1
public class CilentHandler extends
        SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("cilent send");
        ctx.writeAndFlush(Unpooled.copiedBuffer("netty 活跃", CharsetUtil.UTF_8));

    }

    @Override       //acceptInboundMessage
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Client received: " + msg);    //3
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {                    //4
        cause.printStackTrace();
        ctx.close();
    }
}
