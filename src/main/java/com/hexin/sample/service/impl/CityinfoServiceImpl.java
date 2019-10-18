package com.hexin.sample.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hexin.sample.entity.Cityinfo;
import com.hexin.sample.mapper.CityinfoMapper;
import com.hexin.sample.service.CityinfoService;
import org.springframework.stereotype.Service;

/**
* 描述： 服务实现层
* @author sky
* @date 2019-09-24 19:05:59
*/
@Service
public class CityinfoServiceImpl extends ServiceImpl<CityinfoMapper, Cityinfo> implements CityinfoService{
    @Override
    public boolean insertCityinfo(Cityinfo cityinfo) {
        EntityWrapper<Cityinfo> wrapper=new EntityWrapper<>();
//        wrapper.eq("cityid",cityinfo.getCityid());
//        wrapper.eq("citygid",cityinfo.getCitygid());
        wrapper.eq("cityname",cityinfo.getCityname());
        int i = selectCount(wrapper);
        if(i>0) {
            return false;
        }else {
            boolean insert = insert(cityinfo);
            return insert;
        }
    }

    @Override
    public String getCityIdByName(String name) {
        EntityWrapper<Cityinfo> wrapper=new EntityWrapper<>();
        wrapper.eq("cityname",name);
        Cityinfo cityinfo = selectOne(wrapper);
        if(cityinfo!=null){
            return cityinfo.getCityid();
        }else{
            return null;
        }
    }
}