package com.robinwu.api.controller;

import com.robinwu.api.dao.IMingYanDAO;
import com.robinwu.api.domain.MingYan;
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
@Api(value="mingyan")
@Controller
@RequestMapping(value = "mingyan")
public class MingYanController {

    @Autowired
    private IMingYanDAO domainDAO;

    public MingYanController() {
    }

    public MingYanController(IMingYanDAO dao) {
        this.domainDAO = dao;
    }

    @ApiOperation("根据类别id,获取名言列表")
    @ApiParam(name = "categoryId",value = "类别ID,如果不传,为获取全部",required = false)
    @RequestMapping(value="list",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse list(@RequestParam(value="categoryId",defaultValue = "0",required = false) Integer categoryId) {
        List<MingYan> objs = null;
        if (categoryId == 0) {
            objs = domainDAO.listMingYan();
        } else {
            objs = domainDAO.listMingYan(categoryId);
        }
        return WebResponse.getOKResponse(objs);
    }

    @ApiOperation("根据名言id,获取名言信息")
    @RequestMapping(value="getMingYan",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse getMingYan(@RequestParam(value="id") Integer id) {
        MingYan MingYan = domainDAO.getMingYan(id);
        return WebResponse.getOKResponse(MingYan);
    }

    @ApiOperation("添加名言")
    @RequestMapping(value="add",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse add(@RequestParam(value = "author") String author,
                    @RequestParam(value = "content") String content,
                    @RequestParam(value = "categoryId") Integer categoryId) {
        int result = domainDAO.create(author,content,categoryId);
        if(result != 0) {
            return WebResponse.getOKResponse("Create MingYan Finish");
        } else {
            return WebResponse.getErrorResponse("Create MingYan Faild");
        }
    }

    @ApiOperation("删除名言")
    @RequestMapping(value="delete",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse delete(@RequestParam(value = "id") int id) {
        int result = domainDAO.delete(id);
        if(result != 0) {
            return WebResponse.getOKResponse("Remove MingYan Finish");
        } else {
            return WebResponse.getErrorResponse("Remove MingYan Faild");
        }
    }

    @ApiOperation("更新名言")
    @RequestMapping(value="update",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse update(@RequestParam(value = "id") Integer id,
                       @RequestParam(value = "author",required = false) String author,
                       @RequestParam(value = "content",required = false) String content,
                       @RequestParam(value = "categoryId",defaultValue = "0",required = false) Integer categoryId) {
        int result = domainDAO.update(id,author,content,categoryId);
        if(result == 1) {
            return WebResponse.getOKResponse("Update MingYan Finish");
        } else {
            return WebResponse.getErrorResponse("Update MingYan Faild");
        }
    }
}
