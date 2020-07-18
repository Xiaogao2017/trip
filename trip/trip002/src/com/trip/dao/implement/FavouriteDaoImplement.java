package com.trip.dao.implement;

import com.trip.dao.FavouriteDao;
import com.trip.domain.Favourite;
import com.trip.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-07 19:18
 */
public class FavouriteDaoImplement implements FavouriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favourite FindByRidAndUid(int rid, int uid) {
        String sql = "select * from tab_favorite where rid = ? and uid = ? ";
        Favourite favourite = null;
        try {
            favourite = template.queryForObject(sql, new BeanPropertyRowMapper<Favourite>(Favourite.class),rid,uid);
            System.out.println(favourite);
        } catch (Exception e) {
            System.out.println("有异常发生");
        }
        return favourite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ? ";
        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        template.update(sql,rid,new Date(),uid);
    }

    @Override
    public List<Favourite> findRidsByUid(int uid) {
        String sql = "select * from tab_favorite where uid = ? ";
        List<Favourite> ids = template.query(sql, new BeanPropertyRowMapper<Favourite>(Favourite.class), uid);
        return ids;
    }
}
