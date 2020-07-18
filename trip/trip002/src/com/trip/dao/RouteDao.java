package com.trip.dao;

import com.trip.domain.Route;

import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-06 15:48
 */
public interface RouteDao {
    /**
     * 根据cid查询总记录数
     */
    public int finTotalCount(int cid,String rname);
    /**
     * 根据cid，start，pageSize查询当前页面的数据集合
     */
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);

    /**
     * 根据id查询旅游线路
     * @param rid rid
     * @return route对象
     */
    public Route findOne(int rid);

    List<Route> findFavouriteRouteTop12();

    void addFavouriteCountByRid(int rid);
}
