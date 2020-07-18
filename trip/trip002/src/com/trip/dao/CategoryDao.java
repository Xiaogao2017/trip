package com.trip.dao;

import com.trip.domain.Category;

import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-06 11:23
 */
public interface CategoryDao {
    public List<Category> findAll();
}
