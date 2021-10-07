package com.epam.esm.zotov.module2.dal.dao.certificate;

import java.util.List;
import java.util.Optional;

import com.epam.esm.zotov.module2.dal.model.Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:db.properties")
public class CertificateDaoImpl implements CertificateDao {
    @Value("cert.sql.all")
    private String getAllQuerry;
    @Value("cert.sql.findById")
    private String findByIdQuerry;
    @Value("cert.sql.insert")
    private String insertQuerry;
    @Value("cert.sql.delete")
    private String deleteQuerry;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CertificateMapper certificateMapper;

    @Override
    public List<Certificate> getAll() {
        List<Certificate> certificates = jdbcTemplate.query(getAllQuerry, certificateMapper);
        return certificates;
    }

    @Override
    public Optional<Certificate> getById(long id) {
        Optional<Certificate> certificate = Optional
                .of(jdbcTemplate.queryForObject(findByIdQuerry, certificateMapper, id));
        return certificate;
    }

    @Override
    public boolean save(Certificate object) {
        long result = jdbcTemplate.update(insertQuerry, object.getDescription(), object.getPrice(), object.getDuration(),
                object.getCreateDate(), object.getLastUpdateDate());
        return result == 1;
    }

    @Override
    public boolean delete(long id) {
        long result = jdbcTemplate.update(deleteQuerry, id);
        return result == 1;
    }

    @Override
    public boolean selectiveUpdate(Certificate updatedCertificate, List<String> updatedFields) {
        // TODO Auto-generated method stub
        return false;
    }
}