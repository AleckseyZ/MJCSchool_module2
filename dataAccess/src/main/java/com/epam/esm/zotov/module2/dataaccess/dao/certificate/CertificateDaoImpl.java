package com.epam.esm.zotov.module2.dataaccess.dao.certificate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.esm.zotov.module2.dataaccess.dao.tag.TagDao;
import com.epam.esm.zotov.module2.dataaccess.model.Certificate;
import com.epam.esm.zotov.module2.dataaccess.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@PropertySource("classpath:certificate.sql.properties")
public class CertificateDaoImpl implements CertificateDao {
    @Value("${cert.sql.all}")
    private String getAllSQL;
    @Value("${cert.sql.findById}")
    private String findByIdSQL;
    @Value("${cert.sql.insert}")
    private String insertSQL;
    @Value("${cert.sql.delete}")
    private String deleteSQL;
    @Value("${cert.sql.selectiveUpdate}")
    private String selectiveUpdateSQL;
    @Value("${cert.sql.tags}")
    private String getTagsSQL;
    @Value("${cert.sql.addTag}")
    private String addTagSQL;
    @Value("${cert.sql.removeTag}")
    private String removeTagSQL;
    @Value("${cert.id}")
    private String certIdColumn;
    private JdbcTemplate jdbcTemplate;
    private CertificateMapper certificateMapper;
    private TagDao tagDao;
    private int expectedSaveOrDeleteResutNumber = 1;

    @Autowired
    public CertificateDaoImpl(JdbcTemplate jdbcTemplate, CertificateMapper certificateMapper, TagDao tagDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.certificateMapper = certificateMapper;
        this.tagDao = tagDao;
    }

    @Override
    public List<Certificate> getAll() {
        List<Certificate> certificates = jdbcTemplate.query(getAllSQL, certificateMapper);

        certificates.stream().forEach(certificate -> certificate
                .setTags(jdbcTemplate.queryForList(getTagsSQL, String.class, certificate.getCertificateId())));
        return certificates;
    }

    @Override
    public Optional<Certificate> getById(long id) {
        Optional<Certificate> certificate = Optional
                .ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query(findByIdSQL, certificateMapper, id)));

        certificate.ifPresent(
                cert -> cert.setTags(jdbcTemplate.queryForList(getTagsSQL, String.class, cert.getCertificateId())));
        return certificate;
    }

    @Override
    @Transactional
    public boolean save(Certificate certificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(insertSQL, new String[] { certIdColumn });
                statement.setString(1, certificate.getName());
                statement.setString(2, certificate.getDescription());
                statement.setBigDecimal(3, certificate.getPrice());
                statement.setShort(4, certificate.getDuration());
                return statement;
            }
        }, keyHolder);

        certificate.setCertificateId(keyHolder.getKey().longValue());
        saveTags(certificate);

        boolean isSuccessful = (expectedSaveOrDeleteResutNumber == affectedRows);
        return isSuccessful;
    }

    @Override
    public boolean delete(long id) {
        boolean isSuccessful = (jdbcTemplate.update(deleteSQL, id) == expectedSaveOrDeleteResutNumber);
        return isSuccessful;
    }

    @Override
    @Transactional
    public boolean selectiveUpdate(Certificate updatedCertificate) {
        updateTags(updatedCertificate);
        int affectedRows = jdbcTemplate.update(selectiveUpdateSQL, updatedCertificate.getName(),
                updatedCertificate.getDescription(), updatedCertificate.getPrice(), updatedCertificate.getDuration(),
                updatedCertificate.getCertificateId());
        boolean isSuccessful = (expectedSaveOrDeleteResutNumber == affectedRows);
        return isSuccessful;
    }

    private void saveTags(Certificate certificate) {
        List<String> tags = certificate.getTags();
        if (Objects.nonNull(tags)) {
            tags.stream().forEach(tag -> {
                createTagIfNotExists(tag);
                long tagId = tagDao.getByName(tag).get().getTagId();
                jdbcTemplate.update(addTagSQL, certificate.getCertificateId(), tagId);
            });
        }
    }

    private void updateTags(Certificate certificate) {
        List<String> currentTags = jdbcTemplate.queryForList(getTagsSQL, String.class, certificate.getCertificateId());
        List<String> updatedTags = certificate.getTags();

        List<String> removedTags = currentTags.stream().filter(tag -> !updatedTags.contains(tag))
                .collect(Collectors.toList());
        removedTags.stream().forEach(removedTag -> jdbcTemplate.update(removeTagSQL, certificate.getCertificateId(),
                tagDao.getByName(removedTag).get().getTagId()));

        List<String> newTags = updatedTags.stream().filter(tag -> !currentTags.contains(tag))
                .collect(Collectors.toList());
        newTags.stream().forEach(newTag -> {
            createTagIfNotExists(newTag);
            jdbcTemplate.update(addTagSQL, certificate.getCertificateId(), tagDao.getByName(newTag).get().getTagId());
        });
    }

    private void createTagIfNotExists(String tag) {
        if (tagDao.getByName(tag).isEmpty()) {
            Tag tagObject = new Tag();
            tagObject.setTagName(tag);
            tagDao.save(tagObject);
        }
    }
}