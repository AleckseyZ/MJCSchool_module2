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
@PropertySource("classpath:certificate.sql.properties")
public class CertificateDaoImpl implements CertificateDao {
    @Value("cert.sql.all")
    private String getAllQuery;
    @Value("cert.sql.findById")
    private String findByIdQuery;
    @Value("cert.sql.insert")
    private String insertQuery;
    @Value("cert.sql.delete")
    private String deleteQuery;
    @Value("cert.sql.tags")
    private String tagsQuery;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CertificateMapper certificateMapper;

    @Override
    public List<Certificate> getAll() {
        List<Certificate> certificates = jdbcTemplate.query(getAllQuery, certificateMapper);

        certificates.stream().forEach(certificate -> certificate
                .setTags(jdbcTemplate.queryForList(tagsQuery, String.class, certificate.getCertificateId())));
        return certificates;
    }

    @Override
    public Optional<Certificate> getById(long id) {
        Optional<Certificate> certificate = Optional
                .of(jdbcTemplate.queryForObject(findByIdQuery, certificateMapper, id));

        certificate.ifPresent(
                cert -> cert.setTags(jdbcTemplate.queryForList(tagsQuery, String.class, cert.getCertificateId())));
        return certificate;
    }

    @Override
    public boolean save(Certificate object) {
        long result = jdbcTemplate.update(insertQuery, object.getDescription(), object.getPrice(), object.getDuration(),
                object.getCreateDate(), object.getLastUpdateDate());
        return result == 1;
    }

    @Override
    public boolean delete(long id) {
        long result = jdbcTemplate.update(deleteQuery, id);
        return result == 1;
    }

    @Override
    public boolean selectiveUpdate(Certificate updatedCertificate, List<String> updatedFields) {
        // TODO Auto-generated method stub
        return false;
    }
}