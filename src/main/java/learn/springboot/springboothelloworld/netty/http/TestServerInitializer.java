package learn.springboot.springboothelloworld.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author YCKJ2344
 * @date 2020-06-19
 **/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //向管道加入处理器
        //得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();

        //加入netty提供的httpServerCodec  code-decoder
        //HttpServerCodec 是netty提供的处理http请求的编译解码器
        pipeline.addLast("firstHttpServerCodec", new HttpServerCodec());
        //添加自定义handler
        pipeline.addLast("firstHttpServerHandler", new TestHttpServerHandler());
    }
}
