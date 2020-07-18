package com.trip.dao;

import com.trip.domain.Seller;

/**
 * @author Mr. Li xiaogao 2020-06-07 15:11
 * 查询商家信息
 */
public interface SellerDao {
    public Seller findById(int id);
}
