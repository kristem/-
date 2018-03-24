package course;


import util.FormatUtil;

import java.util.HashMap;

public class Course {
    /**
     * 读取到的序号
     * 课程名
     * 类型
     * 教师
     * 单双
     * 教室
     * 开始周数
     * 结束周数
     * 时间
     * 节数
     */
    private String num;
    private String name;
    private String type;
    private String teacher;
    private String day;
    private String[] weeks;
    private String classRoom;
    private String weekEnd;
    private String weekBegin;
    private String rawWeek;
    private String lesson;
    private String hashLesson;
    private String beginLesson;

    public Course(String numAndName, String type,String teacher,String rawWeek,String classRoom) {
        String[] temp = numAndName.split("-");
        this.num = temp[0];
        this.name = temp[1];
        this.type = type;
        this.teacher = teacher;
        this.classRoom = classRoom;
        HashMap<String,String> formatMap = FormatUtil.formatStr(rawWeek);
        this.beginLesson = formatMap.get("beginLesson");
        this.hashLesson = formatMap.get("hashLesson");
        this.day = formatMap.get("day");
        this.lesson = formatMap.get("lesson");

        this.rawWeek = formatMap.get("rawWeek");

        String weekTemp = formatMap.get("week");
        this.weeks = weekTemp.split(" ");
        this.weekBegin = weeks[0];
        this.weekEnd = weeks[weeks.length-1];
    }

    public void changeInfo(String teacher,String rawWeek,String classRoom) {
        this.teacher = teacher;
        this.classRoom = classRoom;
        HashMap<String,String> formatMap = FormatUtil.formatStr(rawWeek);
        this.beginLesson = formatMap.get("beginLesson");
        this.hashLesson = formatMap.get("hashLesson");
        this.day = formatMap.get("day");
        this.lesson = formatMap.get("lesson");
        this.rawWeek = formatMap.get("rawWeek");
    }

    public String getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDay() {
        return day;
    }

    public String[] getWeeks() {
        return weeks;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public String getWeekBegin() {
        return weekBegin;
    }

    public String getRawWeek() {
        return rawWeek;
    }

    public String getLesson() {
        return lesson;
    }

    public String getHashLesson() {
        return hashLesson;
    }

    public String getBeginLesson() {
        return beginLesson;
    }
}
