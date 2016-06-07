package com.robinwu.api.controller;

import com.robinwu.api.dao.IMingYanDAO;
import com.robinwu.api.domain.MingYan;
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
public class MingYanControllerTest {

    @Test
    public void testList() throws Exception{
        MingYan mingyan = TestData.getTempMingYan();
        List<MingYan> mingyansList = new ArrayList<MingYan>();
        mingyansList.add(mingyan);

        IMingYanDAO mingyanDAO = mock(IMingYanDAO.class);

        when(mingyanDAO.listMingYan()).thenReturn(mingyansList);

        MingYanController controller = new MingYanController(mingyanDAO);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/mingyan/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(mingyansList)));
    }

    @Test
    public void testListWithId() throws Exception{
        MingYan mingyan = TestData.getTempMingYan();
        List<MingYan> mingyansList = new ArrayList<MingYan>();
        mingyansList.add(mingyan);

        IMingYanDAO mingyanDAO = mock(IMingYanDAO.class);

        when(mingyanDAO.listMingYan(mingyan.getCategoryId())).thenReturn(mingyansList);

        MingYanController controller = new MingYanController(mingyanDAO);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/mingyan/list?categoryId="+mingyan.getCategoryId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(mingyansList)));
    }

    @Test
    public void testGetMingYan() throws Exception{
        MingYan mingyan = TestData.getTempMingYan();

        IMingYanDAO mingyanDAO = mock(IMingYanDAO.class);

        when(mingyanDAO.getMingYan(mingyan.getId())).thenReturn(mingyan);

        MingYanController controller = new MingYanController(mingyanDAO);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/mingyan/getMingYan?id="+mingyan.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(mingyan)));
    }


    @Test
    public void add() throws Exception{
        IMingYanDAO mingyanDao = mock(IMingYanDAO.class);

        when(mingyanDao.create("author","content",10)).thenReturn(1);

        MingYanController controller = new MingYanController(mingyanDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/mingyan/add")
                .param("author","author")
                .param("content","content")
                .param("categoryId","10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.createJson("MingYan")));
    }

    @Test
    public void remove() throws Exception{
        IMingYanDAO mingyanDao = mock(IMingYanDAO.class);

        when(mingyanDao.delete(11)).thenReturn(1);

        MingYanController controller = new MingYanController(mingyanDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/mingyan/delete")
                .param("id","11"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.deleteJson("MingYan")));
    }

    @Test
    public void update() throws Exception{
        IMingYanDAO mingyanDao = mock(IMingYanDAO.class);

        when(mingyanDao.update(11,"author","content",10)).thenReturn(1);

        MingYanController controller = new MingYanController(mingyanDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/mingyan/update")
                .param("id","11")
                .param("author","author")
                .param("content","content")
                .param("categoryId","10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.updateJson("MingYan")));
    }

    @Test
    public void updateAuthor() throws Exception{
        IMingYanDAO mingyanDao = mock(IMingYanDAO.class);

        when(mingyanDao.update(11,"author",null,0)).thenReturn(1);

        MingYanController controller = new MingYanController(mingyanDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/mingyan/update")
                .param("id","11")
                .param("author","author"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.updateJson("MingYan")));
    }

    @Test
    public void updateContent() throws Exception{
        IMingYanDAO mingyanDao = mock(IMingYanDAO.class);

        when(mingyanDao.update(11,null,"content",0)).thenReturn(1);

        MingYanController controller = new MingYanController(mingyanDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/mingyan/update")
                .param("id","11")
                .param("content","content"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.updateJson("MingYan")));
    }

    @Test
    public void updateCategoyrId() throws Exception{
        IMingYanDAO mingyanDao = mock(IMingYanDAO.class);

        when(mingyanDao.update(11,null,null,10)).thenReturn(1);

        MingYanController controller = new MingYanController(mingyanDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/mingyan/update")
                .param("id","11")
                .param("categoryId","10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.updateJson("MingYan")));
    }



}
