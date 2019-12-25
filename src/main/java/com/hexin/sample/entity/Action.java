package com.hexin.sample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("action")
public class Action {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String actionDes;
    private String actionType;
    private String actionIp;
    private Integer userId;
    private Date actionTime;
}
