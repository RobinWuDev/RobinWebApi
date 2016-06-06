package com.robinwu.api.dao;

import com.robinwu.api.domain.Album;

import java.util.List;

/**
 * Created by Robin on 16/5/30.
 */
public interface IAlbumDAO {

    public int create(String name,String year);

    public int create(Album album);

    public Album getAlbum(Integer id);

    public List<Album> listAlbum();

    public int delete(Integer id);

    public int update(Integer id,String name,String year);
}
