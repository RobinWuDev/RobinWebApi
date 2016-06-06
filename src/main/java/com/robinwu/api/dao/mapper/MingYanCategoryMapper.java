package com.robinwu.api.dao.mapper;

import com.robinwu.api.domain.MingYanCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Robin on 16/5/30.
 */
public class MingYanCategoryMapper implements RowMapper<MingYanCategory> {


    public MingYanCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        MingYanCategory album = new MingYanCategory();
        album.setId(resultSet.getInt("id"));
        album.setName(resultSet.getString("name"));
        return album;
    }
}
