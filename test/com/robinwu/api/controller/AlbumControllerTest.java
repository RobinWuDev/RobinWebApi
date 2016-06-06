package com.robinwu.api.controller;

import com.robinwu.api.dao.IAlbumDAO;
import com.robinwu.api.domain.Album;
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
public class AlbumControllerTest {

    @Test
    public void testList() throws Exception{
        Album album = TestData.getTempAlbum();
        List<Album> albumsList = new ArrayList<Album>();
        albumsList.add(album);

        IAlbumDAO albumDao = mock(IAlbumDAO.class);

        when(albumDao.listAlbum()).thenReturn(albumsList);

        AlbumController controller = new AlbumController(albumDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/album/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(albumsList)));
    }


    @Test
    public void add() throws Exception{
        IAlbumDAO albumDao = mock(IAlbumDAO.class);

        when(albumDao.create("test","1990")).thenReturn(1);

        AlbumController controller = new AlbumController(albumDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/album/add")
                .param("name","test")
                .param("year","1990"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.createJson("Album")));
    }

    @Test
    public void remove() throws Exception{
        IAlbumDAO albumDao = mock(IAlbumDAO.class);

        when(albumDao.delete(11)).thenReturn(1);

        AlbumController controller = new AlbumController(albumDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/album/delete")
                .param("id","11"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.deleteJson("Album")));
    }

    @Test
    public void update() throws Exception{
        IAlbumDAO albumDao = mock(IAlbumDAO.class);

        when(albumDao.update(11,"test","1990")).thenReturn(1);

        AlbumController controller = new AlbumController(albumDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/album/update")
                .param("id","11")
                .param("name","test")
                .param("year","1990"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.updateJson("Album")));
    }

}
