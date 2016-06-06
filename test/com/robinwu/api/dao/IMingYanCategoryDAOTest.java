package com.robinwu.api.dao;

//import com.robinwu.api.config.RootConfig;
import com.robinwu.api.domain.MingYanCategory;
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
public class IMingYanCategoryDAOTest {

    @Autowired
    private IMingYanCategoryDAO domainDAO;

    @Test
    @Transactional
    public void testCreate() {
        int result = domainDAO.create("Test1");
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testCreateWithObj() {
        MingYanCategory tempObj = TestData.getTempMingYanCategory();;
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testGetObj() {
        MingYanCategory tempObj= TestData.getTempMingYanCategory();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        MingYanCategory album = domainDAO.getCategory(tempObj.getId());
        assertEquals(album,tempObj);
    }

    @Test
    @Transactional
    public void testListObj() {
        MingYanCategory tempObj = TestData.getTempMingYanCategory();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        List<MingYanCategory> objList = domainDAO.listCategory();
        assertEquals(objList.size() > 0,true);
        assertEquals(objList.get(objList.size()-1),tempObj);
    }

    @Test
    @Transactional
    public void testDelete() {
        MingYanCategory tempObj = TestData.getTempMingYanCategory();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.delete(tempObj.getId());
        assertEquals(result,1);
    }

    @Test
    @Transactional
    public void testUpdate() {
        MingYanCategory tempObj = TestData.getTempMingYanCategory();
        int result = domainDAO.create(tempObj);
        assertEquals(result,1);

        result = domainDAO.update(tempObj.getId(),"NewCategory");
        assertEquals(result,1);

        MingYanCategory newObj = domainDAO.getCategory(tempObj.getId());
        assertEquals(newObj.getName(),"NewCategory");
    }
}
