package learn.springboot.springboothelloworld.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YCKJ2344
 * @date 2020-06-18
 **/
@Slf4j
public class NettyClient {

    public static void main(String[] args) throws Exception {
        //创建循环事件组
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        //创建客户端启动对象
        //注意客户端使用的是Bootstrap
        Bootstrap bootstrap = new Bootstrap();

        try {
            //设置相关参数
            bootstrap.group(eventExecutors) //设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道的实现类（反射）
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());//加入自己的处理器
                        }
                    });
            log.info("客户端启动完毕");
            //启动客户端连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();
            //给通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
