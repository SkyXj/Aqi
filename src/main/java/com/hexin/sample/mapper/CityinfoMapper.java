/**   
 * Copyright © 2019广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: CityinfoMapper.java 
 * @Package: com.hexin.sample.mapper
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.hexin.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hexin.sample.entity.Cityinfo;
import java.util.List;

public interface CityinfoMapper extends BaseMapper<Cityinfo> {
    List<Cityinfo> selectAll();
}