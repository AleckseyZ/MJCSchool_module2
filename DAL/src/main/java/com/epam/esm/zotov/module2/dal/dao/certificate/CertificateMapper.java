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
    @Value("certificate.id")
    private String idColumn;
    @Value("certificate.description")
    private String descriptionColumn;
    @Value("certificate.price")
    private String priceColumn;
    @Value("certificate.duration")
    private String durationColumn;
    @Value("certificate.createDate")
    private String createDateColumn;
    @Value("certificate.updateDate")
    private String updateDateColumn;

    @Override
    public Certificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Certificate certificate = new Certificate();

        certificate.setCertificateId(rs.getInt(idColumn));
        certificate.setDescription(rs.getString(descriptionColumn));
        certificate.setPrice(rs.getBigDecimal(priceColumn));
        certificate.setDuration(rs.getShort(durationColumn));
        certificate.setCreateDate(rs.getDate(createDateColumn).toInstant());
        certificate.setCreateDate(rs.getDate(updateDateColumn).toInstant());

        return certificate;
    }
}