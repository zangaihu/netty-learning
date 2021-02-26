package com.sh.question.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class S_I_1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer n = (Integer) msg;
        System.out.println("第一个inbound n=" + n);
        n++;
        ctx.fireChannelRead(n);
//        ctx.channel().writeAndFlush(n);  会从pipeline 尾部，经过outhandler 出去
//        ctx.writeAndFlush(n);  会从当前handler 向前，经过遇到的outhandler出去


    }
}