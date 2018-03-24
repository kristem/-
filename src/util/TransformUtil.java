package util;


public class TransformUtil {
    static String[] chNum = {"零","一","二","三","四","五","六","七","八","九","十","十一","十二"};

    public static String trans(int num){
        return chNum[num];
    }
}
