package com.hexin.sample.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hexin.sample.entity.Line;
import com.hexin.sample.service.ILineService;
import com.hexin.sample.service.IMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/1/18 13:57
 * @Version 1.0
 */
@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    IMapService mapService;

    @Autowired
    ILineService lineService;

    @GetMapping("/getMap")
    public List<String> getMap(Integer strategy){
        List<String> result=new ArrayList<>();
        String map = mapService.getMap(strategy);
        JSONObject jsonObject = JSONObject.parseObject(map);
        JSONObject route = jsonObject.getJSONObject("route");
        JSONArray paths = route.getJSONArray("paths");
        for (int i = 0; i < paths.size(); i++) {
            JSONObject path = paths.getJSONObject(i);
//            耗时
            int duration = Integer.parseInt(path.getString("duration"));
            int hours=duration/3600;
            int minutes=duration%3600/60;

            JSONArray steps = path.getJSONArray("steps");
//            高速路线
            Set<String> set=new LinkedHashSet<>();
            for (int j = 0; j < steps.size(); j++) {
                set.add(steps.getJSONObject(j).getString("toll_road"));
            }
            Iterator<String> iterator = set.iterator();
            String road_details="";
            while (iterator.hasNext()) {
                String next = iterator.next();
                road_details+=next+"->";
            }
            road_details.substring(0,road_details.length()-2);
            result.add(path.getString("strategy")+":"+hours+"小时"+minutes+"分;路线："+road_details);

            Line line=new Line();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            line.setRoad(road_details);
            line.setStrategy(strategy);
            line.setCreatedTime(simpleDateFormat.format(new Date()));
            line.setTime(hours+"小时"+minutes+"分");
            line.setDuration(duration);
            lineService.insert(line);
        }
        return result;
    }
}
