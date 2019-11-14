package com.hexin.sample.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hexin.sample.entity.Cityinfo;
import com.hexin.sample.mapper.CityinfoMapper;
import com.hexin.sample.service.CityinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 描述： 服务实现层
* @author sky
* @date 2019-09-24 19:05:59
*/
@Service
public class CityinfoServiceImpl extends ServiceImpl<CityinfoMapper, Cityinfo> implements CityinfoService{

    @Autowired
    CityinfoMapper cityinfoMapper;

    @Override
    public boolean insertCityinfo(Cityinfo cityinfo) {
        QueryWrapper<Cityinfo> wrapper=new QueryWrapper<>();
//        wrapper.eq("cityid",cityinfo.getCityid());
//        wrapper.eq("citygid",cityinfo.getCitygid());
        wrapper.eq("cityname",cityinfo.getCityname());
        int i = baseMapper.selectCount(wrapper);
        if(i>0) {
            return false;
        }else {
            int insert = baseMapper.insert(cityinfo);
            return insert>0;
        }
    }

    @Override
    public String getCityIdByName(String name) {
        QueryWrapper<Cityinfo> wrapper=new QueryWrapper<>();
        wrapper.eq("cityname",name);
        Cityinfo cityinfo = baseMapper.selectOne(wrapper);
        if(cityinfo!=null){
            return cityinfo.getCityid();
        }else{
            return null;
        }
    }

    @Override
    public List<Cityinfo> selectAll() {
        return cityinfoMapper.selectAll();
    }

//    @Override
//    public Cityinfo selectOne() {
//        return cityinfoMapper.selectOne(new QueryWrapper<>());
//    }
}