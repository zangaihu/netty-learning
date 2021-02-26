package com.sh.question;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class C_I_1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer n = (Integer) msg;
        System.out.println("C in 111 get num = " + n);
        n++;
//        ctx.writeAndFlush(n);
    }
}