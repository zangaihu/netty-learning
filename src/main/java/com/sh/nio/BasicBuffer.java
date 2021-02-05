package com.sh.nio;

import java.nio.IntBuffer;

/**
 * @author sunhu
 * @date 2021/2/3 8:25
 */
public class BasicBuffer {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(2 * i);
        }
        //读写转换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

}
