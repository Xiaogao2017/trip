package com.trip.web.servlet;

import com.trip.domain.Favourite;
import com.trip.domain.PageBean;
import com.trip.domain.Route;
import com.trip.domain.User;
import com.trip.service.FavouriteService;
import com.trip.service.RouteService;
import com.trip.service.implement.FavouriteServiceImplement;
import com.trip.service.implement.RouteServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-06 15:32
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    //定义全局变量
    private RouteService routeService = new RouteServiceImplement();
    private FavouriteService favouriteService = new FavouriteServiceImplement();
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //接收线路名称参数，根据输入的线路名称进行数据库查询
        String rname = request.getParameter("rname");
        //解决乱码问题
//        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        //当前页面，如果不传递，则默认第一页
        int currentPage = 0;
        //每页显示条数，如果不传递，默认每页显示 5 条
        int pageSize = 5;
        int cid = 0;
        //处理参数
        if ("undefined".equals(rname) || "null".equals(rname)) {
            rname = null;
        }
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        //调用service查询PageBean对象

        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize,rname);
        //将pageBean对象序列化为json ，方便传输。
        writeValue(pageBean,response);
    }

    /**
     * 旅游线路详情，根据旅游线路id查询线路的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收旅游线路id
        String rid = request.getParameter("rid");
        //调用service查询route对象
        Route route = routeService.findOne(rid);
        //序列化为json对象，返回客户端
        writeValue(route,response);
    }

    /**
     * 判断当前的登录用户是否收藏过该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取线路id
        String rid = request.getParameter("rid");
        //获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            //用户尚未登录
            uid = 0;
        } else {
            //用户已经登录
            uid = user.getUid();
        }
        //调用FavouriteServlet查询是否收藏
        boolean flag = favouriteService.isFavourite(rid, uid);
        //将flag写回客户端
        writeValue(flag,response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取线路id和用户id
        //获取线路id
        String rid = request.getParameter("rid");
        //获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            //用户尚未登录
            return ;
        } else {
            //用户已经登录
            uid = user.getUid();
        }
        //调用service添加我的喜欢
        favouriteService.add(rid,uid);
        routeService.addFavouriteCountByRid(rid);
    }

    public void findFavouriteRouteByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取用户id
        //获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            //用户尚未登录
            //return ;
            uid = 3;
        } else {
            //用户已经登录
            uid = user.getUid();
        }
        List<Favourite> rids = favouriteService.findRidsByUid(uid);
        List<Route> favouriteRoutes = new ArrayList<>();
        Route one = null;
        //根据用户喜欢的路线id查询详细的路线对象
        for (Favourite rid : rids) {
            one = routeService.findOne(Integer.toString(rid.getRid()));
            favouriteRoutes.add(one);
        }
        writeValue(favouriteRoutes,response);
    }
    //findFavouriteRoute
    public void findFavouriteRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取数据库中收藏量前 12 的旅游线路
        List<Route> routeListTop12 = routeService.findFavouriteRouteTop12();
        //写到前端json格式
        writeValue(routeListTop12,response);
    }
}
