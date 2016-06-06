package com.robinwu.api.dao.mapper;

import com.robinwu.api.domain.MingYan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Robin on 16/5/30.
 */
public class MingYanMapper implements RowMapper<MingYan> {


    public MingYan mapRow(ResultSet resultSet, int i) throws SQLException {
        MingYan album = new MingYan();
        album.setId(resultSet.getInt("id"));
        album.setAuthor(resultSet.getString("author"));
        album.setContent(resultSet.getString("content"));
        album.setCategoryId(resultSet.getInt("category_id"));
        return album;
    }
}
