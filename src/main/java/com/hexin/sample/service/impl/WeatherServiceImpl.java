/**   
 * Copyright © 2018广州禾信仪器股份有限公司. All rights reserved.
 * 
 * @Title: WeatherServiceImpl.java 
 * @Package: com.hexin.sample.service.impl
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */


package com.hexin.sample.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hexin.sample.common.Citys;
import com.hexin.sample.entity.*;
import com.hexin.sample.mapper.WeatherMapper;
import com.hexin.sample.service.*;
import com.hexin.sample.tool.Decode;
import com.hexin.sample.tool.HttpUtils2;
import com.hexin.sample.tool.TimeTool;
import com.hexin.sample.tool.UnicodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
* 描述： 服务实现层
* @author sky
* @date 2019-09-24 19:05:59
*/
@Service
@Slf4j
public class WeatherServiceImpl extends ServiceImpl<WeatherMapper, Weather> implements WeatherService{

    final static Decode decode=new Decode();

    @Value("${weather.hb.url}")
    private String url;

    @Autowired
    AqiService aqiService;

    @Autowired
    CityinfoService cityinfoService;

//    @Autowired
//    WeatherService weatherService;

    @Autowired
    PointService pointService;

    @Autowired
    InfluxDBService influxDBService;

    @Override
    public void insertData() {
        long startTime=System.currentTimeMillis();
//        String[] citys_ABCDEF={"阿坝州","安康","阿克苏地区","阿里地区","阿拉善盟","阿勒泰地区","安庆","鞍山","安顺","阿图什","安阳","北京","亳州","蚌埠","白城","保定","北海","宝鸡","毕节","博乐","百色","保山","白山","包头","本溪","巴彦","白银","巴中","滨州","重庆","成都","长沙","长春","承德","常德","昌都","赤峰","昌吉","昌吉","常熟","楚雄","朝阳","崇左","滁州","池州","沧州","长治","常州","潮州","郴州","丹东","东莞","德宏","大理","大连","大庆","大同","定西","大兴安岭","东营","都匀","德阳","德州","达州","鄂尔多斯","恩施","鄂州","福州","富阳","防城港","佛山","抚顺","阜新","阜阳","抚州"};
//        String[] citys_GHIJK={"广州","贵阳","广安","贵港","果洛州","桂林","甘南州","固原","广元","甘孜州","赣州","杭州","合肥","淮安","鹤壁","淮北","海北州","河池","邯郸","海东地区","哈尔滨","黄冈","鹤岗","怀化","红河","黑河","呼和浩特","海口","呼伦贝尔","葫芦岛","海门","哈密","黄南","淮南","海南","黄石","衡水","黄山","和田","海西","河源","衡阳","惠州","贺州","湖州","汉中","菏泽","济南","吉安","金昌","晋城","景德镇","金华","景洪","九江","吉林","荆门","江门","即墨","佳木斯","济宁","胶南","酒泉","句容","吉首","金坛","鸡西","嘉兴","揭阳","江阴","嘉峪关","胶州","荆州","锦州","晋中","焦作","昆明","库尔勒","开封","凯里","克拉玛依","喀什","昆山"};
//        String[] citys_LMNOP={"拉萨","兰州","临安","六安","来宾","聊城","临沧","娄底","临汾","廊坊","漯河","丽江","吕梁","陇南","六盘水","丽水","凉山州","乐山","莱芜","临夏州","莱西","洛阳","龙岩","辽阳","临沂","溧阳","辽源","连云港","柳州","林芝","泸州","莱州","马鞍山","牡丹江","茂名","眉山","绵阳","梅州","南京","南昌","宁波","南充","宁德","内江","怒江","南宁","南平","那曲","南通","南阳","平度","平顶山","普洱","盘锦","平凉","蓬莱","莆田","萍乡","濮阳","攀枝花"};
//        String[] citys_QRSTU={"青岛","秦皇岛","曲靖","齐齐哈尔","七台河","黔西南州","庆阳","清远","衢州","钦州","泉州","荣成","日喀则","乳山","日照","上海","深圳","苏州","三亚","沈阳","苏州","寿光","韶关","绥化","石河子","石家庄","商洛","三明","三门峡","山南","遂宁","四平","宿迁","商丘","上饶","汕头","汕尾","绍兴","松原","邵阳","十堰","双鸭山","朔州","随州","宿州","石嘴山","天津","泰安","铜川","塔城","太仓","通化","铜陵","通辽","铁岭","吐鲁番","铜仁","天水","唐山","太原","泰州","台州"};
//        String[] citys_VWXYZ={"文登","潍坊","瓦房店","武汉","芜湖","乌海","威海","吴江","乌兰","乌鲁木齐","渭南","文山","武威","无锡","吴忠","温州","梧州","厦门","西安","兴安盟","宣城","许昌","襄樊","孝感","香格里拉","锡林浩特","西宁","咸宁","湘潭","邢台","新乡","咸阳","信阳","新余","徐州","忻州","伊春","玉林","延安","雅安","延边","宜宾","银川","盐城","运城","宜春","宜昌","云浮","阳江","营口","榆林","伊宁","阳泉","玉树","鹰潭","烟台","义乌","宜兴","玉溪","益阳","岳阳","永州","扬州","郑州","淄博","自贡","珠海","诸暨","镇江","湛江","张家港","张家界","张家口","周口","驻马店","肇庆","章丘","舟山","中山","昭通","中卫","资阳","张掖","招远","遵义","株洲","漳州","枣庄"};

        List<String[]> list=new ArrayList<>();
        list.add(Citys.CITY_ABCDEF());
        list.add(Citys.CITY_GHIJK());
        list.add(Citys.CITY_LMNOP());
        list.add(Citys.CITY_QRSTU());
        list.add(Citys.CITY_VWXYZ());
        try {
        final CountDownLatch latch = new CountDownLatch(list.size());
        for (String[] citys:list ) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                        for (String city: citys) {
                            insertByCity(city);
                        }
                        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                        latch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
            }.start();
        }
        latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error(TimeTool.Now()+" 导入数据失败,耗时："+(System.currentTimeMillis()-startTime)/1000+"s");
            log.error(TimeTool.Now()+e.getMessage());
        }
        long endTime=System.currentTimeMillis();
        log.info(TimeTool.Now()+" 导入数据成功,耗时："+(endTime-startTime)/1000+"s");
        //成功也放一条在error中
        log.error(TimeTool.Now()+" 导入数据成功,耗时："+(endTime-startTime)/1000+"s");
    }

    @Override
    public boolean insertWeather(Weather weather) {
        EntityWrapper<Weather> wrapper=new EntityWrapper<>();
        wrapper.eq("time",weather.getTime());
        wrapper.eq("cityid",weather.getCityid());
        int i = selectCount(wrapper);
        if(i>0) {
            return false;
        }else {
            boolean insert = insert(weather);
            return insert;
        }
    }

    @Override
    public void insertByCity(String cityname) {
        System.out.println("正在查询："+cityname);
        long startTime=System.currentTimeMillis();
        try {
            String param = decode.GetParam(cityname);
            String result=getServerData(param);
            //解密
            String miwen=decode.DecodeData(result);
            //json化
            HttpResult httpResult= JSON.parseObject(miwen,HttpResult.class);
            //存入mysql数据库
            if(httpResult==null||httpResult.getResult()==null){
                log.error(TimeTool.Now()+" "+cityname+" 导入数据失败;httpResult为空,疑似网络原因");
                return;
            }
            Data data = httpResult.getResult().getData();
            if(data ==null){
                return;
            }
            Cityinfo cityinfo=data.getCityinfo();
            String cityid="";
            if(cityinfo!=null&&cityinfo.getCityname()!=null){
                boolean b1 = cityinfoService.insertCityinfo(cityinfo);
            }else{
                cityinfo=new Cityinfo();
                cityinfo.setCityname(cityname);
                boolean b1 = cityinfoService.insertCityinfo(cityinfo);
            }
            cityid=cityinfo.getCityid();

            Aqi aqi = data.getAqi();
            if(aqi!=null&&aqi.getTime()!=null){
                influxDBService.insertAqi(aqi,cityid);
//                boolean b = aqiService.insetAqi(aqi);
            }

            Weather weather=data.getWeather();
            weather.setCityid(cityinfo==null?null:cityinfo.getCityid());
            if(weather!=null&&weather.getWeather()!=null&&weather.getCityid()!=null){
                influxDBService.insertWeather(weather);
//                boolean b2 = insertWeather(weather);
            }
            List<Row> row=data.getRows();
            if(row!=null&&row.size()>0){
                List<Point> points = new Point().getPoints(row);
                //插入国控点
                pointService.insertPoints(points);
                //存入时序数据库
                influxDBService.insertRows(row,cityid);
            }
        }catch (Exception e){
            log.error(TimeTool.Now()+" "+cityname+" 导入数据失败");
            e.printStackTrace();
        }
    }

    @Override
    public void test() {
        influxDBService.insertRow();
    }

//    public Data getEachHourData(){
//
//    }


    public String getServerData(String param){
        try {
            Data data=new Data();
            Map<String,String> map=new HashMap<>();
            map.put("d",param);
            Connection.Response post = HttpUtils2.post(url, map);
            String result = UnicodeUtils.decodeUnicode(IOUtils.toString(post.bodyStream(), StandardCharsets.UTF_8));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(TimeTool.Now()+e.getMessage());
            return "";
        }
    }


}