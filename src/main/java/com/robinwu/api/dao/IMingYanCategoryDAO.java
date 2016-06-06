package com.robinwu.api.dao;

import com.robinwu.api.domain.MingYanCategory;

import java.util.List;

/**
 * Created by Robin on 16/5/30.
 */
public interface IMingYanCategoryDAO {

    public int create(String name);

    public int create(MingYanCategory category);

    public MingYanCategory getCategory(Integer id);

    public List<MingYanCategory> listCategory();

    public int delete(Integer id);

    public int update(Integer id, String name);
}
