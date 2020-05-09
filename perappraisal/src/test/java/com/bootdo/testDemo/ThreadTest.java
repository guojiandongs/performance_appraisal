package com.bootdo.testDemo;

import ch.qos.logback.classic.LoggerContext;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    static NumberThread numberThread = new NumberThread();
    static StringThread stringThread = new StringThread();
    public static void main(String[] args) {
        /*numberThread.setPriority(Thread.MAX_PRIORITY);
        numberThread.start();
        stringThread.start();*/
        //BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        Semaphore semaphore = new Semaphore(3);
        Thread[] car = new Thread[10];
        for (int i = 0; i < 10; i++) {
            car[i] = new Thread(()->{
                //请求许可
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"可以进入停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //使用资源
                try {
                    int val = new Random().nextInt(10);
                    TimeUnit.SECONDS.sleep(val);
                    System.out.println(Thread.currentThread().getName()+"停留了"+val+"秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                semaphore.release();
                System.out.println(Thread.currentThread().getName()+"离开停车场");
            });
            car[i].start();
        }
    }



}
