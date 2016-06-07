package com.robinwu.api.controller;

import com.robinwu.api.dao.IAlbumDAO;
import com.robinwu.api.domain.Album;
import com.robinwu.api.domain.WebResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
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
@Api(value="album")
@Controller
@RequestMapping(value = "album")
public class AlbumController {

    @Autowired
    private IAlbumDAO albumDAO;

    public AlbumController() {
    }

    public AlbumController(IAlbumDAO dao) {
        this.albumDAO = dao;
    }

    @ApiOperation("获取全部的专辑列表")
    @RequestMapping(value="list",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse list() {
        List<Album> albums = albumDAO.listAlbum();
        return WebResponse.getOKResponse(albums);
    }

    @ApiOperation("根据专辑id,获取专辑信息")
    @RequestMapping(value="getAlbum",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse getMusic(@RequestParam(value="albumId") Integer albumId) {
        Album album = albumDAO.getAlbum(albumId);
        return WebResponse.getOKResponse(album);
    }

    @ApiOperation("添加专辑")
    @RequestMapping(value="add",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse add(@RequestParam(value = "name") String name,
                    @RequestParam(value = "year") String year) {
        int result = albumDAO.create(name,year);
        if(result != 0) {
            return WebResponse.getOKResponse("Create Album Finish");
        } else {
            return WebResponse.getErrorResponse("Create Album Faild");
        }
    }

    @ApiOperation("删除专辑")
    @RequestMapping(value="delete",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse delete(@RequestParam(value = "id") int id) {
        int result = albumDAO.delete(id);
        if(result != 0) {
            return WebResponse.getOKResponse("Remove Album Finish");
        } else {
            return WebResponse.getErrorResponse("Remove Album Faild");
        }
    }

    @ApiOperation("更新专辑")
    @RequestMapping(value="update",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse update(@RequestParam(value = "id") int id,
                    @RequestParam(value = "name") String name,
                    @RequestParam(value = "year") String year) {
        int result = albumDAO.update(id,name,year);
        if(result != 0) {
            return WebResponse.getOKResponse("Update Album Finish");
        } else {
            return WebResponse.getErrorResponse("Update Album Faild");
        }
    }
}
