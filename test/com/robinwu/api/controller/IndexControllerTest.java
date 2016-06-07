package com.robinwu.api.controller;

import com.robinwu.api.dao.IAlbumDAO;
import com.robinwu.api.dao.IMingYanDAO;
import com.robinwu.api.dao.IMusicDAO;
import com.robinwu.api.domain.Album;
import com.robinwu.api.domain.MingYan;
import com.robinwu.api.domain.Music;
import com.robinwu.api.domain.TestData;
import com.robinwu.api.vo.AlbumVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
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
public class IndexControllerTest {

    @Test
    public void testMusicList() throws Exception{
        Album album = TestData.getTempAlbum();
        List<Album> albumsList = new ArrayList<Album>();
        albumsList.add(album);

        Music music = TestData.getTempMusic();
        List<Music> musicList = new ArrayList<Music>();
        musicList.add(music);

        ArrayList<AlbumVO> expectList = new ArrayList<AlbumVO>();
        AlbumVO albumVO = new AlbumVO(album);
        albumVO.setMusics(musicList);
        expectList.add(albumVO);

        IAlbumDAO albumDao = mock(IAlbumDAO.class);
        IMusicDAO musicDao = mock(IMusicDAO.class);

        when(albumDao.listAlbum()).thenReturn(albumsList);
        when(musicDao.listMusic(album.getId())).thenReturn(musicList);

        IndexController controller = new IndexController(musicDao,albumDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/index/musicList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(expectList)));
    }

    @Test
    public void testRandMusic() throws Exception{
        Album album = TestData.getTempAlbum();

        Music music = TestData.getTempMusic();
        List<Music> musicList = new ArrayList<Music>();
        musicList.add(music);

        HashMap<String,Object> expectMap = new HashMap<String, Object>();
        expectMap.put("id",music.getId());
        expectMap.put("name",music.getName());
        expectMap.put("albumId",music.getAlbumId());
        expectMap.put("albumName",album.getName());


        IAlbumDAO albumDao = mock(IAlbumDAO.class);
        IMusicDAO musicDao = mock(IMusicDAO.class);

        when(albumDao.getAlbum(music.getAlbumId())).thenReturn(album);
        when(musicDao.listMusic()).thenReturn(musicList);

        IndexController controller = new IndexController(musicDao,albumDao);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/index/randMusic"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(expectMap)));
    }

    @Test
    public void testRandMingYan() throws Exception{
        MingYan mingYan = TestData.getTempMingYan();
        List<MingYan> mingYanList = new ArrayList<MingYan>();
        mingYanList.add(mingYan);

        IMingYanDAO mingYanDAO = mock(IMingYanDAO.class);

        when(mingYanDAO.listMingYan()).thenReturn(mingYanList);

        IndexController controller = new IndexController(mingYanDAO);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/index/randMingYan"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(TestData.okJson(mingYan)));
    }

}
