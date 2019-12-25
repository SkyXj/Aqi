package com.hexin.sample.fiter;

import com.alibaba.fastjson.JSON;
import com.hexin.sample.annotation.AccessLimit;
import com.hexin.sample.model.common.Constant;
import com.hexin.sample.util.JedisUtil;
import com.hexin.sample.util.JwtUtil;
import com.hexin.sample.util.common.IpUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2019/12/25 10:49
 * @Version 1.0
 */
@Service
public class AccessInterceptor  extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(handler instanceof HandlerMethod) {
//            //token
//            String token = (String) SecurityUtils.getSubject().getPrincipal();
//            //用户名
//            String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
            String ipAddr = IpUtils.getIpAddr(request);
            HandlerMethod hm = (HandlerMethod)handler;
            //请求的方法是否带有accesslimit注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null) {
                return true;
            }
            //如果有判断每请求一次缓存中当前用户key的count+1.直到最大限制禁止访问此接口
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String url = request.getRequestURI();
            if(needLogin) {
                //token
                String token = (String) SecurityUtils.getSubject().getPrincipal();
                //用户名
                String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
                if(token == null) {
                    render(response,"尚未登录" );
                    return false;
                }
            }
            String key =url+ "_" + ipAddr;
            //key
            String time_key=key+"_time";
            if(JedisUtil.exists(key)&&JedisUtil.exists(time_key)){
                int count=Integer.parseInt(JedisUtil.getObject(key).toString());
                Long lasttime=Long.parseLong(JedisUtil.getObject(time_key).toString());
                Long nowtime=System.currentTimeMillis();
                double times=count*1.00/(nowtime-lasttime);
                double times_max=maxCount*1.00/seconds;
                if(nowtime-lasttime>=(seconds/maxCount*1.00)){
                    JedisUtil.setObject(key,count+1);
                    JedisUtil.setObject(time_key,System.currentTimeMillis());
                    return true;
                }else{
                    render(response,"请求次数过于频繁,请稍后再试");
                    return false;
                }
            }else {
                JedisUtil.setObject(key,1);
                JedisUtil.setObject(time_key,System.currentTimeMillis());
                return true;
            }
        }
        return true;
    }
    //返回前台
    private void render(HttpServletResponse response, String msg)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString(msg);
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }


}
