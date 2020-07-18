package com.trip.service.implement;

import com.trip.dao.FavouriteDao;
import com.trip.dao.RouteDao;
import com.trip.dao.RouteImgDao;
import com.trip.dao.SellerDao;
import com.trip.dao.implement.FavouriteDaoImplement;
import com.trip.dao.implement.RouteDaoImplement;
import com.trip.dao.implement.RouteImgDaoImplement;
import com.trip.dao.implement.SellerDaoImplement;
import com.trip.domain.PageBean;
import com.trip.domain.Route;
import com.trip.domain.RouteImg;
import com.trip.domain.Seller;
import com.trip.service.RouteService;

import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-06 16:11
 */
public class RouteServiceImplement implements RouteService {
    private RouteDao routeDao = new RouteDaoImplement();
    private RouteImgDao routeImgDao = new RouteImgDaoImplement();
    private SellerDao sellerDao = new SellerDaoImplement();
    private FavouriteDao favouriteDao = new FavouriteDaoImplement();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装pageBean
        PageBean<Route> pageBean = new PageBean<>();
        //设置当前页码
        pageBean.setCurrentPage(currentPage);
        //设置每页显示条数
        pageBean.setPageSize(pageSize);
        //设置总记录数
        int totalCount = routeDao.finTotalCount(cid,rname);
        pageBean.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        //开始的记录数
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pageBean.setList(list);
        //设置总页数 = 总记录数 / 每页显示条数
        int totalPage = totalCount % pageSize == 0 ? (totalCount/pageSize):(totalCount/pageSize + 1);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public Route findOne(String rid) {
        //根据rid查询route表中的route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        //根据rid查询图片的集合信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        //将图片集合设置到route对象中
        route.setRouteImgList(routeImgList);
        //根据route的id查询商家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        //查询收藏次数
        int count = favouriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }

    @Override
    public List<Route> findFavouriteRouteTop12() {
        List<Route> routeListTop12 = routeDao.findFavouriteRouteTop12();
        return routeListTop12;
    }

    @Override
    public void addFavouriteCountByRid(String rid) {
        routeDao.addFavouriteCountByRid(Integer.parseInt(rid));
    }
}
