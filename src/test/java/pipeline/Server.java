package pipeline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class Server {

    private final int port;

    public Server(final int port) {
        this.port = port;
    }
    public static void main(String[] args) throws Exception {

        int port = 7878;        //1
        new Server(port).start();                //2
    }

    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(); //3
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)                                //4
                    .channel(NioServerSocketChannel.class)        //5
                    .localAddress(new InetSocketAddress(port))    //6
                    .childHandler(new ChannelInitializer<SocketChannel>() { //7
                        @Override
                        public void initChannel(SocketChannel ch){
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast(new InHandler1());
                            pipeline.addLast(new InHandler2());
                            pipeline.addLast(new InHandler3());

                            pipeline.addLast(new ServerHandler());

                            pipeline.addLast(new OutHandler1());
                            pipeline.addLast(new OutHandler2());
                            pipeline.addLast(new OutHandler3());
                        }
                    });

            ChannelFuture f = b.bind().sync();            //8

            System.out.println(Server.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();            //9
        } finally {
            group.shutdownGracefully().sync();            //10
        }
    }
}
