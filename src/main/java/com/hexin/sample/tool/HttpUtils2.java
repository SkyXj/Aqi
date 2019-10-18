package com.hexin.sample.tool;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * Http发送post请求工具，兼容http和https两种请求类型
 */
public class HttpUtils2 {

    private static String[] citys_ABCDEF={"阿坝","安康","阿克苏","阿里","阿拉善盟","阿勒泰","安庆","鞍山","安顺","阿图什","安阳","北京","亳州","蚌埠","白城","保定","北海","宝鸡","毕节","博乐","百色","保山","白山","包头","本溪","巴彦","白银","巴中","滨州","重庆","成都","长沙","长春","承德","常德","昌都","赤峰","昌吉","昌吉","常熟","楚雄","朝阳","崇左","滁州","池州","沧州","长治","常州","潮州","郴州","丹东","东莞","德宏","大理","大连","大庆","大同","定西","大兴安岭","东营","都匀","德阳","德州","达州","鄂尔多斯","恩施","鄂州","福州","富阳","防城港","佛山","抚顺","阜新","阜阳","抚州"};
    private static String[] citys_GHIJK={"广州","贵阳","广安","贵港","果洛","桂林","甘南","固原","广元","甘孜","赣州","杭州","合肥","淮安","鹤壁","淮北","海北","河池","邯郸","海东","哈尔滨","黄冈","鹤岗","怀化","红河","黑河","呼和浩特","海口","呼伦贝尔","葫芦岛","海门","哈密","黄南","淮南","海南","黄石","衡水","黄山","和田","海西","河源","衡阳","惠州","贺州","湖州","汉中","菏泽","济南","吉安","金昌","晋城","景德镇","金华","景洪","九江","吉林","荆门","江门","即墨","佳木斯","济宁","胶南","酒泉","句容","吉首","金坛","鸡西","嘉兴","揭阳","江阴","嘉峪关","胶州","荆州","锦州","晋中","焦作","昆明","库尔勒","开封","凯里","克拉玛依","喀什","昆山"};
    private static String[] citys_LMNOP={"拉萨","兰州","临安","六安","来宾","聊城","临沧","娄底","临汾","廊坊","漯河","丽江","吕梁","陇南","六盘水","丽水","凉山","乐山","莱芜","临夏","莱西","洛阳","龙岩","辽阳","临沂","溧阳","辽源","连云港","柳州","林芝","泸州","莱州","马鞍山","牡丹江","茂名","眉山","绵阳","梅州","南京","南昌","宁波","南充","宁德","内江","怒江","南宁","南平","那曲","南通","南阳","平度","平顶山","普洱","盘锦","平凉","蓬莱","莆田","萍乡","濮阳","攀枝花"};
    private static String[] citys_QRSTU={"青岛","秦皇岛","曲靖","齐齐哈尔","七台河","黔西","庆阳","清远","衢州","钦州","泉州","荣成","日喀则","乳山","日照","上海","深圳","苏州","三亚","沈阳","苏州","寿光","韶关","绥化","石河子","石家庄","商洛","三明","三门峡","山南","遂宁","四平","宿迁","商丘","上饶","汕头","汕尾","绍兴","松原","邵阳","十堰","双鸭山","朔州","随州","宿州","石嘴山","天津","泰安","铜川","塔城","太仓","通化","铜陵","通辽","铁岭","吐鲁番","铜仁","天水","唐山","太原","泰州","台州"};
    private static String[] citys_VWXYZ={"文登","潍坊","瓦房店","武汉","芜湖","乌海","威海","吴江","乌兰","乌鲁木齐","渭南","文山","武威","无锡","吴忠","温州","梧州","厦门","西安","兴安","宣城","许昌","襄樊","孝感","香格里拉","锡林浩特","西宁","咸宁","湘潭","邢台","新乡","咸阳","信阳","新余","徐州","忻州","伊春","玉林","延安","雅安","延边","宜宾","银川","盐城","运城","宜春","宜昌","云浮","阳江","营口","榆林","伊宁","阳泉","玉树","鹰潭","烟台","义乌","宜兴","玉溪","益阳","岳阳","永州","扬州","郑州","淄博","自贡","珠海","诸暨","镇江","湛江","张家港","张家界","张家口","周口","驻马店","肇庆","章丘","舟山","中山","昭通","中卫","资阳","张掖","招远","遵义","株洲","漳州","枣庄"};

