package com.sh.question.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class S_O_2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Integer n = (Integer) msg;
        System.out.println("第二个 outbound n=" + n);
        n++;
        ctx.writeAndFlush(n);
    }
}