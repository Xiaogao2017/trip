package com.trip.test;

import com.trip.dao.UserDao;
import com.trip.dao.implement.UserDaoImplement;
import com.trip.domain.User;

/**
 * @author Mr. Li xiaogao 2020-06-04 20:54
 */
public class Test {
    @org.junit.Test
    public void TestSave() {
        UserDao dao = new UserDaoImplement();
        User user = new User("zhangsan","123456");
        dao.save(user);
    }
    @org.junit.Test
    public void TestFindByUsername(){
        String username = "zhangsan";
        UserDao dao = new UserDaoImplement();
        User user = dao.findByUsername(username);
        System.out.println("user = " + user);
    }
}
