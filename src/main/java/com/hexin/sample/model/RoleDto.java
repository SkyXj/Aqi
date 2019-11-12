package com.hexin.sample.model;


import com.baomidou.mybatisplus.annotations.TableName;
import com.hexin.sample.model.entity.Role;
import javax.persistence.Table;

/**
 *
 * @author dolyw.com
 * @date 2018/8/30 10:47
 */
@TableName(value = "role")
public class RoleDto extends Role {

}