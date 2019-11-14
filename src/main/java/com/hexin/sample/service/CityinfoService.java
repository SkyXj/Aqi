/**   
 * Copyright © 2019广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: CityinfoService.java 
 * @Package: com.hexin.sample.service
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.hexin.sample.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hexin.sample.entity.Cityinfo;

import java.util.List;

/**
* 描述： 服务实现层接口
* @author sky
* @date 2019-09-24 19:05:59
*/
public interface CityinfoService extends IService<Cityinfo> {
	 boolean insertCityinfo(Cityinfo cityinfo);

	 String getCityIdByName(String name);

	 List<Cityinfo> selectAll();

//	 Cityinfo selectOne();
}