package com.hexin.sample.tool;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.nio.charset.Charset;

public class HttpUtils3 {
    public static void main(String[] args) throws IOException {
        String data="tdgHOYxwKdDSgYXe+RLPzYCgLvrddahasI5XXklB4gVLYqab+XRPpMD/oSqnJ/aEmFwzVEUhLnPzRy03+X1BI/BUwKBrNY/t1D8mLMxTQ1Hl5vRaVVbZRylCp1+a+S0DAAaxN2JOhUZrsDSupaj3nhNNTwFBnZcvdWoywkiRHE4OHCW/ilSv3VOWsIhvrZgvlhrz0+BZJJzrI0jz/nzmBraUouwYdtYxpII0UYb5rJsCbJEv0G0djSO46hj1n+hrwiXPqvseR/uH1dv6m0eH46z975s9Y/EwD7GJs3lOTjB5LDrsJ81JG4agxu/jXmIyeo0OBKzbhGDbRKKva73oAg==";
        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, "------------------------------0ea3fcae38ff", Charset.defaultCharset());
        multipartEntity.addPart("d", new StringBody(data, Charset.forName("UTF-8")));
//        multipartEntity.addPart("operator", new StringBody("啦啦啦啦", Charset.forName("UTF-8")));
//        multipartEntity.addPart("VrfKey", new StringBody("渣渣渣", Charset.forName("UTF-8")));
//        multipartEntity.addPart("StatCode", new StringBody("00", Charset.forName("UTF-8")));
//        multipartEntity.addPart("mass_id", new StringBody("1234", Charset.forName("UTF-8")));
//        multipartEntity.addPart("reference_id", new StringBody("21231544", Charset.forName("UTF-8")));
        String url="https://www.aqistudy.cn/apinew/aqistudyapi.php";
        HttpPost request = new HttpPost(url);
        request.setEntity(multipartEntity);
        String BOUNDARY = "----------" + System.currentTimeMillis();
        request.addHeader("Content-Type", "multipart/form-data; boundary="+BOUNDARY);

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);

        InputStream is = response.getEntity().getContent();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null)
        {
            buffer.append(line);
        }

        System.out.println("发送消息收到的返回：" + buffer.toString());
    }
}