    public static void main(String[] args) throws IOException {

        int size=citys_ABCDEF.length+citys_GHIJK.length+citys_LMNOP.length+citys_QRSTU.length+citys_VWXYZ.length;
        System.out.println(size);
//
//        String[] citys=new String[]{};
//        citys=(String[]) ArrayUtils.addAll(citys_ABCDEF,citys_GHIJK);
//        System.out.println(citys.length);
//        citys=(String[]) ArrayUtils.addAll(citys,citys_LMNOP);
//        System.out.println(citys.length);
//        citys=(String[]) ArrayUtils.addAll(citys,citys_QRSTU);
//        System.out.println(citys.length);
//        citys=(String[]) ArrayUtils.addAll(citys,citys_VWXYZ);
//        System.out.println(citys.length);


//        long startTime=System.currentTimeMillis();//获得当前时间
//        int sum=0;
//        for(int i=0;i<121212;i++) {
//            sum+=i;
//        }
//        long endTime=System.currentTimeMillis();//获得当前时间
//        System.out.println(endTime-startTime);

//        String data="tdgHOYxwKdDSgYXe+RLPzYCgLvrddahasI5XXklB4gVLYqab+XRPpMD/oSqnJ/aEmFwzVEUhLnPzRy03+X1BI/BUwKBrNY/t1D8mLMxTQ1Hl5vRaVVbZRylCp1+a+S0DAAaxN2JOhUZrsDSupaj3nhNNTwFBnZcvdWoywkiRHE4OHCW/ilSv3VOWsIhvrZgvlhrz0+BZJJzrI0jz/nzmBraUouwYdtYxpII0UYb5rJsCbJEv0G0djSO46hj1n+hrwiXPqvseR/uH1dv6m0eH46z975s9Y/EwD7GJs3lOTjB5LDrsJ81JG4agxu/jXmIyeo0OBKzbhGDbRKKva73oAg==";
//        String url="https://www.aqistudy.cn/apinew/aqistudyapi.php";
//        Map<String, String> paramMap=new HashMap<>();
//        paramMap.put("d",data);
//        Response post = post(url, paramMap);
//        System.out.println(IOUtils.toString(post.bodyStream(), StandardCharsets.UTF_8));
    }

    /**
     * 请求超时时间
     */
    private static final int TIME_OUT = 120000;

    /**
     * Https请求
     */
    private static final String HTTPS = "https";

    /**
     * Content-Type
     */
    private static final String CONTENT_TYPE = "Content-Type";

    /**
     * 表单提交方式Content-Type
     */
    private static final String FORM_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";
    //private static final String FORM_TYPE = "multipart/form-data; boundary=--------------------------876759659616103528890601";

    /**
     * JSON提交方式Content-Type
     */
    private static final String JSON_TYPE = "application/json;charset=UTF-8";

