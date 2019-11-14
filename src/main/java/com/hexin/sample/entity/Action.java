package com.hexin.sample.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("action")
public class Action {
    private Integer id;
    private String actionDes;
    private String actionType;
    private String actionIp;
    private Integer userId;
    private Date actionTime;
}
