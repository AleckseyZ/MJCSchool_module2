package com.epam.esm.zotov.module2.dataaccess.dao.certificate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.esm.zotov.module2.dataaccess.DataAccessConfig;
import com.epam.esm.zotov.module2.dataaccess.DataAccessTestConfig;
import com.epam.esm.zotov.module2.dataaccess.dao.tag.TagDaoImpl;
import com.epam.esm.zotov.module2.dataaccess.dao.tag.TagMapper;
import com.epam.esm.zotov.module2.dataaccess.model.Certificate;

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
@ContextConfiguration(classes = { DataAccessConfig.class, DataAccessTestConfig.class, CertificateDaoImpl.class,
        CertificateMapper.class, TagMapper.class, TagDaoImpl.class })
@Sql("classpath:/test/schema.sql")
public class CertificateDaoImplTest {
    @Autowired
    CertificateDao certificateDao;

    @Test
    @Transactional
    void createTest() {
        boolean isSuccesful = certificateDao
                .save(new Certificate(1L, "TEST", "Description", BigDecimal.valueOf(1), (short) 1, null, null, null));
        assumeTrue(isSuccesful);
    }

    @Test
    @Transactional
    void deleteTest() {
        certificateDao
                .save(new Certificate(1L, "TEST", "Description", BigDecimal.valueOf(1), (short) 1, null, null, null));
        boolean isSuccesful = certificateDao.delete(1L);
        assumeTrue(isSuccesful);
        Optional<Certificate> certificate = certificateDao.getById(1L);
        assumeTrue(certificate.isEmpty());
    }

    @Test
    @Transactional
    void readTest() {
        certificateDao
                .save(new Certificate(1L, "TEST", "Description", BigDecimal.valueOf(1), (short) 1, null, null, null));
        Certificate certificate = certificateDao.getById(1L).get();
        assertEquals("TEST", certificate.getName());
    }

    @Test
    @Transactional
    void selectiveUpdateTest() {
        List<String> tags = new ArrayList<>();
        tags.add("TEST TAG");
        certificateDao
                .save(new Certificate(1L, "TEST", "Description", BigDecimal.valueOf(1), (short) 1, null, null, null));
        certificateDao.selectiveUpdate(new Certificate(1L, "CHANGED TEST", null, null, null, null, null, tags));
        Certificate certificate = certificateDao.getById(1L).get();
        assertEquals("CHANGED TEST", certificate.getName());
        assertEquals("Description", certificate.getDescription());
    }
}