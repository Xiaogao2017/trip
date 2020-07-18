package com.trip.dao;

import com.trip.domain.User;

/**
 * @author Mr. Li xiaogao 2020-06-04 17:55
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户对象
     */
    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    User findByUsernameAndPassword(String username, String password);
}
