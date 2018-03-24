package util;

import java.util.Arrays;
import java.util.HashMap;


public class FormatUtil {
    public static HashMap<String, String> formatStr(String str) {

        HashMap<String, String> formatMap = new HashMap<>();

        int lessonEnd = str.indexOf("节");

        String lessonStr = "第";

        String[] lessonTime = str.substring(4, lessonEnd).split("-");
        int LessonB = Integer.valueOf(lessonTime[0]);
        int LessonE = Integer.valueOf(lessonTime[1]);
        int hashLesson = LessonE - LessonB + 1;
        lessonStr += TransformUtil.trans(LessonB) + TransformUtil.trans(LessonE) + "节";

        String weekStr = str.substring(lessonEnd + 1);
        String trimStr = weekStr.replace("周","");
        String[] weeks = trimStr.split(",");
        String  weekList = "";
        for(int i = 0;i< weeks.length;i++){

            if(weeks[i].indexOf("-") != -1){
                int flag;
                char mod = weeks[i].charAt(weeks[i].length()-1);
                switch (mod){
                    case '单':flag= 1 ;break;
                    case '双':flag= 2 ;break;
                    default:flag= 0 ;break;
                }
                String[] weekSE = weeks[i].split("-");
                int begin = Integer.valueOf(weekSE[0]);

                int end = Integer.valueOf(weekSE[1].replace("双","").replace("单", ""));
                for(int j =begin; j <= end;j++){
                    if(flag==1 && j%2 == 0){
                        continue;
                    }else if(flag == 2 && j%2==1){
                        continue;
                    }
                    weekList += (j + " ");
                }
            }
            else {
                weekList += (weeks[i]+" ");
            }
        }

        weekList = weekList.trim();

        formatMap.put("day", str.substring(0, 2)+TransformUtil.trans(str.charAt(2)-'0'));
        formatMap.put("lesson", lessonStr);
        formatMap.put("hashLesson", hashLesson + "");
        formatMap.put("beginLesson", LessonB+ "");
        formatMap.put("week", weekList);
        formatMap.put("rawWeek",str.substring(lessonEnd+1));

        return formatMap;
    }
}
