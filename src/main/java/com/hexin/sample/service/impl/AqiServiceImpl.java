/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: AqiServiceImpl.java 
 * @Package: com.hexin.sample.service.impl
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */


package com.hexin.sample.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;
import com.hexin.sample.entity.Aqi;
import com.hexin.sample.mapper.AqiMapper;
import com.hexin.sample.service.AqiService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
* 描述： 服务实现层
* @author sky
* @date 2019-09-24 19:05:59
*/
@Service
public class AqiServiceImpl extends ServiceImpl<AqiMapper, Aqi> implements AqiService{


    @Override
    public boolean insetAqi(Aqi aqi) {
        EntityWrapper<Aqi> wrapper=new EntityWrapper<>();
        wrapper.eq("time",aqi.getTime());
        wrapper.eq("city",aqi.getCity());
        int i = selectCount(wrapper);
        if(i>0) {
            return false;
        }else {
            boolean insert = insert(aqi);
            return insert;
        }
    }
}