package com.hexin.sample.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hexin.sample.common.Strategys;
import com.hexin.sample.entity.Line;
import com.hexin.sample.service.ILineService;
import com.hexin.sample.service.IMapService;
import com.hexin.sample.tool.HttpUtils2;
import com.hexin.sample.tool.UnicodeUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/1/18 14:00
 * @Version 1.0
 */
@Service
public class MapServiceImpl implements IMapService {

    @Autowired
    IMapService mapService;

    @Autowired
    ILineService lineService;

    @Override
    public String getMap(Integer strategy) {
        String start="113.5036071400,23.1570559600";
        String end="115.9859523800,30.1096354800";
        //高德的key
        String gaodeKey="3957c071a0043a7f95eab3ada3415580";
        String url="https://restapi.amap.com/v3/direction/driving?origin="+start+"&destination="+end+"&strategy="+strategy+"&extensions=base&key="+gaodeKey;
        try {
            Connection.Response response = HttpUtils2.get(url);
            String result = UnicodeUtils.decodeUnicode(IOUtils.toString(response.bodyStream(), StandardCharsets.UTF_8));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void inserts() {
        Map<Integer, String> strategyMap = Strategys.getStrategyMap();
        //策略
        for (Integer strategy:strategyMap.keySet()) {
            insert(strategy);
        }
    }

    @Override
    public void insert(Integer strategy) {
//        List<String> result=new ArrayList<>();
        String map = mapService.getMap(strategy);
        JSONObject jsonObject = JSONObject.parseObject(map);
        JSONObject route = jsonObject.getJSONObject("route");
        JSONArray paths = route.getJSONArray("paths");
        for (int i = 0; i < paths.size(); i++) {
            JSONObject path = paths.getJSONObject(i);
            //距离千米
            int distance = Integer.parseInt(path.getString("distance"))/1000;
            //过路费
            String tolls=path.getString("tolls");
            //策略说明
            String description=path.getString("strategy");
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
//            result.add(path.getString("strategy")+":"+hours+"小时"+minutes+"分;路线："+road_details);

            Line line=new Line();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            line.setRoad(road_details);
            line.setStrategy(strategy);
            line.setCreatedTime(simpleDateFormat.format(new Date()));
            line.setTime(hours+"小时"+minutes+"分");
            line.setDuration(duration);
            line.setDistance(distance);
            line.setTolls(tolls);
            line.setDescription(description);
            lineService.insert(line);
        }

    }
}
