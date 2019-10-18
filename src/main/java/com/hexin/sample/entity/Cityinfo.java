/**   
 * Copyright © 2019广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: Cityinfo.java 
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
@TableName("aqi_city")
public class Cityinfo{
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
    @TableField("cityid")
    @Column(name = "cityid",length = 11,nullable = true)
    private String cityid;

    
    /**
    *
    */
    @TableField("citygid")
    @Column(name = "citygid",length = 11,nullable = true)
    private String citygid;

    
    /**
    *
    */
    @TableField("cityname")
    @Column(name = "cityname",length = 255,nullable = true)
    private String cityname;

    
    /**
    *
    */
    @TableField("provincename")
    @Column(name = "provincename",length = 255,nullable = true)
    private String provincename;

    
    /**
    *
    */
    @TableField("rankflag")
    @Column(name = "rankflag",length = 255,nullable = true)
    private String rankflag;

    
    /**
    *
    */
    @TableField("rankflag_169")
    @Column(name = "rankflag_169",length = 255,nullable = true)
    @JSONField(name="rankflag_169")
    private String rankflag_169;

    
    
}