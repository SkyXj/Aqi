/**
 * Copyright © 2018广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Title: InfluxDbUtils.java
 * @Package: com.hexin.sample.config
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description :
 * @Modify Person :
 * @version: V1.0
 */
package com.hexin.sample.tool;

import lombok.Data;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Point.Builder;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class InfluxDbUtils2<T> {
    private String userName;
    private String password;
    private String url;
    public String database;
    private String retentionPolicy;
    // InfluxDB实例
    private InfluxDB influxDB;

    Logger logger = LoggerFactory.getLogger(InfluxDbUtils2.class);

    // 数据保存策略
    public static String policyNamePix = "logmonitor";



    public InfluxDbUtils2(String userName, String password, String url, String database,
                         String retentionPolicy) {
        this.userName = userName;
        this.password = password;
        this.url = url;
        this.database = database;
        this.retentionPolicy = retentionPolicy == null || "".equals(retentionPolicy) ? "autogen" : retentionPolicy;
        this.influxDB = influxDbBuild();
    }

    /**
     * 连接数据库 ，若不存在则创建
     *
     * @return influxDb实例
     */
    private InfluxDB influxDbBuild() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(url, userName, password);
        }
        try {
            createDB(database);
            influxDB.setDatabase(database);
        } catch (Exception e) {
            logger.error("create influx db failed, error: {}", e.getMessage());
        } finally {
            influxDB.setRetentionPolicy(retentionPolicy);
        }
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }



    /****
     *  创建数据库
     * @param database
     */
    private void createDB(String database) {
        influxDB.query(new Query("CREATE DATABASE " + database));
    }

    /**
     * 查询
     * @param command 查询语句
     * @return
     */
    public QueryResult query(String command){
        return influxDB.query(new Query(command, database));
    }

    /**
     * 查询
     * @param command 查询语句
     * @return
     */
    public List<T> querylist(Class<T> cls,String command){
        List<T> lists=new ArrayList<>();
        QueryResult results = influxDB.query(new Query(command, database));
        for (QueryResult.Result result : results.getResults()) {
            List<QueryResult.Series> series= result.getSeries();
            for (QueryResult.Series serie : series) {
                List<List<Object>>  values = serie.getValues();
                List<String> columns = serie.getColumns();
                lists.addAll(getQueryData(cls,columns, values));
            }
        }
        return lists;
    }

    /***整理列名、行数据***/
    private List<T> getQueryData(Class<T> cls,List<String> columns, List<List<Object>>  values){
        List<T> lists = new ArrayList<T>();
        for (List<Object> list : values) {
//            Class <T> cls = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            T t = null;
            try {
                t = cls.newInstance();
                BeanWrapperImpl bean = new BeanWrapperImpl(t);
                for(int i=0; i< list.size(); i++){
                    String propertyName = setColumns(columns.get(i));//字段名
                    Object value = list.get(i);//相应字段值
                    bean.setPropertyValue(propertyName, value);
                }
                lists.add(t);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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

    /**
     * 插入
     * @param measurement 表
     * @param tags 标签
     * @param fields 字段
     */
    public void insert(String measurement, Map<String, String> tags, Map<String, Object> fields){
        Builder builder = Point.measurement(measurement);
        builder.tag(tags);
        builder.fields(fields);
        influxDB.write(database, "", builder.build());
    }

    /**
     * 删除
     * @param command 删除语句
     * @return 返回错误信息
     */
    public String deleteMeasurementData(String command){
        QueryResult result = influxDB.query(new Query(command, database));
        return result.getError();
    }

}
