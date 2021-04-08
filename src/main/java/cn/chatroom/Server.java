package cn.chatroom;

import cn.chatroom.decoder.IntToString;
import cn.chatroom.encoder.StringToInt;
import cn.chatroom.inhandler.ServerHandler;
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
                            pipeline.addLast(new StringToInt());

                            pipeline.addLast(new IntToString());
                            pipeline.addLast(new ServerHandler());
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
