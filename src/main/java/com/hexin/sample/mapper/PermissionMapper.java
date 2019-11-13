package com.hexin.sample.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hexin.sample.model.PermissionDto;
import com.hexin.sample.model.RoleDto;

import java.util.List;

/**
 * PermissionMapper
 * @author dolyw.com
 * @date 2018/8/31 14:42
 */
public interface PermissionMapper extends BaseMapper<PermissionDto> {
    /**
     * 根据Role查询Permission
     * @param roleDto
     * @return java.util.List<com.wang.model.PermissionDto>
     * @author dolyw.com
     * @date 2018/8/31 11:30
     */
    List<PermissionDto> findPermissionByRole(RoleDto roleDto);
}