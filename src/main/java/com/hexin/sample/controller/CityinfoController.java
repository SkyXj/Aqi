/**   
 * Copyright © 2019广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: CityinfoController.java 
 * @Package: com.hexin.sample.web.api
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.hexin.sample.controller;

import com.hexin.sample.entity.Cityinfo;
import com.hexin.sample.service.CityinfoService;
import com.hexin.sample.tool.HttpUtils2;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/cityinfo")
public class CityinfoController{
	
	@Autowired
	CityinfoService cityinfoService; 

	@GetMapping(value="/getimg",produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getimg(){
		try {
			Connection.Response response = HttpUtils2.get("http://www.google.cn/maps/vt?lyrs=s%40800&x=53419&y=28447&z=16");
			BufferedInputStream bufferedInputStream = response.bodyStream();
			byte[] bytes = readStream(bufferedInputStream);
			return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.FAILED_DEPENDENCY);
	}

	public static byte[] readStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while((len = inStream.read(buffer)) != -1){
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

	@GetMapping(value="/test1")
	public List<Cityinfo> selectAll(){
		List<Cityinfo> cityinfos = cityinfoService.selectAll();
		return cityinfos;
	}

	@GetMapping(value="/one")
	public Cityinfo selectOne(){
		Cityinfo cityinfo = cityinfoService.selectOne();
		return cityinfo;
	}

}