package com.robinwu.api.dao;

import com.robinwu.api.domain.MingYan;

import java.util.List;

/**
 * Created by Robin on 16/5/30.
 */
public interface IMingYanDAO {

    public int create(String author, String content,Integer categoryId);

    public int create(MingYan mingYan);

    public MingYan getMingYan(Integer id);

    public List<MingYan> listMingYan();

    public List<MingYan> listMingYan(Integer categoryId);

    public int delete(Integer id);

    public int update(Integer id, String author, String content,Integer categoryId);

    public int updateAuthor(Integer id, String author);

    public int updateContent(Integer id, String content);

    public int updateCategory(Integer id, Integer categoryId);
}
