package com.sh.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author sunhu
 * @date 2021/2/2 8:33
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("server is ok...");
        while (true) {
            System.out.println("wait....");
            // 此时阻塞
            Socket socket = serverSocket.accept();
            ThreadPoolExecutor threadPoolExecutor =
                    new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512), new ThreadPoolExecutor.DiscardPolicy());
            threadPoolExecutor.execute(() -> handler(socket));
        }

    }


    public static void handler(Socket socket) {
        System.out.println("一个客户端连接了：" + Thread.currentThread().getId());
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("read....");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
