package com.trip.dao.implement;

import com.trip.dao.RouteDao;
import com.trip.domain.Route;
import com.trip.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-06 16:00
 */
public class RouteDaoImplement implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int finTotalCount(int cid,String rname) {
        //String sql = "select count(*) from tab_route where cid = ?";
        //多条件组合查询
        //定义sql模板
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        //条件的参数
        List params = new ArrayList();
        //判断是否有值
        if (cid != 0) {
            stringBuilder.append(" and cid = ? ");
            //添加条件对应的值
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            stringBuilder.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = stringBuilder.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        //String sql = "select * from tab_route where cid = ? limit ? , ?";
        //定义动态sql
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        //条件的参数
        List params = new ArrayList();
        //判断是否有值
        if (cid != 0) {
            stringBuilder.append(" and cid = ? ");
            //添加条件对应的值
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            stringBuilder.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        stringBuilder.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);

        sql = stringBuilder.toString();

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }

    @Override
    public List<Route> findFavouriteRouteTop12() {
        String sql = "SELECT * FROM tab_route ORDER BY COUNT DESC LIMIT 0,12 ";
        List<Route> routeListTop12 = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
        return routeListTop12;
    }

    @Override
    public void addFavouriteCountByRid(int rid) {
        String sql = "UPDATE tab_route SET COUNT=COUNT+1 WHERE rid = ? ";
        template.update(sql,rid);
    }
}
