package com.hexin.sample.tool;

import org.springframework.core.io.ClassPathResource;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

public class Decode {
//    @Value("${js.path}")
    private String jspath="../js/decode.js";

    private ScriptEngine engine=null;

    private Invocable invocable = null;

    public Decode() {
//        String scriptResult ="";//脚本的执行结果
        engine = new ScriptEngineManager().getEngineByName("nashorn");//1.得到脚本引擎
        try {
            //2.引擎读取 脚本字符串
            //engine.eval(new StringReader(routeScript));
            //如果js存在文件里，举例

            File file=new File(jspath);
//            ClassPathResource aesJs = new ClassPathResource(jspath);
//            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(jspath);
//            Resource aesJs = new ClassPathResource(jspath,this.getClass().getClassLoader());
//            engine.eval(new FileReader(file));
            //engine.eval(new FileReader(aesJs.getFile()));
            engine.eval(new FileReader(file));
            //3.将引擎转换为Invocable，这样才可以掉用js的方法
            invocable = (Invocable) engine;

//            //4.使用 invocable.invokeFunction掉用js脚本里的方法，第一個参数为方法名，后面的参数为被调用的js方法的入参
//            scriptResult = (String) invocable.invokeFunction("getParamxj", "");

        }catch(ScriptException e) {
            e.printStackTrace();
            System.out.println("Error executing script: " + e.getMessage() + " script:[]");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String GetParam(String cityname){
        String result= null;
        try {
            result = (String) invocable.invokeFunction("getParamxj", cityname);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String DecodeData(String param){
        String result= null;
        try {
            result = (String) invocable.invokeFunction("decodeData", param);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getAQIParam(String cityname,String type,String starttime,String endtime){
        String result= null;
        try {
            result = (String) invocable.invokeFunction("getParamAqi", cityname,type,starttime,endtime);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }
//    @Bean
//    public Decode getDecode(){
//        return new Decode();
//    }
}
