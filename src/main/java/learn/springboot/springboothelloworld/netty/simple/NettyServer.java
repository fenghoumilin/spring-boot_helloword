package learn.springboot.springboothelloworld.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author YCKJ2344
 * @Date 2020/1/8
 **/
@Slf4j
public class NettyServer {

    public static void main(String[] args) throws Exception {
        //创建BossGroup 和WorkerGroup
        //说明
        //1.创建两个线程组
        //bossgroup 只处理连接请求，业务处理交给workGroup
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) //设置nio的channel作为通道实现
                    .option(ChannelOption.SO_BACKLOG, 128) //设置线程对列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象
                        //给 pipeline 设置处理器

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //可以使用集合管理SocketChannel，在推送消息是将业务加入到各个channel对应的NIOEventLoop的taskQueue里面
                            log.info("客户socketChannel hashcode = {}", socketChannel.hashCode());
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务器已经准备完成");
            //绑定一个端口
            ChannelFuture cf = bootstrap.bind(6666).sync();

            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (cf.isSuccess()) {
                        log.info("监听端口成功");
                    } else {
                        log.info("监听端口失败");
                    }
                }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    
}
