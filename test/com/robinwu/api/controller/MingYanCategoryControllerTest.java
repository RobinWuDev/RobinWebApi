package com.robinwu.api.controller;

import com.robinwu.api.dao.IMingYanCategoryDAO;
import com.robinwu.api.domain.MingYanCategory;
import com.robinwu.api.domain.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Robin on 16/6/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/WEB-servlet.xml"})
@ActiveProfiles("test")
public class MingYanCategoryControllerTest {

    @Test
    public void testList() throws Exception{
        MingYanCategory mingYanCategory = TestData.getTempMingYanCategory();
        List<MingYanCategory> mingYanCategorysList = new ArrayList<MingYanCategory>();
        mingYanCategorysList.add(mingYanCategory);

        IMingYanCategoryDAO mingYanCategoryDAO = mock(IMingYanCategoryDAO.class);

        when(mingYanCategoryDAO.listCategory()).thenReturn(mingYanCategorysList);

        MingYanCategoryController controller = new MingYanCategoryController(mingYanCategoryDAO);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/mingyancategory/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(mingYanCategorysList)));
    }

    @Test
    public void testGetMingYanCategory() throws Exception{
        MingYanCategory mingYanCategory = TestData.getTempMingYanCategory();

        IMingYanCategoryDAO mingYanCategoryDAO = mock(IMingYanCategoryDAO.class);

        when(mingYanCategoryDAO.getCategory(mingYanCategory.getId())).thenReturn(mingYanCategory);

        MingYanCategoryController controller = new MingYanCategoryController(mingYanCategoryDAO);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/mingyancategory/getCategory?id="+mingYanCategory.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(mingYanCategory)));
    }


    @Test
    public void add() throws Exception{
        IMingYanCategoryDAO mingYanCategoryDao = mock(IMingYanCategoryDAO.class);

        when(mingYanCategoryDao.create("test")).thenReturn(1);

        MingYanCategoryController controller = new MingYanCategoryController(mingYanCategoryDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/mingyancategory/add")
                .param("name","test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.createJson("MingYanCategory")));
    }

    @Test
    public void remove() throws Exception{
        IMingYanCategoryDAO mingYanCategoryDao = mock(IMingYanCategoryDAO.class);

        when(mingYanCategoryDao.delete(11)).thenReturn(1);

        MingYanCategoryController controller = new MingYanCategoryController(mingYanCategoryDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/mingyancategory/delete")
                .param("id","11"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.deleteJson("MingYanCategory")));
    }

    @Test
    public void update() throws Exception{
        IMingYanCategoryDAO mingYanCategoryDao = mock(IMingYanCategoryDAO.class);

        when(mingYanCategoryDao.update(11,"test")).thenReturn(1);

        MingYanCategoryController controller = new MingYanCategoryController(mingYanCategoryDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/mingyancategory/update")
                .param("id","11")
                .param("name","test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.updateJson("MingYanCategory")));
    }
}
