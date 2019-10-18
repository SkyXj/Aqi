/**
 * Copyright © 2018广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Title: InfluxDbConfig.java
 * @Package: com.hexin.sample.config
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description :
 * @Modify Person :
 * @version: V1.0
 */

package com.hexin.sample.config;

import com.hexin.sample.tool.InfluxDbUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class InfluxDbConfig {

    @Value("${spring.influx.url:''}")
    private String influxDBUrl;

    @Value("${spring.influx.user:''}")
    private String userName;

    @Value("${spring.influx.password:''}")
    private String password;

    @Value("${spring.influx.database:''}")
    private String database;

    @Bean
    public InfluxDbUtils influxDbUtils() {
        return new InfluxDbUtils(userName, password, influxDBUrl, database, "");
    }
}
