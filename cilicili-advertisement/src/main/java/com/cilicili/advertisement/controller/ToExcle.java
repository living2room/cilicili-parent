package com.cilicili.advertisement.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("ToExcle")
@RequestMapping("/excletable")
public class ToExcle {

	//转成Excle方法
	@RequestMapping(value = "/outPutExcel",method = RequestMethod.GET)
    @ResponseBody
    public void outPutExcel(HttpServletRequest request,HttpServletResponse response,List<T> list) throws UnsupportedEncodingException {
        //一、从数据库拿数据
        if (null == request || null == response){
            return;
        }
        //根据request获得前台传递过来的数据
       
        //根据前台的数据去数据库查出来自己要的数据
        
        
        //二、 数据转成excel
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-download");
		//定义excel的文件的名字
        String fileName="产品列表.xlsx";
        fileName= URLEncoder.encode(fileName, "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        // 定义一个新的工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        // 创建一个Sheet页
        XSSFSheet sheet = wb.createSheet("products");
        sheet.setDefaultRowHeight((short) (2 * 256));//设置行高
        sheet.setColumnWidth(0, 4000);//设置列宽
        sheet.setColumnWidth(1,4000);
        sheet.setColumnWidth(2,4000);
        sheet.setColumnWidth(3,4000);
        sheet.setColumnWidth(11,4000);
        sheet.setColumnWidth(12,4000);
        sheet.setColumnWidth(13,4000);
        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
		//获得表格第一行
        XSSFRow row = sheet.createRow(0);
		//根据需要给第一行每一列设置标题
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("产品ID");

        cell=row.createCell(1);
        cell.setCellValue("链接");

        cell=row.createCell(2);
        cell.setCellValue("图片地址");

        cell=row.createCell(3);
        cell.setCellValue("图片描述");

        cell=row.createCell(4);
        cell.setCellValue("图片信息");

        cell=row.createCell(5);
        cell.setCellValue("发布时间");

        cell=row.createCell(6);
        cell.setCellValue("修改时间");

        cell=row.createCell(7);
        cell.setCellValue("发布人id");

        cell=row.createCell(8);
        cell.setCellValue("广告状态");


        XSSFRow rows;
        XSSFCell cells;
		//循环后台拿到的数据给所有行每一列设置对应的值
        for (int i=0;i<list.size();i++){
            // 在这个sheet页里创建一行
            rows = sheet.createRow(i+1);
            // 该行创建一个单元格,在该单元格里设置值
            cells = rows.createCell(0);
            cells.setCellValue(list.get(i).toString());
            cells = rows.createCell(1);
            cells.setCellValue(list.get(i).toString());
            cells = rows.createCell(2);
            cells.setCellValue(list.get(i).toString());
            cells = rows.createCell(3);
            cells.setCellValue(list.get(i).toString());
            cells = rows.createCell(4);
            cells.setCellValue(list.get(i).toString());
            cells = rows.createCell(5);
            cells.setCellValue(list.get(i).toString());
            cells = rows.createCell(6);
            cells.setCellValue(list.get(i).toString());
            cells = rows.createCell(7);
            cells.setCellValue(list.get(i).toString());
            cells = rows.createCell(8);
            cells.setCellValue(list.get(i).toString());
            //如果拿到的是一个时间戳，根据时间戳转换成string格式存放
        
        }
        OutputStream out=null;
        try {
            out = response.getOutputStream();
            wb.write(out);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {

                out.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
}
