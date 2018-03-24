package util;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class JsonUtil {

    public static final String UTF8 = "UTF-8";

    public static boolean writeResponse(HttpServletResponse response, String json){
        BufferedWriter writer = null;
        boolean result=false;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            response.getOutputStream(),UTF8
                    )
            );
            writer.write(json);
            writer.flush();
            result=true;
        } catch (IOException e) {
            e.printStackTrace();
            result=false;
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
