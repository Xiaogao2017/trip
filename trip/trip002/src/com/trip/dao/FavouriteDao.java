package com.trip.dao;

import com.trip.domain.Favourite;

import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-07 19:13
 */
public interface FavouriteDao {
    /**
     * 根据路线id和用户id查询收藏信息
     * @param rid rid
     * @param uid uid
     * @return Favourite对象
     */
    public Favourite FindByRidAndUid(int rid, int uid);

    int findCountByRid(int rid);

    void add(int rid, int uid);

    public List<Favourite> findRidsByUid(int uid);
}
