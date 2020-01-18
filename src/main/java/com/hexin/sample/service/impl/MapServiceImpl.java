package com.hexin.sample.service.impl;

import com.hexin.sample.service.IMapService;
import com.hexin.sample.tool.HttpUtils2;
import com.hexin.sample.tool.UnicodeUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/1/18 14:00
 * @Version 1.0
 */
@Service
public class MapServiceImpl implements IMapService {
    @Override
    public String getMap(String strategy) {
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
}
