package com.robinwu.api.dao;

//import com.robinwu.api.config.RootConfig;
import com.robinwu.api.domain.Album;
import com.robinwu.api.domain.Music;
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
public class IMusicDAOTest {

    @Autowired
    private IMusicDAO domainDAO;

    @Test
    @Transactional
    public void testCreate() {
        int result = domainDAO.create("Test1",10);
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testCreateWithMusic() {
        Music tempObj = TestData.getTempMusic();;
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testGetObj() {
        Music tempObj= TestData.getTempMusic();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        Music album = domainDAO.getMusic(tempObj.getId());
        assertEquals(album,tempObj);
    }

    @Test
    @Transactional
    public void testListObj() {
        Music tempObj = TestData.getTempMusic();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        List<Music> objList = domainDAO.listMusic();
        assertEquals(objList.size() > 0,true);
        assertEquals(objList.get(objList.size()-1),tempObj);
    }

    @Test
    @Transactional
    public void testListObjWithCategoryId() {
        Music tempObj = TestData.getTempMusic();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        List<Music> objList = domainDAO.listMusic(tempObj.getAlbumId());
        assertEquals(objList.size() > 0,true);
        assertEquals(objList.get(objList.size()-1),tempObj);
    }

    @Test
    @Transactional
    public void testDelete() {
        Music tempObj = TestData.getTempMusic();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.delete(tempObj.getId());
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testUpdate() {
        Music tempObj = TestData.getTempMusic();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.update(tempObj.getId(),"NewMusic",20);
        assertEquals(result,1);

        Music newObj = domainDAO.getMusic(tempObj.getId());
        assertEquals(newObj.getName(),"NewMusic");
        assertEquals(newObj.getAlbumId(),Integer.valueOf(20));
    }

    @Test
    @Transactional
    public void testUpdateName() {
        Music tempObj = TestData.getTempMusic();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.update(tempObj.getId(),"NewMusic",0);
        assertEquals(result,1);

        Music newObj = domainDAO.getMusic(tempObj.getId());
        assertEquals(newObj.getName(),"NewMusic");
        assertEquals(newObj.getAlbumId(),tempObj.getAlbumId());
    }

    @Test
    @Transactional
    public void testUpdateAlbumId() {
        Music tempObj = TestData.getTempMusic();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.update(tempObj.getId(),null,20);
        assertEquals(result,1);

        Music newObj = domainDAO.getMusic(tempObj.getId());
        assertEquals(newObj.getName(),tempObj.getName());
        assertEquals(newObj.getAlbumId(),Integer.valueOf(20));
    }

}
