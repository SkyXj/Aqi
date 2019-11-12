/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: PointServiceImpl.java 
 * @Package: com.hexin.sample.service.impl
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */


package com.hexin.sample.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hexin.sample.entity.*;
import com.hexin.sample.service.CityinfoService;
import com.hexin.sample.tool.ExcelUtils;
import com.hexin.sample.tool.InfluxDbUtils;
import com.hexin.sample.tool.TimeTool;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.Rows;
import org.influxdb.InfluxDB;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hexin.sample.mapper.PointMapper;
import com.hexin.sample.service.PointService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* 描述： 服务实现层
* @author sky
* @date 2019-09-24 19:05:59
*/
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService{

    @Autowired
    InfluxDbUtils influxDbUtils;

    @Autowired
    CityinfoService cityinfoService;

    @Override
    public boolean insertPoint(Point point) {
        EntityWrapper<Point> wrapper=new EntityWrapper<>();
        wrapper.eq("cityname",point.getCityname());
        //有些点是没有pointgid
//        wrapper.eq("pointgid",point.getPointgid());
        wrapper.eq("pointname",point.getPointname());
        int i = selectCount(wrapper);
        if(i>0) {
            return false;
        }else {
            boolean insert = insert(point);
            return insert;
        }
    }

    @Override
    public int insertPoints(List<Point> points) {
        int i=0;
        for (Point point: points) {
            boolean b = insertPoint(point);
            if(b){
                i++;
            }
        }
        return i;
    }

    @Override
    public void excelExport(HttpServletResponse response,String startTime, String endTime, String pointname,String cityname) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        ExcelUtils.createTitle(workbook,sheet);

        String cityid = cityinfoService.getCityIdByName(cityname);
        //获取各个控点的数据
        String sql="SELECT * FROM aqi_point WHERE time > '"
                + StringUtils.trim(startTime) + "' and time < '" + StringUtils.trim(endTime)+"'"
//                + (StringUtils.isBlank(pointname)?"":(" and pointname='" + StringUtils.trim(pointname) + "'"))
                + pointnameSql(pointname)
                + (StringUtils.isBlank(cityid)?"":(" and cityid='" + StringUtils.trim(cityid) + "'"));


        //获取天气
        String sql1="SELECT * FROM weather WHERE time > '"
                + StringUtils.trim(startTime) + "' and time < '" + StringUtils.trim(endTime)+"'"
                + (StringUtils.isBlank(cityid)?"":(" and cityid='" + StringUtils.trim(cityid) + "'"));

        QueryResult results1 = influxDbUtils.query(sql1);
        if(results1.getResults() == null){
            return;
        }
        List<Weather> weatherList=new ArrayList<>();
        for (QueryResult.Result result : results1.getResults()) {
            List<QueryResult.Series> series= result.getSeries();
            for (QueryResult.Series serie : series) {
                List<List<Object>>  values = serie.getValues();
                List<String> columns = serie.getColumns();
                weatherList.addAll(getWeatherData(columns, values));
            }
        }


        QueryResult results = influxDbUtils.query(sql);
        if(results.getResults() == null){
            return;
        }
        List<AqiYz> lists=new ArrayList<>();
        for (QueryResult.Result result : results.getResults()) {
            List<QueryResult.Series> series= result.getSeries();
            for (QueryResult.Series serie : series) {
                List<List<Object>>  values = serie.getValues();
                List<String> columns = serie.getColumns();
                lists.addAll(getQueryData(columns, values));
            }
        }

        Map<String,List<AqiYz>> map=lists.stream()
                .collect(Collectors.groupingBy(s->s.getCityid()+"_"+s.getPointname()+"_"+s.getTime()));
        List<Row> rows=new ArrayList<>();
        for (String key:map.keySet()){
            Row row=new Row();
            List<AqiYz> listtmp=map.get(key);
            if (listtmp == null || listtmp.size() <= 0) {
                continue;
            }
            row.setSo2(listtmp.stream().filter(s->s.getYz().equals("so2")).collect(Collectors.toList()).get(0).getValue());
            row.setNo2(listtmp.stream().filter(s->s.getYz().equals("no2")).collect(Collectors.toList()).get(0).getValue());
            row.setCo(listtmp.stream().filter(s->s.getYz().equals("co")).collect(Collectors.toList()).get(0).getValue());
            row.setO3(listtmp.stream().filter(s->s.getYz().equals("03")).collect(Collectors.toList()).get(0).getValue());
            row.setAqi(listtmp.stream().filter(s->s.getYz().equals("aqi")).collect(Collectors.toList()).get(0).getValue());
            row.setPm2_5(listtmp.stream().filter(s->s.getYz().equals("pm_25")).collect(Collectors.toList()).get(0).getValue());
            row.setPm10(listtmp.stream().filter(s->s.getYz().equals("pm_10")).collect(Collectors.toList()).get(0).getValue());
            row.setPointname(listtmp.get(0).getPointname());
            row.setTime(listtmp.get(0).getTime());
            rows.add(row);
        }

        //排序row
        Collections.sort(rows); // 按名称排序

        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        //新增数据行，并且设置单元格数据
        int rowNum=1;
        for(Row aqi: rows){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(aqi.getPointname());
            row.createCell(1).setCellValue(TimeTool.formatTZ(aqi.getTime()));
//            HSSFCell cell = row.createCell(1);
//            cell.setCellValue(TimeTool.formatTZ(aqi.getTime()));
//            cell.setCellStyle(style);
//            row.createCell(1).setCellValue(aqi.getTime());
            row.createCell(2).setCellValue(aqi.getSo2());
            row.createCell(3).setCellValue(aqi.getNo2());
            row.createCell(4).setCellValue(aqi.getCo());
            row.createCell(5).setCellValue(aqi.getO3());
            row.createCell(6).setCellValue(aqi.getAqi());
            row.createCell(7).setCellValue(aqi.getPm2_5());
            row.createCell(8).setCellValue(aqi.getPm10());
            //天气信息
            List<Weather> weathers = weatherList.stream().filter(t -> t.getTime().equals(aqi.getTime())).collect(Collectors.toList());
            if(weathers==null||weathers.size()<=0) {
                continue;
            }
            //temp
            row.createCell(9).setCellValue(weathers.get(0).getTemp());
            //RH
            row.createCell(10).setCellValue(weathers.get(0).getHumi());
            //wd
            row.createCell(11).setCellValue(weathers.get(0).getWd());
            //ws
            row.createCell(12).setCellValue(weathers.get(0).getWs());
            //p
            row.createCell(13).setCellValue(weathers.get(0).getPressure());

//            HSSFCell cell = row.createCell(3);
//            cell.setCellValue(user.getCreate_time());
//            cell.setCellStyle(style);
            rowNum++;
        }
        String start=TimeTool.format(startTime,"yyyy-MM-dd HH:mm:ss","yyyy年MM月dd日HH点");
        String end=TimeTool.format(endTime,"yyyy-MM-dd HH:mm:ss","yyyy年MM月dd日HH点");

        String fileName = cityname+"_"+start+"至"+end+"各控点aqi情况.xls";

        //生成excel文件
        try {
            ExcelUtils.buildExcelFile(fileName, workbook);
            //浏览器下载excel
            ExcelUtils.buildExcelDocument(fileName,workbook,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String pointnameSql(String pointnames){
        String result=" and ( 1!=1 ";
        if(StringUtils.isBlank(pointnames)){
           return "";
        }
        if(pointnames.indexOf(',')<0){
            return " and pointname='"+pointnames+"'";
        }
        String[] strs = pointnames.split(",");
        for (String pointname : strs) {
            result+="or pointname= '"+pointname+"' ";
        }
        result+=")";
        return result;
    }

    /***整理列名、行数据***/
    private List<Weather> getWeatherData(List<String> columns, List<List<Object>>  values){
        List<Weather> lists = new ArrayList<Weather>();
        for (List<Object> list : values) {
            Weather info = new Weather();
            BeanWrapperImpl bean = new BeanWrapperImpl(info);
            for(int i=0; i< list.size(); i++){
                String propertyName = setColumns(columns.get(i));//字段名
                Object value = list.get(i);//相应字段值
                bean.setPropertyValue(propertyName, value);
            }
            lists.add(info);
        }
        return lists;
    }


    /***整理列名、行数据***/
    private List<AqiYz> getQueryData(List<String> columns, List<List<Object>>  values){
        List<AqiYz> lists = new ArrayList<AqiYz>();
        for (List<Object> list : values) {
            AqiYz info = new AqiYz();
            BeanWrapperImpl bean = new BeanWrapperImpl(info);
            for(int i=0; i< list.size(); i++){
                String propertyName = setColumns(columns.get(i));//字段名
                Object value = list.get(i);//相应字段值
                bean.setPropertyValue(propertyName, value);
            }
            lists.add(info);
        }
        return lists;
    }


    /***转义字段***/
    private String setColumns(String column){
        String[] cols = column.split("_");
        StringBuffer sb = new StringBuffer();
        for(int i=0; i< cols.length; i++){
            String col = cols[i].toLowerCase();
            if(i != 0){
                String start = col.substring(0, 1).toUpperCase();
                String end = col.substring(1).toLowerCase();
                col = start + end;
            }
            sb.append(col);
        }
        return sb.toString();
    }

    @Override
    public Page<Point> list(Integer pagenum,Integer pagesize,String cityname,String pointname) {
        int count = selectCount(null);
        EntityWrapper<Point> wrapper=new EntityWrapper<>();
        if(!StringUtils.isBlank(cityname)){
            wrapper.eq("cityname",cityname);
        }
        if(!StringUtils.isBlank(pointname)){
            wrapper.eq("pointname",pointname);
        }
        Page<Point> pointPage = selectPage(new Page<Point>(pagenum, pagesize),wrapper);
        pointPage.setTotal(count);
        return pointPage;
    }

    //@Override
	//public Point getPoint(long id) {
		// TODO Auto-generated method stub
		//return baseMapper.getPoint(id);
	//}
	
	//@Override
	//public Page<Point> listPoint(Integer pageNumber, Integer pageSize,String keyword) {
		// TODO Auto-generated method stub
		//Page<Point> page=new Page<Point>();
		//page.setRecords(baseMapper.listPoint((pageNumber-1)*pageSize,pageSize,keyword));
		//page.setTotal(baseMapper.getTotal(keyword));
		//return page;
	//}
}