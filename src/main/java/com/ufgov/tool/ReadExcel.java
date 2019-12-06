package com.ufgov.tool;

import com.ufgov.tool.impl.Z01Sheet;
import com.ufgov.tool.impl.Z12Sheet;
import com.ufgov.tool.itf.SheetPrototype;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author:zeus
 * @Date:2019/11/22 9:48
 * @主要功能:
 **/
public class ReadExcel {
//    public  static  final String sheetName = "Z01 收入支出决算总表(财决01表)";

    public static void main(String[] args) {
        Set<SheetPrototype>  sheetPrototypes = regiester();
        for(SheetPrototype sheetPrototye:sheetPrototypes){
            sheetPrototye.exeRead("D:\\work\\外交部\\2018年决算数据");
        }


//        String fileNamePath ="C:\\Users\\zeus\\Documents\\WeChat Files\\fealtyjay\\FileStorage\\File\\2019-11\\读取exel插入数据库\\正式数据文件举例\\中华人民共和国外交部.XLS";
////        EasyExcel.read(fileName,)
//        File file =new File(fileNamePath);
//        String fileName = file.getName().replaceAll(".XLS","");
//        System.out.print("--"+fileName);
//        ReadSheet sheet  = new ReadSheet() ;
//        sheet.setSheetName(sheetName);
//        sheet.setHeadRowNumber(4);
//        OutputStream os=null;
//        PrintWriter pw=null;
//        try {
//             os = new FileOutputStream("output.sql");
//             pw=new PrintWriter(os);
//            List<LinkedHashMap<String,String>> res = EasyExcel.read(new FileInputStream(file)).sheet(sheetName).doReadSync();
//            System.out.println("--"+res.toString());
//            String str ="";
//            for(int i=5;i<=11;i++){
//                LinkedHashMap<String,String> rows6 = res.get(i);
//                str="INSERT INTO Z01(Z01ID,AGENCY_CODE,ROWNUMS,SET_YEAR,SET_MONTH,XM,AMTTYPE,AMT0,AMT1,AMT2) values ('" +
//                        UUID.randomUUID().toString().replaceAll("-","") + "','"+fileName+"','" +rows6.get(1)+"',"+
//                        "'2018','12','"+rows6.get(0)+"','收入','"+rows6.get(2)+"','"+rows6.get(3)+"','"+rows6.get(4)+"'"+
//                        ");";
//                pw.println(str);
//                System.out.println(str);
//        }
//            for(int j=5;j<=27;j++){
//                LinkedHashMap<String,String> rows6 = res.get(j);
//                str="INSERT INTO Z01(Z01ID,AGENCY_CODE,ROWNUMS,SET_YEAR,SET_MONTH,XM,AMTTYPE,AMT0,AMT1,AMT2) values ('" +
//                        UUID.randomUUID().toString().replaceAll("-","") + "','"+fileName+"','" +rows6.get(6)+"',"+
//                        "'2018','12','"+rows6.get(5)+"','支出','"+getValue(rows6.get(7))+"','"+getValue(rows6.get(8))+"','"+getValue(rows6.get(9))+"'"+
//                        ");";
//                pw.println(str);
//                System.out.println(str);
//                str="INSERT INTO Z01(Z01ID,AGENCY_CODE,ROWNUMS,SET_YEAR,SET_MONTH,XM,AMTTYPE,AMT0,AMT1,AMT2) values ('" +
//                        UUID.randomUUID().toString().replaceAll("-","") + "','"+fileName+"','" +rows6.get(11)+"',"+
//                        "'2018','12','"+rows6.get(10)+"','支出','"+getValue(rows6.get(12))+"','"+getValue(rows6.get(13))+"','"+getValue(rows6.get(14))+"'"+
//                        ");";
//                pw.println(str);
//                System.out.println(str);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if(os!=null){
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(pw!=null){
//                pw.close();
//            }
//        }

    }
//    private static String getValue(String str){
//        if(str==null)
//            return "0.0";
//        return str.equals("—") ? "0.0":str;
//    }

    private static Set<SheetPrototype> regiester(){
        Set<SheetPrototype>  sheetPrototypes = new HashSet<SheetPrototype>();
        Z01Sheet z01sheet  = new Z01Sheet("Z01 收入支出决算总表(财决01表)",5,23);
        sheetPrototypes.add(z01sheet);
        Z12Sheet z12Sheet  = new Z12Sheet("Z12 资产负债简表(财决12表)",5,23);
        sheetPrototypes.add(z12Sheet);
        return sheetPrototypes;
    }

}
