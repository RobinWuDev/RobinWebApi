package com.robinwu.api.controller;

import com.robinwu.api.dao.IAlbumDAO;
import com.robinwu.api.dao.IMusicDAO;
import com.robinwu.api.domain.Album;
import com.robinwu.api.domain.Music;
import com.robinwu.api.domain.WebResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Robin on 16/6/3.
 */
@Api(value="music")
@Controller
@RequestMapping(value = "music")
public class MusicController {

    @Autowired
    private IMusicDAO domainDAO;

    public MusicController() {
    }

    public MusicController(IMusicDAO dao) {
        this.domainDAO = dao;
    }

    @ApiOperation("根据专辑id,获取音乐列表")
    @ApiParam(name = "albumId",value = "专辑ID,如果不传,为获取全部",required = false)
    @RequestMapping(value="list",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse list(@RequestParam(value="albumId",defaultValue = "0",required = false) Integer albumId) {
        List<Music> objs = null;
        if (albumId == 0) {
            objs = domainDAO.listMusic();
        } else {
            objs = domainDAO.listMusic(albumId);
        }
        return WebResponse.getOKResponse(objs);
    }

    @ApiOperation("根据音乐id,获取音乐信息")
    @RequestMapping(value="getMusic",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse getMusic(@RequestParam(value="id") Integer id) {
        Music music = domainDAO.getMusic(id);
        return WebResponse.getOKResponse(music);
    }

    @ApiOperation("添加音乐")
    @RequestMapping(value="add",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse add(@RequestParam(value = "name") String name,
                    @RequestParam(value = "albumId") Integer albumId) {
        int result = domainDAO.create(name,albumId);
        if(result != 0) {
            return WebResponse.getOKResponse("Create Music Finish");
        } else {
            return WebResponse.getErrorResponse("Create Music Faild");
        }
    }

    @ApiOperation("删除音乐")
    @RequestMapping(value="delete",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse delete(@RequestParam(value = "id") int id) {
        int result = domainDAO.delete(id);
        if(result != 0) {
            return WebResponse.getOKResponse("Remove Music Finish");
        } else {
            return WebResponse.getErrorResponse("Remove Music Faild");
        }
    }

    @ApiOperation("更新音乐")
    @RequestMapping(value="update",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse update(@RequestParam(value = "id") int id,
                    @RequestParam(value = "name",required = false) String name,
                    @RequestParam(value = "albumId",defaultValue = "0",required = false) Integer albumId) {
        int result = domainDAO.update(id,name,albumId);
        if(result != 0) {
            return WebResponse.getOKResponse("Update Music Finish");
        } else {
            return WebResponse.getErrorResponse("Update Music Faild");
        }
    }
}
