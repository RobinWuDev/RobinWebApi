package com.robinwu.api.dao.mapper;

import com.robinwu.api.domain.Album;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Robin on 16/5/30.
 */
public class AlbumMapper implements RowMapper<Album> {


    public Album mapRow(ResultSet resultSet, int i) throws SQLException {
        Album album = new Album();
        album.setId(resultSet.getInt("id"));
        album.setName(resultSet.getString("name"));
        album.setYear(resultSet.getString("year"));
        return album;
    }
}
