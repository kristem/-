package util;

import course.Course;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlUtil {

        public static String getData(String strUrl) throws IOException {

            URL url = new URL(strUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(

                    new InputStreamReader(

                            connection.getInputStream(), "UTF-8"

                    )

            );

            StringBuilder builder = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {

                builder.append(line);

            }

            reader.close();

            connection.disconnect();

            return builder.toString();

        }

        public static JSONObject getCourseList(String stu_num) throws IOException {

            String PageData = getData("http://jwzx.cqupt.edu.cn/jwzxtmp/kebiao/kb_stu.php?xh=" + stu_num);
            JSONObject jsonObject = CourseMaker.getList(PageData);
            return jsonObject;

        }
}
