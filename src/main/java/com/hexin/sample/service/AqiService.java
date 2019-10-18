/**   
 * Copyright © 2019广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: AqiService.java 
 * @Package: com.hexin.sample.service
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.hexin.sample.service;


import com.hexin.sample.entity.Aqi;
import com.baomidou.mybatisplus.service.IService;
/**
* 描述： 服务实现层接口
* @author sky
* @date 2019-09-24 19:05:59
*/
public interface AqiService extends IService<Aqi> {
	boolean insetAqi(Aqi aqi);
	
	//Page<Aqi> listAqi(Integer pageNumber, Integer pageSize,String keyword);
}