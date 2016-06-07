package com.robinwu.api.controller;

import com.robinwu.api.dao.IAlbumDAO;
import com.robinwu.api.dao.IMingYanDAO;
import com.robinwu.api.dao.IMusicDAO;
import com.robinwu.api.domain.Album;
import com.robinwu.api.domain.MingYan;
import com.robinwu.api.domain.Music;
import com.robinwu.api.domain.WebResponse;
import com.robinwu.api.vo.AlbumVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Robin on 16/6/3.
 */
@Api(value="index")
@Controller
@RequestMapping(value = "index")
public class IndexController {

    @Autowired
    private IMusicDAO musicDAO;

    @Autowired
    private IAlbumDAO albumDAO;

    @Autowired
    private IMingYanDAO mingYanDAO;

    public IndexController() {
    }

    public IndexController(IMusicDAO musicDao,IAlbumDAO albumDao) {
        this.musicDAO = musicDao;
        this.albumDAO = albumDao;
    }

    public IndexController(IMingYanDAO mingYanDAO) {
        this.mingYanDAO = mingYanDAO;
    }

    @ApiOperation("获取全部的专辑信息,包含音乐详细")
    @RequestMapping(value="musicList",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse musicList() {
        ArrayList<AlbumVO> result = new ArrayList<AlbumVO>();

        List<Album> allAlbums = albumDAO.listAlbum();
        Iterator<Album> iterator = allAlbums.iterator();
        while(iterator.hasNext()){
            AlbumVO albumVo = new AlbumVO(iterator.next());
            List<Music> musics = musicDAO.listMusic(albumVo.getId());
            albumVo.setMusics(musics);
            result.add(albumVo);
        }
        return WebResponse.getOKResponse(result);
    }

    @ApiOperation("随机获取一首歌")
    @RequestMapping(value="randMusic",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse randMusic() {
        HashMap<String,Object> result = new HashMap<String, Object>();
        List<Music> musics = musicDAO.listMusic();
        if(musics.size() > 0) {
            int randNumber = (int)(Math.random() * (musics.size()-1));
            Music music = musics.get(randNumber);
            Album album = albumDAO.getAlbum(music.getAlbumId());
            result.put("id",music.getId());
            result.put("name",music.getName());
            result.put("albumId",music.getAlbumId());
            result.put("albumName",album.getName());
        }

        return WebResponse.getOKResponse(result);
    }

    @ApiOperation("随机获取一句名言")
    @RequestMapping(value="randMingYan",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse randMingYan() {
        MingYan mingYan = null;
        List<MingYan> mingYans = mingYanDAO.listMingYan();
        if(mingYans.size() > 0) {
            int randNumber = (int)(Math.random() * (mingYans.size()-1));
            mingYan = mingYans.get(randNumber);
        }
        return WebResponse.getOKResponse(mingYan);
    }
}
