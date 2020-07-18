package com.trip.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.domain.ResultInfo;
import com.trip.domain.User;
import com.trip.service.UserService;
import com.trip.service.implement.UserServiceImplement;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Mr. Li xiaogao 2020-06-05 10:49
 */
@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码
        Map<String, String[]> map = request.getParameterMap();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service查询
        UserService service = new UserServiceImplement();
        User u = service.login(user);
        //提示信息
        ResultInfo info = new ResultInfo();
        //判断用户对象是否为null
        if (u == null) {
            //用户名或密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误!!!");
        } else {
            //数据库存在该用户，登录成功的判断
            info.setFlag(true);
            //存入session域中
            request.getSession().setAttribute("user",u);
        }
        //响应数据
        ObjectMapper mapper = new ObjectMapper();
        //设置响应格式
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
        //将info对象序列化为json
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
//        //将json数据写回客户端
//        //设置context-type类型
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(json);
        System.out.println("json = " + info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
