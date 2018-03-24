package util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtraInfoUtil {

    public static Map<String, String> getInfo (String str) {
        Map<String, String> hashMap = new HashMap<>();
        Pattern pattern = Pattern.compile("<li>〉〉(.*?) 学生课表&gt;&gt;(.*?) </li>");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            hashMap.put("term", matcher.group(1));
            hashMap.put("stuNum", matcher.group(2));
        }
        return hashMap;
    }
}
