package com.robinwu.api.controller;

import com.robinwu.api.dao.IMingYanCategoryDAO;
import com.robinwu.api.dao.IMingYanCategoryDAO;
import com.robinwu.api.domain.MingYanCategory;
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
@Api(value="mingyancategory")
@Controller
@RequestMapping(value = "mingyancategory")
public class MingYanCategoryController {

    @Autowired
    private IMingYanCategoryDAO domainDAO;

    public MingYanCategoryController() {
    }

    public MingYanCategoryController(IMingYanCategoryDAO dao) {
        this.domainDAO = dao;
    }

    @ApiOperation("获取全部的名言类别列表")
    @RequestMapping(value="list",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse list() {
        List<MingYanCategory> objs = domainDAO.listCategory();
        return WebResponse.getOKResponse(objs);
    }


    @ApiOperation("根据名言类别id,获取名言类别信息")
    @RequestMapping(value="getCategory",method=RequestMethod.GET,produces = "application/json")
    public  @ResponseBody
    WebResponse getMingYanCategory(@RequestParam(value="id") Integer id) {
        MingYanCategory MingYanCategory = domainDAO.getCategory(id);
        return WebResponse.getOKResponse(MingYanCategory);
    }

    @ApiOperation("添加名言类别")
    @RequestMapping(value="add",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse add(@RequestParam(value = "name") String name) {
        int result = domainDAO.create(name);
        if(result != 0) {
            return WebResponse.getOKResponse("Create MingYanCategory Finish");
        } else {
            return WebResponse.getErrorResponse("Create MingYanCategory Faild");
        }
    }

    @ApiOperation("删除名言类别")
    @RequestMapping(value="delete",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse delete(@RequestParam(value = "id") int id) {
        int result = domainDAO.delete(id);
        if(result != 0) {
            return WebResponse.getOKResponse("Remove MingYanCategory Finish");
        } else {
            return WebResponse.getErrorResponse("Remove MingYanCategory Faild");
        }
    }

    @ApiOperation("更新名言类别")
    @RequestMapping(value="update",method=RequestMethod.POST,produces = "application/json")
    public  @ResponseBody
    WebResponse update(@RequestParam(value = "id") int id,
                    @RequestParam(value = "name") String name) {
        int result = domainDAO.update(id,name);
        if(result != 0) {
            return WebResponse.getOKResponse("Update MingYanCategory Finish");
        } else {
            return WebResponse.getErrorResponse("Update MingYanCategory Faild");
        }
    }
}
