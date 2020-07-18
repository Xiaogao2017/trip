package com.trip.service.implement;

import com.trip.dao.CategoryDao;
import com.trip.dao.implement.CategoryImplement;
import com.trip.domain.Category;
import com.trip.service.CategoryService;

import java.util.List;

/**
 * @author Mr. Li xiaogao 2020-06-06 11:32
 */
public class CategoryServiceImplement implements CategoryService {
    private CategoryDao categoryDao = new CategoryImplement();
    @Override
    public List<Category> finAll() {
        return categoryDao.findAll();
    }
}
