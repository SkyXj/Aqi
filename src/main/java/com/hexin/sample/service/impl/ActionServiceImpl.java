package com.hexin.sample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hexin.sample.entity.Action;
import com.hexin.sample.mapper.ActionMapper;
import com.hexin.sample.service.ActionService;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl extends ServiceImpl<ActionMapper, Action> implements ActionService {

}
