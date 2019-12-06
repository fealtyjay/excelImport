package com.ufgov.tool;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.ufgov.tool.itf.SheetPrototype;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;

public class CreateSheet implements SheetPrototype {

    public CreateSheet(String sheetName, int beginRowNum, int endRowNum) {
        this.sheetName = sheetName;
        this.beginRowNum = beginRowNum;
        this.endRowNum = endRowNum;
    }

    private  String sheetName;

    private   int   beginRowNum;

    private   int   endRowNum;

    public int getBeginRowNum() {
        return this.beginRowNum;
    }

    public int getEndRowNum() {
        return this.endRowNum;
    }

    public void exeRead(String dir) {
        File file = new File(dir);
        if(file.isDirectory()){
            File[]   allelxs = file.listFiles();
            for (File xls:allelxs
            ) {
//                String fileNamePath ="C:\\Users\\zeus\\Documents\\WeChat Files\\fealtyjay\\FileStorage\\File\\2019-11\\读取exel插入数据库\\正式数据文件举例\\中华人民共和国外交部.XLS";
//        EasyExcel.read(fileName,)
//                File file =new File(fileNamePath);
                String fileName = xls.getName().replaceAll(".xls","");
                System.out.print("--"+fileName);
                ReadSheet sheet  = new ReadSheet() ;
                sheet.setSheetName(sheetName);
                sheet.setHeadRowNumber(4);
//                OutputStream os=null;
//                PrintWriter pw=null;
                try {
//                    os = new FileOutputStream("output.sql");
//                    pw=new PrintWriter(os);
                    List<LinkedHashMap<String,String>> res = EasyExcel.read(new FileInputStream(xls)).sheet(sheetName).doReadSync();
//                  输出获取到的内容
//                  System.out.println("--"+res.toString());
                    String str ="";
                    String str2="";
                    //打印建表语句
                    for (int i=3;i<=res.size();i++){
                        //开头表注释
                        //create table语句循环一次就终止
                        str="----"+res.get(0).get(2)+"=="+res.get(0).get(6)+"\r\n create table "+res.get(0).get(2)+"( \r\n" ;
                        break;
                    }
                    for(int i=3;i<=res.size();i++){
                        LinkedHashMap<String,String> rows = res.get(i);
                        //循环到建表语句时 停止
                        if (rows.get(1).equals("建表语句：")) {
                            //将最后一个逗号删除
                            str = str.substring(0, str.length() - 3);
                            str+=");";
                            break;
                        }
                        //判断主键
                        if(rows.get(1).equals("CHAR_ID")|| "主键".equals(rows.get(2)) || "唯一主键".equals(rows.get(2))){
                            //拼接字段
                            str+=""+rows.get(1)+" "+
                                    ""+getDataType(rows.get(3), rows.get(4), rows.get(5))+" Primary Key ";
                        }else {
                            //拼接字段
                            str+=""+rows.get(1)+" "+
                                    ""+getDataType(rows.get(3), rows.get(4), rows.get(5))+" ";
                        }

                        str+=",\r\n";
                    }
                    System.out.println(str);

                    //打印表字段注释
                    for (int i=3;i<=res.size();i++){
                        //COMMENT ON table   表注释循环一次
                        str2="COMMENT ON table "+res.get(0).get(2)+" is '"+res.get(0).get(6)+"';\r\n";
                        break;
                    }
                    for(int i=3;i<=res.size();i++){
                        LinkedHashMap<String,String> rows = res.get(i);
                        if (rows.get(1).equals("建表语句：")) {
                            break;
                        }
                        str2+= "COMMENT ON column "+res.get(0).get(2)+"."+rows.get(1)+" is '"+rows.get(2)+"'; \r\n";
//                        pw.println(str);
                    }
                    System.out.println(str2);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
//                    if(os!=null){
//                        try {
//                            os.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if(pw!=null){
//                        pw.close();
//                    }
                }
            }
        }

    }

    /**
     * 获取数据类型
     * @param dataSource
     * @return
     */
    private String getDataType (String dataSource, String startLength, String endLength) {
        String dataType = "";
        if ("字符型C".equals(dataSource)) {
            dataType = "varchar2("+startLength+")";
        } else {
            dataType = "number("+startLength+", "+endLength+")";
        }
        return dataType;
    }

    public String getSheetName() {
        return null;
    }

    private static String getValue(String str){
        if(str==null)
            return "0.0";
        return str.equals("—") ? "0.0":str;
    }
}
