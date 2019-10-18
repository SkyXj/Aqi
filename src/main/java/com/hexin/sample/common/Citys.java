package com.hexin.sample.common;

import org.apache.commons.lang3.ArrayUtils;

public class Citys {
    private static final String[] CITY_A={"阿坝州","阿克苏地区","阿拉善盟","阿勒泰地区","阿里地区","安康","安庆","安顺","安阳","鞍山"};
    private static final String[] CITY_B={"巴彦淖尔","巴中","白城","白山","白银","百色","蚌埠","包头","宝鸡","保定","保山","北海","北京","本溪","毕节","滨州","博州"};
    private static final String[] CITY_C={"沧州","昌都","昌吉州","长春","长沙","长治","常德","常熟","常州","朝阳","潮州","郴州","成都","承德","池州","赤峰","崇左","滁州","楚雄州"};
    private static final String[] CITY_D={"达州","大理州","大连","大庆","大同","大兴安岭地区","丹东","德宏州","德阳","德州","定西","东莞","东营"};
    private static final String[] CITY_E={"鄂尔多斯","鄂州","恩施州"};
    private static final String[] CITY_F={"防城港","佛山","抚顺","抚州","阜新","阜阳","福州","富阳"};
    private static final String[] CITY_G={"甘南州","甘孜州","赣州","固原","广安","广元","广州","贵港","贵阳","桂林","果洛州"};
    private static final String[] CITY_H={"哈尔滨","哈密地区","海北州","海东地区","海口","海门","海南州","海西州","邯郸","汉中","杭州","合肥","和田地区","河池","河源","菏泽","贺州","鹤壁","鹤岗","黑河","衡水","衡阳","红河州","呼和浩特","呼伦贝尔","湖州","葫芦岛","怀化","淮安","淮北","淮南","黄冈","黄南州","黄山","黄石","惠州"};
    private static final String[] CITY_J={"鸡西","吉安","吉林","即墨","济南","济宁","佳木斯","嘉兴","嘉峪关","江门","江阴","胶南","胶州","焦作","揭阳","金昌","金华","金坛","锦州","晋城","晋中","荆门","荆州","景德镇","九江","酒泉","句容"};
    private static final String[] CITY_K={"喀什地区","开封","克拉玛依","库尔勒","昆明","昆山"};
    private static final String[] CITY_L={"拉萨","来宾","莱芜","莱西","莱州","兰州","廊坊","乐山","丽江","丽水","溧阳","连云港","凉山州","辽阳","辽源","聊城","林芝","临安","临沧","临汾","临夏州","临沂","柳州","六安","六盘水","龙岩","陇南","娄底","泸州","吕梁","洛阳","漯河"};
    private static final String[] CITY_M={"马鞍山","茂名","眉山","梅州","绵阳","牡丹江"};
    private static final String[] CITY_N={"内江","那曲地区","南昌","南充","南京","南宁","南平","南通","南阳","宁波","宁德","怒江州"};
    private static final String[] CITY_P={"攀枝花","盘锦","蓬莱","平顶山","平度","平凉","萍乡","莆田","濮阳"};
    private static final String[] CITY_Q={"七台河","齐齐哈尔","黔西南州","钦州","秦皇岛","青岛","清远","庆阳","曲靖","衢州","泉州"};
    private static final String[] CITY_R={"日喀则","日照","荣成","乳山"};
    private static final String[] CITY_S={"三门峡","三明","三亚","山南","汕头","汕尾","商洛","商丘","上海","上饶","韶关","邵阳","绍兴","深圳","沈阳","十堰","石河子","石家庄","石嘴山","寿光","双鸭山","朔州","四平","松原","宿迁","宿州","绥化","随州","遂宁"};
    private static final String[] CITY_T={"塔城地区","台州","太仓","太原","泰安","泰州","唐山","天津","天水","铁岭","通化","通辽","铜川","铜陵","铜仁地区","吐鲁番地区"};
    private static final String[] CITY_W={"瓦房店","威海","潍坊","渭南","温州","文登","文山州","乌海","乌兰察布","乌鲁木齐","无锡","吴江","吴忠","芜湖","梧州","武汉","武威"};
    private static final String[] CITY_X={"西安","西宁","锡林郭勒盟","厦门","咸宁","咸阳","湘潭","襄阳","孝感","忻州","新乡","新余","信阳","兴安盟","邢台","徐州","许昌","宣城","伊春","玉林"};
    private static final String[] CITY_Y={"雅安","烟台","延安","延边州","盐城","扬州","阳江","阳泉","伊犁哈萨克州","宜宾","宜昌","宜春","宜兴","义乌","益阳","银川","鹰潭","营口","永州","榆林","玉树州","玉溪","岳阳","云浮","运城"};
    private static final String[] CITY_Z={"枣庄","湛江","张家港","张家界","张家口","张掖","章丘","漳州","招远","昭通","肇庆","镇江","郑州","中山","中卫","舟山","周口","株洲","珠海","诸暨","驻马店","资阳","淄博","自贡","遵义"};


    public static String[] CITY_ABCDEF(){
        return UnionArrays(CITY_A,CITY_B,CITY_C,CITY_D,CITY_E,CITY_F);
    }

    public static String[] CITY_GHIJK(){
        return UnionArrays(CITY_G,CITY_H,CITY_J,CITY_K);
    }

    public static String[] CITY_LMNOP(){
        return UnionArrays(CITY_L,CITY_M,CITY_N,CITY_P);
    }

    public static String[] CITY_QRSTU(){
        return UnionArrays(CITY_Q,CITY_R,CITY_S,CITY_T);
    }
    public static String[] CITY_VWXYZ(){
        return UnionArrays(CITY_W,CITY_X,CITY_Y,CITY_Z);
    }

    public static  String[] UnionArrays(String[]... args){
        if(args.length==0){
            return new String[]{};
        }
        if(args.length==1){
            return args[0];
        }
        String[] result=args[0];
        for (int i = 1; i < args.length; i++) {
            result=(String[]) ArrayUtils.addAll(result,args[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(CITY_A.length+CITY_B.length+CITY_C.length+CITY_D.length+CITY_E.length+CITY_F.length);
        System.out.println(CITY_ABCDEF().length);
    }
}
