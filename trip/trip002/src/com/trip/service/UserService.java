package com.trip.service;

import com.trip.domain.User;

/**
 * @author Mr. Li xiaogao 2020-06-04 17:52
 */
public interface UserService {
    boolean register(User user);

    User login(User user);
}
