/**   
 * Copyright © 2019广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: Weather.java 
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

import lombok.Data;

/**
* 描述：模型
* @author sky
* @date 2019-09-24 19:05:59
*/
@Data
@TableName("weather")
public class Weather{
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
    @TableField("time")
    @Column(name = "time",length = 255,nullable = true)
    private String time;
    
    /**
    *
    */
    @TableField("weather")
    @Column(name = "weather",length = 255,nullable = true)
    private String weather;

    
    /**
    *
    */
    @TableField("weather_icon")
    @Column(name = "weather_icon",length = 255,nullable = true)
    private String weatherIcon;

    
    /**
    *
    */
    @TableField("temp")
    @Column(name = "temp",length = 255,nullable = true)
    private String temp;

    
    /**
    *
    */
    @TableField("humi")
    @Column(name = "humi",length = 255,nullable = true)
    private String humi;

    
    /**
    *
    */
    @TableField("rain")
    @Column(name = "rain",length = 255,nullable = true)
    private String rain;

    
    /**
    *
    */
    @TableField("wd")
    @Column(name = "wd",length = 255,nullable = true)
    private String wd;

    
    /**
    *
    */
    @TableField("wdangle")
    @Column(name = "wdangle",length = 255,nullable = true)
    private String wdangle;

    
    /**
    *
    */
    @TableField("ws")
    @Column(name = "ws",length = 255,nullable = true)
    private String ws;

    
    /**
    *
    */
    @TableField("wl")
    @Column(name = "wl",length = 255,nullable = true)
    private String wl;

    
    /**
    *
    */
    @TableField("visibility")
    @Column(name = "visibility",length = 255,nullable = true)
    private String visibility;

    
    /**
    *
    */
    @TableField("pressure")
    @Column(name = "pressure",length = 255,nullable = true)
    private String pressure;

    
    /**
    *
    */
    @TableField("tq")
    @Column(name = "tq",length = 255,nullable = true)
    private String tq;

    /**
     *
     */
    @TableField("cityid")
    @Column(name = "cityid",length = 11,nullable = true)
    private String cityid;

    
    
}