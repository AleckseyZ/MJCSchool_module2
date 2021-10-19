package com.epam.esm.zotov.module2.dataaccess.dao.certificate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import com.epam.esm.zotov.module2.dataaccess.model.Certificate;

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
        Certificate certificate = new Certificate(rs.getLong(idColumn), rs.getString(nameColumn),
                rs.getString(descriptionColumn), rs.getBigDecimal(priceColumn), rs.getShort(durationColumn),
                rs.getTimestamp(createDateColumn).toInstant(), null, null);
        Optional<Timestamp> updateDate = Optional.ofNullable(rs.getTimestamp(updateDateColumn));

        if (updateDate.isPresent()) {
            certificate.setLastUpdateDate(updateDate.get().toInstant());
        }
        return certificate;
    }
}