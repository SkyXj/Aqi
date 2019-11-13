package com.hexin.sample.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.hexin.sample.model.entity.Permission;
import javax.persistence.Table;

/**
 *
 * @author dolyw.com
 * @date 2018/8/30 10:48
 */
@TableName(value = "permission")
public class PermissionDto extends Permission {

}