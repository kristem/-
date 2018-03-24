package servlet;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import util.CourseMaker;
import util.JsonUtil;
import util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/info/getTable")
public class getTable extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String stuNum = req.getParameter("stu_num");
        JSONObject jsonObject = UrlUtil.getCourseList(stuNum);
        jsonObject.put("result","success");
        JsonUtil.writeResponse(resp, jsonObject.toString());
    }
}
