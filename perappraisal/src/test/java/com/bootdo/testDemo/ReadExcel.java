package com.bootdo.testDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class ReadExcel {
    //文件路径
    static String filePath= "D://doc//";

    public static void main(String[] args) throws IOException {
        ReadExcel obj = new ReadExcel();
        // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
        File file = new File(filePath+"data//改删补得2020-4-260.xls");
        List excelList = obj.readExcel(file);
        //System.out.println("list中的数据打印出来"+excelList);
        Integer index = 1;
        System.out.println("excelList========"+excelList.size());
        for (int i = 0; i < excelList.size(); i++) {
            List<String> list = (List) excelList.get(i);
            Map<String, String> dataMap = new HashMap<>();
            //for (int j = 0; j < list.size(); j++) {
            if(list.size()>0){
                dataMap.put("name",list.get(2));
                dataMap.put("sno",list.get(3));
                dataMap.put("add1",list.get(0));
                dataMap.put("add2",list.get(1));
                dataMap.put("add",list.get(0)+""+list.get(1));
                dataMap.put("type", list.get(4));
                dataMap.put("jiegou", list.get(5)+"结构");
                dataMap.put("time", list.get(6)+"年");
                dataMap.put("sizes", list.get(8));
                dataMap.put("number", list.get(7));
                dataMap.put("grade", list.get(9));
                String jiegou = list.get(5);
                String layout = "";
                if(jiegou.equals("砖木")||jiegou.equals("土木")||jiegou.equals("石木")){
                    layout = "双坡-木屋盖";
                }else if(jiegou.equals("砖混")){
                    layout = "混凝土现浇屋盖";
                }else if(jiegou.equals("石窑洞")||jiegou.equals("砖窑")){
                    layout = "砖砌体平屋顶";
                }else if(jiegou.equals("土窑洞")||jiegou.equals("土窑")){
                    layout = "平屋顶";
                }
                dataMap.put("xingshi", layout);
                String a = "D://doc//";
                ArrayList files = WordUtil.getFiles(a+"pic"+"//"+list.get(1),list.get(2));
                //System.out.print("list中的数据打印出来11111111"+list.get(j));
                if(files.size()>0) {
                    for (int j = 0; j < 4; j++) {
                        if (files.size() == 1) {

                        }
                        if (files.size() == 2) {
                            String url = String.valueOf(files.get(1));
                            String urls = WordUtil.getImageString(url);
                            dataMap.put("image" + (j + 1), urls);
                        }
                        if (files.size() == 3) {
                            String url = "";
                            if (j > 1) {
                                url = String.valueOf(files.get(2));
                            } else {
                                url = String.valueOf(files.get(j + 1));
                            }
                            String urls = WordUtil.getImageString(url);
                            dataMap.put("image" + (j + 1), urls);
                        }
                        if (files.size() == 4) {
                            String url = "";
                            if (j > 2) {
                                url = String.valueOf(files.get(3));
                            } else {
                                url = String.valueOf(files.get(j + 1));
                            }
                            String urls = WordUtil.getImageString(url);
                            dataMap.put("image" + (j + 1), urls);
                        }
                        if (files.size() > 4) {
                            String url = String.valueOf(files.get(j + 1));
                            String urls = WordUtil.getImageString(url);
                            dataMap.put("image" + (j + 1), urls);
                        }
                    }
                }
            //}

                String xml = list.get(9);
                String names = list.get(2);
                System.out.println("name========="+list);
                String type = list.get(10);
                System.out.print("\r\n xml==============="+xml);
                if(null!=type&&type.equals("认定")){
                    xml = "F.xml";
                    String village = String.valueOf(list.get(1));
                    if(i!=0){
                        List<String> list_ahead = (List) excelList.get(i-1);
                        String village_ahead  = String.valueOf(list_ahead.get(1));
                        if(village.equals(village_ahead)){
                            index+=1;
                        }else{
                            index = 1;
                        }
                    }
                    String indexStr = String.valueOf(index);
                    if(indexStr.length()==1){
                        indexStr = "00"+indexStr;
                    }else if(indexStr.length()==2){
                        indexStr = "0"+indexStr;
                    }
                    dataMap.put("index", indexStr);
                }else{
                    if(xml.equals("A")){
                        xml = "A.xml";
                    }else if(xml.equals("B")){
                        xml = "B.xml";
                    }else if(xml.equals("C")){
                        xml = "C.xml";
                    }else if(xml.equals("D")){
                        xml = "D.xml";
                    }else{
                        xml = "E.xml";
                    }
                }
                System.out.println("excelListi========"+i);
                System.out.print("type==============="+type);
                WordUtil.createWord(dataMap, xml, filePath, list.get(1)+"/"+list.get(2)+".doc");
            }
        }
    }
    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public List readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo.isEmpty()){
                            continue;
                        }
                        innerList.add(cellinfo);
                        System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
                    System.out.println();
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
