package com.hexin.sample.service;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/1/18 14:00
 * @Version 1.0
 */
public interface IMapService {
    String getMap(Integer strategy);

    void insert(Integer strategy);

    void inserts();
}
