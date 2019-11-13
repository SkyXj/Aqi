/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: Point.java 
 * @Package: com.hexin.sample.entity
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.hexin.sample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
* 描述：模型
* @author sky
* @date 2019-09-24 19:05:59
*/
@Data
@TableName("aqi_point")
public class Point{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
    *
    */
    @TableId(type= IdType.AUTO)
    @Column(name = "id",length = 10,nullable = false)
    private Integer id;

    
    /**
    *
    */
    @TableField("cityname")
    @Column(name = "cityname",length = 255,nullable = true)
    private String cityname;

    
    /**
    *
    */
    @TableField("pointgid")
    @Column(name = "pointgid",length = 11,nullable = true)
    private String pointgid;

    
    /**
    *
    */
    @TableField("pointname")
    @Column(name = "pointname",length = 255,nullable = true)
    private String pointname;

    
    /**
    *
    */
    @TableField("pointlevel")
    @Column(name = "pointlevel",length = 255,nullable = true)
    private String pointlevel;

    
    /**
    *
    */
    @TableField("region")
    @Column(name = "region",length = 255,nullable = true)
    private String region;

    
    /**
    *
    */
    @TableField("latitude")
    @Column(name = "latitude",length = 255,nullable = true)
    private String latitude;

    
    /**
    *
    */
    @TableField("longitude")
    @Column(name = "longitude",length = 255,nullable = true)
    private String longitude;

    public List<Point> getPoints(List<Row> rows){
        List<Point> points=new ArrayList<>();
        for (Row row : rows) {
            points.add(getPoint(row));
        }
        return  points;
    }
    public Point getPoint(Row row){
        Point point=new Point();
        point.setCityname(row.getCityname());
        point.setLatitude(row.getLatitude());
        point.setLongitude(row.getLongitude());
        point.setPointgid(row.getPointgid());
        point.setPointlevel(row.getPointlevel());
        point.setPointname(row.getPointname());
        point.setRegion(row.getRegion());
        return point;
    }
    
}