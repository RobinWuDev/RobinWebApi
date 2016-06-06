package com.robinwu.api.dao;

//import com.robinwu.api.config.RootConfig;
import com.robinwu.api.domain.Album;
import com.robinwu.api.domain.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Robin on 16/6/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/WEB-servlet.xml"})
@ActiveProfiles("test")
public class IAlbumDAOTest {

    @Autowired
    private IAlbumDAO albumDAO;

    @Test
    @Transactional
    public void testCreate() {
        int result = albumDAO.create("Test1","1990");
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testCreateWithAlbum() {
        Album tempAlbum = TestData.getTempAlbum();;
        int result = albumDAO.create(tempAlbum);
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testGetAlbum() {
        Album tempAlbum = TestData.getTempAlbum();
        int result = albumDAO.create(tempAlbum);
        assertEquals(result,1);

        Album album = albumDAO.getAlbum(tempAlbum.getId());
        assertEquals(album,tempAlbum);
    }

    @Test
    @Transactional
    public void testListAlbum() {
        Album tempAlbum = TestData.getTempAlbum();
        int result = albumDAO.create(tempAlbum);
        assertEquals(result,1);

        List<Album> albumList = albumDAO.listAlbum();
        assertEquals(albumList.size() > 0,true);
        assertEquals(albumList.get(albumList.size()-1),tempAlbum);
    }

    @Test
    @Transactional
    public void testDelete() {
        Album tempAlbum = TestData.getTempAlbum();
        int result = albumDAO.create(tempAlbum);
        assertEquals(result,1);

        result = albumDAO.delete(tempAlbum.getId());
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testUpdate() {
        Album tempAlbum = TestData.getTempAlbum();
        int result = albumDAO.create(tempAlbum);
        assertEquals(result,1);

        result = albumDAO.update(tempAlbum.getId(),"Test5","1998");
        assertEquals(result,1);

        Album newAlbum = albumDAO.getAlbum(tempAlbum.getId());
        assertEquals(newAlbum.getName(),"Test5");
        assertEquals(newAlbum.getYear(),"1998");
    }
}
