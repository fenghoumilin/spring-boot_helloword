package learn.springboot.springboothelloworld.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author YCKJ2344
 * @date 2020-06-18
 * 自定义hand
 *
 **/
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //读取数据实例（这里读取客户端发送的消息）

    /**
     * 读取数据实例（这里读取客户端发送的消息）
     * @param ctx 上下文对象，含有管道pipeline，通道 channel ，地址
     * @param msg 客户端发送数据 默认是Object对象
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //假设一个非常耗时操作 --》 异步执行 --》 提交到channel对应的NOOEventLoop 的taskQueue中
        /* Thread.sleep(10 * 1000);
         ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端2", CharsetUtil.UTF_8));
         log.info("go on");*/
        /**
         * 加入线程池
         */
        /*ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端2", CharsetUtil.UTF_8));
                log.info("go on");
            } catch (InterruptedException e) {
                log.error("channelRead InterruptedException");
            }
        });*/

        /**
         * 定时任务加入线程池
         */
        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端2", CharsetUtil.UTF_8));
                log.info("go on");
            } catch (InterruptedException e) {
                log.error("channelRead InterruptedException");
            }
        }, 5, TimeUnit.SECONDS);



        /*log.info("服务器读取线程名 ： {}", Thread.currentThread().getName());
        log.info("Server ctx = {}", ctx);

        log.info("查看channel和pipeline的关系");
        ChannelPipeline pipeline = ctx.pipeline(); //本质是一个双向列表，出栈入栈
        log.info("pipeline = {}", pipeline);

        ByteBuf buf = (ByteBuf) msg;
        log.info("Server buf = {}", buf.toString(CharsetUtil.UTF_8));
        log.info("address : {}", ctx.channel().remoteAddress());*/
    }

    /**
     * 读取数据结束，返回消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端1", CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
