package com.sh.question.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class S_I_2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer n = (Integer) msg;
        System.out.println("第二个inbound n=" + n);
        n++;
        ctx.writeAndFlush(n);
    }
}