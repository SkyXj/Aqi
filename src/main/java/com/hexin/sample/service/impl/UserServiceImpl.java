package com.hexin.sample.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hexin.sample.mapper.UserMapper;
import com.hexin.sample.model.UserDto;
import com.hexin.sample.service.IUserService;
import org.springframework.stereotype.Service;

/**
 *
 * @author dolyw.com
 * @date 2018/8/9 15:45
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserDto> implements IUserService {

}
