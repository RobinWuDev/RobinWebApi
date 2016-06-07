package com.robinwu.api.dao;

//import com.robinwu.api.config.RootConfig;
import com.robinwu.api.domain.MingYan;
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
public class IMingYanDAOTest {

    @Autowired
    private IMingYanDAO domainDAO;

    @Test
    @Transactional
    public void testCreate() {
        int result = domainDAO.create("author1","content1",10);
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testCreateWithMingYan() {
        MingYan tempObj = TestData.getTempMingYan();;
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testGetObj() {
        MingYan tempObj= TestData.getTempMingYan();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        MingYan album = domainDAO.getMingYan(tempObj.getId());
        assertEquals(album,tempObj);
    }

    @Test
    @Transactional
    public void testListObj() {
        MingYan tempObj = TestData.getTempMingYan();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        List<MingYan> objList = domainDAO.listMingYan();
        assertEquals(objList.size() > 0,true);
        assertEquals(objList.get(objList.size()-1),tempObj);
    }

    @Test
    @Transactional
    public void testListObjWithCategoryId() {
        MingYan tempObj = TestData.getTempMingYan();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        List<MingYan> objList = domainDAO.listMingYan(tempObj.getCategoryId());
        assertEquals(objList.size() > 0,true);
        assertEquals(objList.get(objList.size()-1),tempObj);
    }

    @Test
    @Transactional
    public void testDelete() {
        MingYan tempObj = TestData.getTempMingYan();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.delete(tempObj.getId());
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testUpdate() {
        MingYan tempObj = TestData.getTempMingYan();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.update(tempObj.getId(),"NewAuthor","NewContent",20);
        assertEquals(result,1);

        MingYan newObj = domainDAO.getMingYan(tempObj.getId());
        assertEquals(newObj.getAuthor(),"NewAuthor");
        assertEquals(newObj.getContent(),"NewContent");
        assertEquals(newObj.getCategoryId(),Integer.valueOf(20));
    }

    @Test
    @Transactional
    public void testUpdateAuthor() {
        MingYan tempObj = TestData.getTempMingYan();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.update(tempObj.getId(),"NewAuthor",null,0);
        assertEquals(result,1);

        MingYan newObj = domainDAO.getMingYan(tempObj.getId());
        assertEquals(newObj.getAuthor(),"NewAuthor");
        assertEquals(newObj.getContent(),tempObj.getContent());
        assertEquals(newObj.getCategoryId(),tempObj.getCategoryId());
    }

    @Test
    @Transactional
    public void testUpdateContent() {
        MingYan tempObj = TestData.getTempMingYan();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.update(tempObj.getId(),null,"NewContent",0);
        assertEquals(result,1);

        MingYan newObj = domainDAO.getMingYan(tempObj.getId());
        assertEquals(newObj.getAuthor(),tempObj.getAuthor());
        assertEquals(newObj.getContent(),"NewContent");
        assertEquals(newObj.getCategoryId(),tempObj.getCategoryId());
    }

    @Test
    @Transactional
    public void testUpdateCategory() {
        MingYan tempObj = TestData.getTempMingYan();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.update(tempObj.getId(),null,null,20);
        assertEquals(result,1);

        MingYan newObj = domainDAO.getMingYan(tempObj.getId());
        assertEquals(newObj.getAuthor(),tempObj.getAuthor());
        assertEquals(newObj.getContent(),tempObj.getContent());
        assertEquals(newObj.getCategoryId(),Integer.valueOf(20));
    }

}
