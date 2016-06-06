package com.robinwu.api.dao.impl;

import com.robinwu.api.dao.IMingYanCategoryDAO;
import com.robinwu.api.dao.mapper.AlbumMapper;
import com.robinwu.api.dao.mapper.MingYanCategoryMapper;
import com.robinwu.api.domain.Album;
import com.robinwu.api.domain.MingYanCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Robin on 16/5/30.
 */
@Repository
public class MingYanCategoryDAOImpl implements IMingYanCategoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    public Album getAlbum(Integer id) {
        String SQL = "select * from jay_album where id = ?";
        Album album = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new AlbumMapper());
        return album;
    }

    public List<Album> listAlbum() {
        String SQL = "select * from jay_album";
        List <Album> albums = jdbcTemplateObject.query(SQL,
                new AlbumMapper());
        return albums;
    }

    public int create(String name) {
        String SQL = "insert into jay_mingyan_category (name) values (?)";
        int result = jdbcTemplateObject.update(SQL,name);
        System.out.println("Create MingYanCategory Record Name = " + name + " result = " + result);
        return result;
    }

    public int create(MingYanCategory category) {
        String SQL = "insert into jay_mingyan_category (id,name) values (?,?)";
        int result = jdbcTemplateObject.update(SQL,category.getId(),category.getName());
        System.out.println("Create MingYanCategory Record Name = " + category.getName() + " result = " + result);
        return result;
    }

    public MingYanCategory getCategory(Integer id) {
        String SQL = "select * from jay_mingyan_category where id = ?";
        MingYanCategory mingYanCategory = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new MingYanCategoryMapper());
        return mingYanCategory;
    }

    public List<MingYanCategory> listCategory() {
        String SQL = "select * from jay_mingyan_category";
        List <MingYanCategory> mingYanCategories = jdbcTemplateObject.query(SQL,
                new MingYanCategoryMapper());
        return mingYanCategories;
    }

    public int delete(Integer id) {
        String SQL = "delete from jay_mingyan_category where id = ?";
        int result = jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted MingYanCategory Record with ID = " + id + " result = " + result);
        return result;
    }

    public int update(Integer id, String name) {
        String SQL = "update jay_mingyan_category set name = ? where id = ?";
        int result = jdbcTemplateObject.update(SQL, name , id);
        System.out.println("Updated MingYanCategory Record with ID = " + id + " name=" + name + " result = " + result);
        return result;
    }
}
