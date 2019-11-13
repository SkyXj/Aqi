package com.hexin.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hexin.sample.model.RoleDto;
import com.hexin.sample.model.UserDto;

import java.util.List;

/**
 * RoleMapper
 * @author dolyw.com
 * @date 2018/8/31 14:42
 */
public interface RoleMapper extends BaseMapper<RoleDto> {
    /**
     * 根据User查询Role
     * @param userDto
     * @return java.util.List<com.wang.model.RoleDto>
     * @author dolyw.com
     * @date 2018/8/31 11:30
     */
    List<RoleDto> findRoleByUser(UserDto userDto);
}