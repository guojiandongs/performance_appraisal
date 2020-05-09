package com.bootdo.testDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConsumerThread extends Thread{
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            try {
                blockingQueue.take();
                System.out.println("车库离开车牌号:"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /*public static void main(String[] args) {
        new Thread("输出结果").start();
        new NumberThread().start();
    }*/
}
