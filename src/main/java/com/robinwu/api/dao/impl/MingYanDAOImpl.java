package com.robinwu.api.dao.impl;

import com.robinwu.api.dao.IAlbumDAO;
import com.robinwu.api.dao.IMingYanDAO;
import com.robinwu.api.dao.mapper.AlbumMapper;
import com.robinwu.api.dao.mapper.MingYanMapper;
import com.robinwu.api.domain.Album;
import com.robinwu.api.domain.MingYan;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Robin on 16/5/30.
 */
@Repository
public class MingYanDAOImpl implements IMingYanDAO {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    public int create(String author, String content, Integer categoryId) {
        String SQL = "insert into jay_mingyan (author,content,category_id) values (?,?,?)";
        int result = jdbcTemplateObject.update(SQL,author,content,categoryId);
        System.out.println("Create MingYan Record author = " + author + "content = " + content +
                " categoryId = " + categoryId + " result = " + result);
        return result;
    }

    public int create(MingYan mingYan) {
        String SQL = "insert into jay_mingyan (id,author,content,category_id) values (?,?,?,?)";
        int result = jdbcTemplateObject.update(SQL, mingYan.getId(),mingYan.getAuthor(),mingYan.getContent(),mingYan.getCategoryId());
        System.out.println("Create MingYan Record author = " + mingYan.getAuthor() + "content = " + mingYan.getContent() +
                " categoryId = " + mingYan.getCategoryId() + " result = " + result);
        return result;
    }

    public MingYan getMingYan(Integer id) {
        String SQL = "select * from jay_mingyan where id = ?";
        MingYan album = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new MingYanMapper());
        return album;
    }

    public List<MingYan> listMingYan() {
        String SQL = "select * from jay_mingyan";
        List <MingYan> albums = jdbcTemplateObject.query(SQL,
                new MingYanMapper());
        return albums;
    }

    public List<MingYan> listMingYan(Integer categoryId) {
        String SQL = "select * from jay_mingyan where category_id = ?";
        List <MingYan> albums = jdbcTemplateObject.query(SQL,
                new Object[]{categoryId},
                new MingYanMapper());
        return albums;
    }

    public int delete(Integer id) {
        String SQL = "delete from jay_mingyan where id = ?";
        int result = jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted MingYan Record with ID = " + id + " result = " + result);
        return result;
    }

    public int update(Integer id, String author, String content, Integer categoryId) {
        ArrayList<String> params = new ArrayList<String>();
        if(author != null) {
            params.add("author='"+author+"'");
        }

        if(content != null) {
            params.add("content='"+content+"'");
        }

        if(categoryId != 0) {
            params.add("category_id="+categoryId);
        }

        if(params.size() == 0) {
            return 0;
        }
        String SQL = "update jay_mingyan set " + StringUtils.join(params,',') + " where id = ?";
        int result = jdbcTemplateObject.update(SQL,id);
        System.out.println("Updated MingYan Record with ID = " + id + " result = " + result);
        return result;
    }

}
