/**   
 * Copyright © 2019广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: Aqi.java 
 * @Package: com.hexin.sample.entity
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.hexin.sample.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.persistence.*;

import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
* 描述：模型
* @author sky
* @date 2019-09-24 19:05:59
*/
@Data
@TableName("aqi")
public class Aqi{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
    *
    */
    @TableId(type=IdType.AUTO)
    @Column(name = "id",length = 10,nullable = false)
    private Integer id;

    
    /**
    *
    */
    @TableField("city")
    @Column(name = "city",length = 255,nullable = true)
    private String city;

    
    /**
    *
    */
    @TableField("time")
    @Column(name = "time",length = 255,nullable = true)
    private String time;
    
    /**
    *
    */
    @TableField("aqi")
    @Column(name = "aqi",length = 255,nullable = true)
    private String aqi;

    
    /**
    *
    */
    @TableField("pm2_5")
    @Column(name = "pm2_5",length = 255,nullable = true)
    private String pm2_5;

    
    /**
    *
    */
    @TableField("pm10")
    @Column(name = "pm10",length = 255,nullable = true)
    private String pm10;

    
    /**
    *
    */
    @TableField("so2")
    @Column(name = "so2",length = 255,nullable = true)
    private String so2;

    
    /**
    *
    */
    @TableField("no2")
    @Column(name = "no2",length = 255,nullable = true)
    private String no2;

    
    /**
    *
    */
    @TableField("o3")
    @Column(name = "o3",length = 255,nullable = true)
    private String o3;

    
    /**
    *
    */
    @TableField("o3_8h")
    @Column(name = "o3_8h",length = 255,nullable = true)
    private String o3_h;

    
    /**
    *
    */
    @TableField("co")
    @Column(name = "co",length = 255,nullable = true)
    private String co;

    
    /**
    *
    */
    @TableField("rank")
    @Column(name = "rank",length = 255,nullable = true)
    private String rank;

    
    /**
    *
    */
    @TableField("level")
    @Column(name = "level",length = 255,nullable = true)
    private String level;

    
    /**
    *
    */
    @TableField("quality")
    @Column(name = "quality",length = 255,nullable = true)
    private String quality;

    
    /**
    *
    */
    @TableField("primary_pollutant")
    @Column(name = "primary_pollutant",length = 255,nullable = true)
    private String primary_pollutant;

    
    /**
    *
    */
    @TableField("day_aqi")
    @Column(name = "day_aqi",length = 255,nullable = true)
    private String day_aqi;

    
    /**
    *
    */
    @TableField("day_poll")
    @Column(name = "day_poll",length = 255,nullable = true)
    private String day_poll;

    
    /**
    *
    */
    @TableField("day_complex")
    @Column(name = "day_complex",length = 255,nullable = true)
    private String day_complex;

    
    /**
    *
    */
    @TableField("74complexrank")
    @Column(name = "74complexrank",length = 255,nullable = true)
    @JSONField(name="74complexrank")
    private String complexrank74;

    
    /**
    *
    */
    @TableField("complexrank")
    @Column(name = "complexrank",length = 255,nullable = true)
    private String complexrank;

    
    /**
    *
    */
    @TableField("169complexrank")
    @Column(name = "169complexrank",length = 255,nullable = true)
    @JSONField(name="169complexrank")
    private String complexrank169;

    
    
}