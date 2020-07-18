package com.trip.web.servlet;

import com.trip.domain.Favourite;
import com.trip.domain.Route;
import com.trip.domain.User;
import com.trip.service.FavouriteService;
import com.trip.service.RouteService;
import com.trip.service.implement.FavouriteServiceImplement;
import com.trip.service.implement.RouteServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-07 22:30
 */
@WebServlet("/findFavouriteRoutesServlet")
public class FindFavouriteRoutesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FavouriteService service = new FavouriteServiceImplement();
        RouteService routeService = new RouteServiceImplement();
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            //用户尚未登录
            return ;
        } else {
            //用户已经登录
            uid = user.getUid();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
