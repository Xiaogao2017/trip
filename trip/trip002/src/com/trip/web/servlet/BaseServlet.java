package com.trip.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Mr. Li xiaogao 2020-06-06 9:43
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法的分发
        //1.获取请求路径
        String uri = req.getRequestURI(); //  uri = /trip002/user/add
        System.out.println("uri = " + uri);
        //2.获取方法对象Method
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("methodName = " + methodName);
        //3.获取方法对象 this 谁调用我？我代表谁 this = com.trip.web.servlet.UserServlet@7d0d6ad2
        System.out.println("this = " + this);
        try {
            //getDeclareMethod :忽略访问权限控制符，获取方法
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //4.执行方法
            //暴力反射
            //method.setAccessible(true);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将传入的对象序列化为json，并且写回客户端
     * @param object o
     */
    public void writeValue(Object object,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),object);
    }

    /**
     * 将传入的对象序列化为json，并返回
     * @param object
     * @return
     */
    public String writeValueAsString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
