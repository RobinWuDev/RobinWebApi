package com.robinwu.api.dao.impl;

import com.robinwu.api.dao.IMusicDAO;
import com.robinwu.api.dao.mapper.MusicMapper;
import com.robinwu.api.domain.Music;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 16/5/30.
 */
@Repository
public class MusicDAOImpl implements IMusicDAO {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    public int create(String name, Integer albumId) {
        String SQL = "insert into jay_music (name,album_id) values (?,?)";
        int result = jdbcTemplateObject.update(SQL,name,albumId);
        System.out.println("Create Music Record Name = " + name + "albumId = " + albumId + " result = " + result);
        return result;
    }

    public int create(Music music) {
        String SQL = "insert into jay_music (id,name,album_id) values (?,?,?)";
        int result = jdbcTemplateObject.update(SQL,music.getId(),music.getName(),music.getAlbumId());
        System.out.println("Create Music Record Name = " + music.getName() + "albumId = " + music.getAlbumId() + " result = " + result);
        return result;
    }

    public Music getMusic(Integer id) {
        String SQL = "select * from jay_music where id = ?";
        Music music = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new MusicMapper());
        return music;
    }

    public List<Music> listMusic() {
        String SQL = "select * from jay_music";
        List<Music> musics = jdbcTemplateObject.query(SQL,
                new MusicMapper());
        return musics;
    }

    public List<Music> listMusic(Integer albumId) {
        String SQL = "select * from jay_music where album_id = ?";
        List<Music> musics = jdbcTemplateObject.query(SQL,new Object[]{albumId},
                new MusicMapper());
        return musics;
    }

    public int delete(Integer id) {
        String SQL = "delete from jay_music where id = ?";
        int result = jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Music Record with ID = " + id + " result = " + result);
        return result;
    }

    public int update(Integer id, String name, Integer albumId) {
        ArrayList<String> params = new ArrayList<String>();
        if(name != null) {
            params.add("name='"+name+"'");
        }

        if(albumId != 0) {
            params.add("album_id="+albumId);
        }

        if(params.size() == 0) {
            return 0;
        }
        String SQL = "update jay_music set " + StringUtils.join(params,',') + " where id = ?";
        int result = jdbcTemplateObject.update(SQL,id);
        System.out.println("Updated Music Record with ID = " + id + " name=" + name +
                " albumId=" + albumId + " result = " + result);
        return result;
    }
}
