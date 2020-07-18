package com.trip.service;

import com.trip.domain.Favourite;

import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-07 19:08
 */
public interface FavouriteService {
    /**
     * 判断是否收藏的方法
     * @param rid 页面传进来的路线id
     * @param uid 从session中获取的用户id
     * @return 收藏或没收藏
     */
    public boolean isFavourite(String rid,int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);

    List<Favourite> findRidsByUid(int uid);
}
