package com.hexin.sample.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hexin.sample.model.entity.UserRole;
import javax.persistence.Table;

/**
 *
 * @author dolyw.com
 * @date 2018/8/30 10:49
 */
@TableName(value = "user_role")
public class UserRoleDto extends UserRole {

}