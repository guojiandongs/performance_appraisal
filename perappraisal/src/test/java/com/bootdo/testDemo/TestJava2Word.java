package com.bootdo.testDemo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJava2Word {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 3; i++) {
            //文件名称
            String fileName = "宋家湾村"+i+".doc";
            createWord(fileName);
        }
        //String BASE64 = WordUtil.getImageString("D://doc//friend.jpg");
        //System.out.println(BASE64);
    }

    public static void createWord(String fileName) throws IOException {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        /** 组装数据 */
        dataMap.put("name","车建中");
        dataMap.put("sno","142733195503301210");
        dataMap.put("add","历山镇宋家湾村");
        dataMap.put("type", "建档立卡户");
        dataMap.put("jiegou", "砖木"+"结构");
        dataMap.put("time", "2000"+"年");
        dataMap.put("sizes", "80");
        dataMap.put("number", "4");
        dataMap.put("xingshi", "双坡-木屋盖");
        dataMap.put("result", "B");
        dataMap.put("content","华语天王周杰伦在北京召开周杰伦战略发布会。在发布会上，推出时尚与专业结合的：引起了广泛的关注和讨论。现在终于正式与大家见面，天猫、官网同时上线，首卖期间还有好礼相赠！买就送，就是这么任性！");
        dataMap.put("zhushi","（一）房屋\n" +
                "（1）依据标准判定，房屋安全性等级为A级，能满足房屋正常安全使用要求。\n" +
                "（2）依据标准判定，房屋安全性等级为B级，基本满足安全使用要求，仅个别构件、个别部位存在局部病害，不影响整体承载。建议对房屋局部危险构件及部位进行维修处理。\n" +
                "（3）依据标准判定，房屋安全性等级为C级，房屋主要承重构件存在明显病害，影响房屋整体承载，房屋已构成局部危房，建议及时对房屋采取加固处理措施。\n" +
                "（4）依据标准判定，房屋安全性等级为D级，房屋承重结构存在严重病害，已严重影响房屋整体承载，房屋已构成整体危房，建议立即采取措施对房屋进行可靠加固处理，也可根据实际情况拆除新建。\n" +
                "（二）窑洞\n" +
                "（1）依据标准判定，窑洞安全性等级为一级，能满足房屋正常安全使用要求，日常维护即可。\n" +
                "（2）依据标准判定，窑洞安全性等级为二级；应采取加固措施或针对性进行维修处理\n" +
                "（3）依据标准判定，窑洞安全性等级为三级；加固经济型很差，停止使用。");
        List<Map<String, Object>> newsList=new ArrayList<Map<String,Object>>();
        for(int i=1;i<=10;i++){
            newsList.add(dataMap);
        }
        dataMap.put("myListData",newsList);

        /*String myPic = "";
        String myPic2 = "";
        String myPic3 = "";
        String myPic4 = "";
        try {
            myPic = WordUtil.getImageString("D://doc//安小娇//1.jpg");
            myPic2 = WordUtil.getImageString("D://doc//安小娇//2.jpg");
            myPic3 = WordUtil.getImageString("D://doc//安小娇//3.jpg");
            myPic4 = WordUtil.getImageString("D://doc//安小娇//4.jpg");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String filePath= "D://doc";
        dataMap.put("image1", myPic);
        dataMap.put("image2", myPic2);
        dataMap.put("image3", myPic3);
        dataMap.put("image4", myPic4);*/
        //文件路径
        String filePath= "D:\\doc";
        ArrayList files = WordUtil.getFiles(filePath+"//","安小娇");
        if(files.size()>0){
            for (int i = 0; i < 4; i++) {
                if(files.size()==1){

                }
                if(files.size()==2){
                    String url = String.valueOf(files.get(1));
                    String urls = WordUtil.getImageString(url);
                    dataMap.put("image"+(i+1), urls);
                }
                if(files.size()==3){
                    String url = "";
                    if(i>1){
                        url = String.valueOf(files.get(2));
                    }else{
                        url = String.valueOf(files.get(i+1));
                    }
                    String urls = WordUtil.getImageString(url);
                    dataMap.put("image"+(i+1), urls);
                }
                if(files.size()==4){
                    String url = "";
                    if(i>2){
                        url = String.valueOf(files.get(3));
                    }else{
                        url = String.valueOf(files.get(i+1));
                    }
                    String urls = WordUtil.getImageString(url);
                    dataMap.put("image"+(i+1), urls);
                }
                if(files.size()>4){
                    String url = String.valueOf(files.get(i+1));
                    String urls = WordUtil.getImageString(url);
                    dataMap.put("image"+(i+1), urls);
                }
            }
        }

        /** 生成word */
        WordUtil.createWord(dataMap, "F.xml", filePath, fileName);
    }


}


