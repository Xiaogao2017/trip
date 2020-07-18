package com.trip.dao;

import com.trip.domain.RouteImg;

import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-07 14:58
 */
public interface RouteImgDao {
    /**
     * 根据线路id查询图片
     * @param rid rid
     * @return 线路的图片信息
     */
    public List<RouteImg> findByRid(int rid);
}
