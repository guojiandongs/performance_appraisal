package com.bootdo.testDemo;

public class ProducerThread extends Thread{

    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            System.out.println("车牌号:"+i);
        }
    }

    /*public static void main(String[] args) {
        new Thread("输出结果").start();
        new NumberThread().start();
    }*/
}
