package com.epam.esm.zotov.module2.dataaccess.dao.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Optional;

import com.epam.esm.zotov.module2.dataaccess.DataAccessConfig;
import com.epam.esm.zotov.module2.dataaccess.DataAccessTestConfig;
import com.epam.esm.zotov.module2.dataaccess.model.Tag;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DataAccessConfig.class, DataAccessTestConfig.class, TagDaoImpl.class,
        TagMapper.class })
@Sql("classpath:/test/schema.sql")
public class TagDaoImplTest {
    @Autowired
    TagDaoImpl tagDao;

    @Test
    @Transactional
    void createTest() {
        boolean isSuccesful = tagDao.save(new Tag(1L, "TEST"));
        assumeTrue(isSuccesful);
    }

    @Test
    @Transactional
    void readTest() {
        tagDao.save(new Tag(1L, "TEST"));
        Tag tag = tagDao.getById(1L).get();
        assertEquals("TEST", tag.getTagName());
    }

    @Test
    @Transactional
    void deleteTest() {
        tagDao.save(new Tag(1L, "TEST"));
        boolean isSuccesful = tagDao.delete(1L);
        assumeTrue(isSuccesful);
        Optional<Tag> tag = tagDao.getById(1L);
        assumeTrue(tag.isEmpty());
    }
}