package com.robinwu.api.dao;

import com.robinwu.api.domain.Music;

import java.util.List;

/**
 * Created by Robin on 16/5/30.
 */
public interface IMusicDAO {

    public int create(String name, Integer albumId);

    public int create(Music album);

    public Music getMusic(Integer id);

    public List<Music> listMusic();

    public List<Music> listMusic(Integer albumId);

    public int delete(Integer id);

    public int update(Integer id, String name, Integer albumId);

    public int update(Integer id, String name);

    public int update(Integer id, Integer albumId);
}