    /**
     * 发送Get请求
     *
     * @param url 请求URL
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * 发送Get请求
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response get(String url, Map<String, String> headers) throws IOException {
        if (null == url || url.isEmpty()) {
            throw new RuntimeException("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }
        Connection connection = Jsoup.connect(url);
        connection.method(Method.GET);
        connection.timeout(TIME_OUT);
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);
        connection.maxBodySize(0);

        if (null != headers) {
            connection.headers(headers);
        }

        Response response = connection.execute();
        return response;
    }

    /**
     * 发送JSON格式参数POST请求
     *
     * @param url 请求路径
     * @param params JSON格式请求参数
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, String params) throws IOException {
        return doPostRequest(url, null, params);
    }

    /**
     * 发送JSON格式参数POST请求
     *
     * @param url 请求路径
     * @param headers 请求头中设置的参数
     * @param params JSON格式请求参数
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, Map<String, String> headers, String params) throws IOException {
        return doPostRequest(url, headers, params);
    }

    /**
     * 字符串参数post请求
     *
     * @param url 请求URL地址
     * @param paramMap 请求字符串参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, Map<String, String> paramMap) throws IOException {
        return doPostRequest(url, null, paramMap, null);
    }

    /**
     * 带请求头的普通表单提交方式post请求
     *
     * @param headers 请求头参数
     * @param url 请求URL地址
     * @param paramMap 请求字符串参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(Map<String, String> headers, String url, Map<String, String> paramMap) throws IOException {
        return doPostRequest(url, headers, paramMap, null);
    }

    /**
     * 带上传文件的post请求
     *
     * @param url 请求URL地址
     * @param paramMap 请求字符串参数集合
     * @param fileMap 请求文件参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, Map<String, String> paramMap, Map<String, File> fileMap) throws IOException {
        return doPostRequest(url, null, paramMap, fileMap);
    }

    /**
     * 带请求头的上传文件post请求
     *
     * @param url 请求URL地址
     * @param headers 请求头参数
     * @param paramMap 请求字符串参数集合
     * @param fileMap 请求文件参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, Map<String, String> headers, Map<String, String> paramMap, Map<String, File> fileMap) throws IOException {
        return doPostRequest(url, headers, paramMap, fileMap);
    }

    /**
     * 执行post请求
     *
     * @param url 请求URL地址
     * @param headers 请求头
     * @param jsonParams 请求JSON格式字符串参数
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    private static Response doPostRequest(String url, Map<String, String> headers, String jsonParams) throws IOException {
        if (null == url || url.isEmpty()) {
            throw new RuntimeException("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }

        Connection connection = Jsoup.connect(url);
        connection.method(Method.POST);
        connection.timeout(TIME_OUT);
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);
        connection.maxBodySize(0);

        if (null != headers) {
            connection.headers(headers);
        }

        connection.header(CONTENT_TYPE, JSON_TYPE);
        connection.requestBody(jsonParams);

        Response response = connection.execute();
        return response;
    }

    /**
     * 普通表单方式发送POST请求
     *
     * @param url 请求URL地址
     * @param headers 请求头
     * @param paramMap 请求字符串参数集合
     * @param fileMap 请求文件参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    private static Response doPostRequest(String url, Map<String, String> headers, Map<String, String> paramMap, Map<String, File> fileMap) throws IOException {
        if (null == url || url.isEmpty()) {
            throw new RuntimeException("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }

        Connection connection = Jsoup.connect(url);
        connection.method(Method.POST);
        connection.timeout(TIME_OUT);
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);
        connection.maxBodySize(0);

        if (null != headers) {
            connection.headers(headers);
        }

        // 收集上传文件输入流，最终全部关闭
        List<InputStream> inputStreamList = null;
        try {

            // 添加文件参数
            if (null != fileMap && !fileMap.isEmpty()) {
                inputStreamList = new ArrayList<InputStream>();
                InputStream in = null;
                File file = null;
                for (Entry<String, File> e : fileMap.entrySet()) {
                    file = e.getValue();
                    in = new FileInputStream(file);
                    inputStreamList.add(in);
                    connection.data(e.getKey(), file.getName(), in);
                }
            }

            // 普通表单提交方式
            else {
                connection.header(CONTENT_TYPE, FORM_TYPE);
            }

            // 添加字符串类参数
            if (null != paramMap && !paramMap.isEmpty()) {
                connection.data(paramMap);
            }

            Response response = connection.execute();
            return response;
        }

        // 关闭上传文件的输入流
        finally {
            closeStream(inputStreamList);
        }
    }

    /**
     * 关流
     *
     * @param streamList 流集合
     */
    private static void closeStream(List<? extends Closeable> streamList) {
        if (null != streamList) {
            for (Closeable stream : streamList) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取服务器信任
     */
    private static void getTrust() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
