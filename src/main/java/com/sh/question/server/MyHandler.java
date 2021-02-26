package com.sh.question.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author sunhu
 * @date 2021/2/26 14:38
 */
public class MyHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer n = (Integer) msg;
        System.out.println("MyHandler n=" + n);
        ctx.writeAndFlush(n);
    }
}
