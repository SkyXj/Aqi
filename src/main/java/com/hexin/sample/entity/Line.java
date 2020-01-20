package com.hexin.sample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/1/18 15:38
 * @Version 1.0
 */
@Data
@TableName("line")
public class Line {
    @TableId(type= IdType.AUTO)
    private Integer id;
    @TableField("strategy")
    private Integer strategy;
    @TableField("created_time")
    private String createdTime;
    @TableField("road")
    private String road;
    @TableField("duration")
    private Integer duration;
    @TableField("time")
    private String time;
    @TableField("distance")
    private Integer distance;
    @TableField("tolls")
    private String tolls;
    @TableField("description")
    private String description;
}
