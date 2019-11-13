/**   
 * Copyright © 2019广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: PointController.java 
 * @Package: com.hexin.sample.web.api
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.hexin.sample.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hexin.sample.entity.Point;
import com.hexin.sample.service.PointService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/point")
public class PointController{
	
	@Autowired
	PointService pointService; 


	@GetMapping("/excel")
//	@RequiresPermissions(logical = Logical.AND, value = {"user:view"})
	public void excelExport(HttpServletResponse response,
							@RequestParam String startTime,
							@RequestParam String endTime,
							@RequestParam(required = false) String pointname,
							@RequestParam String cityname){
		pointService.excelExport(response,startTime,endTime,pointname,cityname);
	}

	@GetMapping("/list")
	public IPage<Point> list(@RequestParam Integer pagenum,
							   @RequestParam Integer pagesize,
							   @RequestParam(required = false) String cityname,
							   @RequestParam(required = false) String pointname){
		return pointService.list(pagenum,pagesize,cityname,pointname);
	}
}