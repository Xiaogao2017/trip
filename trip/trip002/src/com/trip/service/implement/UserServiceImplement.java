package com.trip.service.implement;

import com.trip.dao.UserDao;
import com.trip.dao.implement.UserDaoImplement;
import com.trip.domain.User;
import com.trip.service.UserService;

/**
 * @author Mr. Li xiaogao 2020-06-04 17:53
 */
public class UserServiceImplement implements UserService {
    //声明成员变量
    private UserDao dao = new UserDaoImplement();
    /**
     * 注册方法
     * @param user 注册页面返回的用户对象
     * @return 结果
     */
    @Override
    public boolean register(User user) {
        //根据用户名查询用户对象，避免注册的用户名与数据库中的数据重复
        User u = dao.findByUsername(user.getUsername());
        if (u != null) {
            //用户名存在，注册失败
            return false;
        }
        //保存
        dao.save(user);
        return true;
    }

    /**
     * 用户登录方法
     * @param user 登录页面返回的用户对象
     * @return 返回数据库查询的结果
     */
    @Override
    public User login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        return dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
