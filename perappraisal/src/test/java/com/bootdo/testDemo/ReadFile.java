package com.bootdo.testDemo;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;

public class ReadFile {

    public ReadFile() {
    }
    public static ArrayList<String> getFiles(String filepath){
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(filepath);
        File[] tempLists = file.listFiles();
        for (int i = 0; i < tempLists.length; i ++) {
            if (tempLists[i].isFile()) {
                files.add(tempLists[i].toString());
            }
        }
        for (int i = 0; i < files.size(); i++) {
            System.out.println(files.get(i));
        }
        return files;
    }
    public static void main(String[] args) {

        //添加文件路径
        getFiles("D:\\doc\\");
    }
}