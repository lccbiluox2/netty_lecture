package com.shengsiyuan.netty.firstexample;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    /**
     * 测试方法：
     * 1. 启动服务器
     *
     * 2.执行命令
     * lcc@lcc netty_lecture$ curl 'http://localhost:8899'
     * Hello World
     * lcc@lcc netty_lecture$
     *
     * 服务器打印如下：
     * handler added
     * channel registered
     * channel active
     * class io.netty.handler.codec.http.DefaultHttpRequest
     * /0:0:0:0:0:0:0:1:59383
     * 请求方法名：GET
     * class io.netty.handler.codec.http.LastHttpContent$1
     * /0:0:0:0:0:0:0:1:59383
     * channel inactive
     * channel unregistered
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
