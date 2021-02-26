package com.sh.question;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author sunhu
 * @date 2021/2/26 14:05
 */
public class Client {

    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();


        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {


                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ObjectEncoder());
                            ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE,
                                    ClassResolvers.weakCachingConcurrentResolver(null))); // 最大长度
                            ch.pipeline().addLast(new C_I_1());
                        }
                    });
            Integer n = 1;
            ChannelFuture f = bootstrap.connect("127.0.0.1", 6666).sync();
            f.channel().writeAndFlush(n);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            group.shutdownGracefully();
        }

    }

}
