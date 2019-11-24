package com.ufgov.tool.impl;

import com.ufgov.tool.itf.SheetPrototype;

import java.io.File;

/**
 * @Author:zeus
 * @Date:2019/11/23 15:48
 * @主要功能:
 **/
public class Z01Sheet implements SheetPrototype {

    public Z01Sheet(String sheetName, int beginRowNum, int endRowNum) {
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

            }
        }

    }


    public String getSheetName() {
        return null;
    }
}
