package cn.protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.*;

public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {
    private final boolean isClient;

    public HttpAggregatorInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        /**
         * 请求格式，和响应格式不一样(所以存在HttpClientCodec，HttpServerCodec)
         */
        /*if (client) {
            pipeline.addLast("decoder", new HttpResponseDecoder());
            pipeline.addLast("encoder", new HttpRequestEncoder());
        } else {
            pipeline.addLast("decoder", new HttpRequestDecoder());
            pipeline.addLast("encoder", new HttpResponseEncoder());
        }*/

        if (isClient) {
            //客户端：处理http的请求编码，收到响应解码
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            //服务端：处理http的收到请求解码，响应编码
            pipeline.addLast("codec", new HttpServerCodec());
        }

        //允许消息的最大大小
        pipeline.addLast("aggregator",
                new HttpObjectAggregator(512 * 1024));
    }
}
