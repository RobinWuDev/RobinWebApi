package com.robinwu.api.controller;

import com.robinwu.api.dao.IMusicDAO;
import com.robinwu.api.domain.Music;
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
public class MusicControllerTest {

    @Test
    public void testList() throws Exception{
        Music music = TestData.getTempMusic();
        List<Music> musicsList = new ArrayList<Music>();
        musicsList.add(music);

        IMusicDAO musicDAO = mock(IMusicDAO.class);

        when(musicDAO.listMusic()).thenReturn(musicsList);

        MusicController controller = new MusicController(musicDAO);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/music/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(musicsList)));
    }

    @Test
    public void testListWithId() throws Exception{
        Music music = TestData.getTempMusic();
        List<Music> musicsList = new ArrayList<Music>();
        musicsList.add(music);

        IMusicDAO musicDAO = mock(IMusicDAO.class);

        when(musicDAO.listMusic(music.getAlbumId())).thenReturn(musicsList);

        MusicController controller = new MusicController(musicDAO);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/music/list?albumId="+music.getAlbumId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(musicsList)));
    }

    @Test
    public void testGetMusic() throws Exception{
        Music music = TestData.getTempMusic();

        IMusicDAO musicDAO = mock(IMusicDAO.class);

        when(musicDAO.getMusic(music.getId())).thenReturn(music);

        MusicController controller = new MusicController(musicDAO);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/music/getMusic?id="+music.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(music)));
    }


    @Test
    public void add() throws Exception{
        IMusicDAO musicDao = mock(IMusicDAO.class);

        when(musicDao.create("test",10)).thenReturn(1);

        MusicController controller = new MusicController(musicDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/music/add")
                .param("name","test")
                .param("albumId","10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.createJson("Music")));
    }

    @Test
    public void remove() throws Exception{
        IMusicDAO musicDao = mock(IMusicDAO.class);

        when(musicDao.delete(11)).thenReturn(1);

        MusicController controller = new MusicController(musicDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/music/delete")
                .param("id","11"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.deleteJson("Music")));
    }

    @Test
    public void update() throws Exception{
        IMusicDAO musicDao = mock(IMusicDAO.class);

        when(musicDao.update(11,"test",10)).thenReturn(1);

        MusicController controller = new MusicController(musicDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/music/update")
                .param("id","11")
                .param("name","test")
                .param("albumId","10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.updateJson("Music")));
    }

    @Test
    public void updateName() throws Exception{
        IMusicDAO musicDao = mock(IMusicDAO.class);

        when(musicDao.update(11,"test",0)).thenReturn(1);

        MusicController controller = new MusicController(musicDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/music/update")
                .param("id","11")
                .param("name","test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.updateJson("Music")));
    }

    @Test
    public void updateAlbumId() throws Exception{
        IMusicDAO musicDao = mock(IMusicDAO.class);

        when(musicDao.update(11,null,10)).thenReturn(1);

        MusicController controller = new MusicController(musicDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/music/update")
                .param("id","11")
                .param("albumId","10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.updateJson("Music")));
    }

}
