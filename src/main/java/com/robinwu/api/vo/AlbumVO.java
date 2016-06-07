package com.robinwu.api.vo;

import com.robinwu.api.domain.Album;
import com.robinwu.api.domain.Music;

import java.util.List;

/**
 * Created by Robin on 16/6/7.
 */
public class AlbumVO extends Album{
    private List<Music> musics;

    public AlbumVO(Album album) {
        this.setId(album.getId());
        this.setName(album.getName());
        this.setYear(album.getYear());
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }
}
