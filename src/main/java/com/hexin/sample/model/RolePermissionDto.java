package com.hexin.sample.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hexin.sample.model.entity.RolePermission;
import javax.persistence.Table;

/**
 *
 * @author dolyw.com
 * @date 2018/8/30 10:49
 */
@TableName(value = "role_permission")
public class RolePermissionDto extends RolePermission {

}