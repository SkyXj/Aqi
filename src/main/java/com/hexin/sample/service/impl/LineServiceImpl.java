package com.hexin.sample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hexin.sample.entity.Line;
import com.hexin.sample.mapper.LineMapper;
import com.hexin.sample.service.ILineService;
import org.springframework.stereotype.Service;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/1/18 15:42
 * @Version 1.0
 */
@Service
public class LineServiceImpl extends ServiceImpl<LineMapper, Line> implements ILineService {

    @Override
    public int insert(Line line) {
        return baseMapper.insert(line);
    }
}
