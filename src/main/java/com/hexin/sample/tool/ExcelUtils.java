package com.hexin.sample.tool;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class ExcelUtils {
    //创建表头
    public static void createTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("控点名称");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("时间");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("SO2");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("NO2");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("CO");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("O3");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("AQI");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("pm2.5");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("pm10");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("Temp");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("RH");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("WD");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("WS");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("P");
        cell.setCellStyle(style);

    }

    //生成excel文件
    public static void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    public static void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
