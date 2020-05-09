package com.bootdo.testDemo;

public class StringThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 26; i++) {
            System.out.println("输出字母"+(char) (65 + i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
