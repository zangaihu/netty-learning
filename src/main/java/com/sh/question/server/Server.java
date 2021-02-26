package com.sh.question.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


public class Server {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(), workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //这个object转换的如果不放在前面会在发送的时候找不到out
                        ch.pipeline().addLast(new ObjectEncoder());
                        ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE,
                                ClassResolvers.weakCachingConcurrentResolver(null))); // 最大长度
                        ch.pipeline().addLast(new S_I_1());
                        ch.pipeline().addLast(new S_O_2());
                        ch.pipeline().addLast(new S_I_2());
                        ch.pipeline().addLast(new S_O_1());

                        ch.pipeline().addLast(new MyHandler());
                    }
                });
        try {
            ChannelFuture f = b.bind(6666).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}