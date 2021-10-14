package com.epam.esm.zotov.module2.dal.dao.certificate;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.esm.zotov.module2.dal.model.Certificate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:certificate.sql.properties")
public class CertificateMapper implements RowMapper<Certificate> {
    @Value("${cert.id}")
    private String idColumn;
    @Value("${cert.name}")
    private String nameColumn;
    @Value("${cert.description}")
    private String descriptionColumn;
    @Value("${cert.price}")
    private String priceColumn;
    @Value("${cert.duration}")
    private String durationColumn;
    @Value("${cert.createDate}")
    private String createDateColumn;
    @Value("${cert.updateDate}")
    private String updateDateColumn;

    @Override
    public Certificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Certificate certificate = new Certificate();

        certificate.setCertificateId(rs.getInt(idColumn));
        certificate.setName(rs.getString(nameColumn));
        certificate.setDescription(rs.getString(descriptionColumn));
        certificate.setPrice(rs.getBigDecimal(priceColumn));
        certificate.setDuration(rs.getShort(durationColumn));
        certificate.setCreateDate(rs.getDate(createDateColumn).toInstant());
        certificate.setCreateDate(rs.getDate(updateDateColumn).toInstant());

        return certificate;
    }
}