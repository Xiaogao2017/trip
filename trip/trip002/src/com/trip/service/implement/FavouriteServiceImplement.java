package com.trip.service.implement;

import com.trip.dao.FavouriteDao;
import com.trip.dao.implement.FavouriteDaoImplement;
import com.trip.domain.Favourite;
import com.trip.service.FavouriteService;

import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-07 19:11
 */
public class FavouriteServiceImplement implements FavouriteService {
    private FavouriteDao favouriteDao = new FavouriteDaoImplement();
    @Override
    public boolean isFavourite(String rid, int uid) {
        Favourite favourite = favouriteDao.FindByRidAndUid(Integer.parseInt(rid), uid);
        //如果favourite不为空则说明已经收藏过，返回true
        return favourite != null;
    }

    @Override
    public void add(String rid, int uid) {
        favouriteDao.add(Integer.parseInt(rid),uid);
    }

    @Override
    public List<Favourite> findRidsByUid(int uid) {
        List<Favourite> ids = favouriteDao.findRidsByUid(uid);
        return ids;
    }
}
