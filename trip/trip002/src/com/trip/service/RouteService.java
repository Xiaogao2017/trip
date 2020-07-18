package com.trip.service;

import com.trip.domain.PageBean;
import com.trip.domain.Route;

import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-06 16:08
 */
public interface RouteService {
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

    /**
     * 根据旅游线路id查询详细信息
     * @param rid 旅游线路id
     * @return 详细路线
     */
    Route findOne(String rid);

    List<Route> findFavouriteRouteTop12();

    void addFavouriteCountByRid(String rid);
}
