package learn.springboot.springboothelloworld.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YCKJ2344
 * @date 2020-06-18
 **/
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道就绪，则触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client : {}", ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 服务端", CharsetUtil.UTF_8));
    }

    /**
     * 通道读取事件触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("Client read ctx = {}", ctx);
        ByteBuf buf = (ByteBuf) msg;
        log.info("服务器回复消息 = {}", buf.toString(CharsetUtil.UTF_8));
        log.info("服务器地址：{}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
