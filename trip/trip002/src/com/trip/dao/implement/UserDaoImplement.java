package com.trip.dao.implement;

import com.trip.dao.UserDao;
import com.trip.domain.User;
import com.trip.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Mr. Li xiaogao 2020-06-04 17:54
 */
public class UserDaoImplement implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ? ";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }
        return user;
    }

    /**
     * 保存user对象到数据库
     * @param user 注册的用户
     */
    @Override
    public void save(User user) {

        String sql = "insert into tab_user(uid,username,password,name,birthday,sex,telephone,email) values(null,?,?,?,?,?,?,?)";
        System.out.println("user = " + user);
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
    }

    /**
     * 根据用户名和密码查询用户信息
     * @param username username
     * @param password password
     * @return user对象
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (Exception e) {

        }
        return user;
    }
}
