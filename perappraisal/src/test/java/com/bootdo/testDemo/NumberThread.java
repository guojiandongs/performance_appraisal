package com.bootdo.testDemo;

public class NumberThread extends Thread{

    @Override
    public void run() {
        for (int i = 1; i < 53; i++) {
            System.out.println("输出数字"+i);
            try {
                if(i%2==0){
                    Thread.sleep(1000);
                }
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
