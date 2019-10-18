/**   
 * Copyright © 2019广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: PointService.java 
 * @Package: com.hexin.sample.service
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.hexin.sample.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.hexin.sample.entity.Point;
import com.baomidou.mybatisplus.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* 描述： 服务实现层接口
* @author sky
* @date 2019-09-24 19:05:59
*/
public interface PointService extends IService<Point> {
	boolean insertPoint(Point point);

	int insertPoints(List<Point> points);

	void excelExport(HttpServletResponse response,String startTime, String endTime, String pointname,String cityname);

	Page<Point> list(Integer pagenum,Integer pagesize,String cityname,String pointname);
}