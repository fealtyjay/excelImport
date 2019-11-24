package com.ufgov.tool.itf;

/**
 * @Author:zeus
 * @Date:2019/11/23 15:42
 * @主要功能:
 **/
public interface SheetPrototype {

    public  int getBeginRowNum();

    public  int  getEndRowNum();

    public  void exeRead(String  dir);

    public  String  getSheetName();


}
