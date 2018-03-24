package util;

import course.Course;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseMaker {

    public static JSONObject getList (String PageData) {
        JSONArray jsonArray = new JSONArray();

        int b_pos = PageData.indexOf("id=\"kbStuTabs-list\"");
        int e_pos = PageData.indexOf("id=\"kbStuTabs-ttk\"");
        String cutStr = PageData.substring(b_pos, e_pos).replace(" ", "");
        Pattern pattern_01 = Pattern.compile("<tr><tdrowspan.*?>.*?</td><tdrowspan.*?>(.*?)</td><tdrowspan.*?>(.*?)</td><tdrowspan.*?>.*?</td><tdrowspan.*?>.*?</td><td.*?>(.*?)</td><td.*?>(.*?)</td><td.*?>(.*?)</td><td.*?>.*?</td><td.*?>.*?</td>");
        Pattern pattern_02 = Pattern.compile("<tr><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td></tr>");
        Matcher matcher = pattern_01.matcher(cutStr);

        ArrayList<String> courses = new ArrayList<>();

        int lastIndex = 0, beginIndex = 0;
        Course lastCourse = null;

        boolean firstFlag = true;

        while (matcher.find() ) {
            if(matcher.group(1).charAt(0) < 'A' || matcher.group(1).charAt(0) > 'Z' ) {
                continue;
            }

            Course course = new Course(matcher.group(1), matcher.group(2),
                    matcher.group(3),matcher.group(4),matcher.group(5));

            courses.add(JSONObject.fromObject(course).toString());

            beginIndex = matcher.start(1);


            if (!firstFlag) {
                String extraStr = cutStr.substring(lastIndex, beginIndex);
                Matcher matcher_temp = pattern_02.matcher(extraStr);
                while(matcher_temp.find()) {
                    lastCourse.changeInfo(matcher_temp.group(1), matcher_temp.group(2), matcher_temp.group(3));
                    courses.add(JSONObject.fromObject(lastCourse).toString());
                }
            }

            lastCourse = course;
            lastIndex = matcher.end(0);

            if(firstFlag) {
                firstFlag = false;
            }
        }

        String extraStr = cutStr.substring(lastIndex);
        Matcher matcher_temp = pattern_02.matcher(extraStr);
        while(matcher_temp.find()) {
            lastCourse.changeInfo(matcher.group(1), matcher.group(2), matcher.group(3));
        }



        jsonArray.add(courses.toString());

        JSONObject jsonObject = new JSONObject();

        Map<String, String> hashMap = ExtraInfoUtil.getInfo(PageData);
        jsonObject.put("term", hashMap.get("term"));
        jsonObject.put("stuNum", hashMap.get("stuNum"));
        jsonObject.put("data", jsonArray.toString());

        return jsonObject;

    }
}
