package com.robinwu.api.dao.impl;

import com.robinwu.api.dao.mapper.AlbumMapper;
import com.robinwu.api.dao.IAlbumDAO;
import com.robinwu.api.domain.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Robin on 16/5/30.
 */
@Repository
public class AlbumDAOImpl implements IAlbumDAO {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    public int create(String name, String year) {
        String SQL = "insert into jay_album (name,year) values (?,?)";
        int result = jdbcTemplateObject.update(SQL,name,year);
        System.out.println("Create Album Record Name = " + name + "year = " + year + " result = " + result);
        return result;
    }

    public int create(Album album) {
        String SQL = "insert into jay_album (id,name,year) values (?,?,?)";
        int result = jdbcTemplateObject.update(SQL,album.getId(),album.getName(),album.getYear());
        System.out.println("Create Album Record Id = " + album.getId() + " Name = " + album.getName() + "year = " + album.getYear() + " result = " + result);
        return result;
    }


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

    public int delete(Integer id) {
        String SQL = "delete from jay_album where id = ?";
        int result = jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Album Record with ID = " + id + " result = " + result);
        return result;
    }

    public int update(Integer id,String name, String year) {
        String SQL = "update jay_album set name = ?,year = ? where id = ?";
        int result = jdbcTemplateObject.update(SQL, name, year , id);
        System.out.println("Updated Album Record with ID = " + id + " result = " + result);
        return result;
    }
}
