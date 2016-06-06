package com.robinwu.api.dao.mapper;

import com.robinwu.api.domain.Music;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Robin on 16/5/30.
 */
public class MusicMapper implements RowMapper<Music> {


    public Music mapRow(ResultSet resultSet, int i) throws SQLException {
        Music album = new Music();
        album.setId(resultSet.getInt("id"));
        album.setName(resultSet.getString("name"));
        album.setAlbumId(resultSet.getInt("album_id"));
        return album;
    }
}
