package com.epam.esm.zotov.module2.dal.dao.certificate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.esm.zotov.module2.dal.dao.tag.TagDao;
import com.epam.esm.zotov.module2.dal.model.Certificate;
import com.epam.esm.zotov.module2.dal.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CertificateMapper certificateMapper;
    @Autowired
    private TagDao tagDao;

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
                .ofNullable(jdbcTemplate.queryForObject(findByIdSQL, certificateMapper, id));

        certificate.ifPresent(
                cert -> cert.setTags(jdbcTemplate.queryForList(getTagsSQL, String.class, cert.getCertificateId())));
        return certificate;
    }

    @Override
    @Transactional
    public boolean save(Certificate object) {
        int result = jdbcTemplate.update(insertSQL, object.getName(), object.getDescription(), object.getPrice(),
                object.getDuration(), object.getCreateDate(), object.getLastUpdateDate());
        saveTags(object);
        return result == 1;
    }

    @Override
    public boolean delete(long id) {
        int result = jdbcTemplate.update(deleteSQL, id);
        return result == 1;
    }

    @Override
    @Transactional
    public boolean selectiveUpdate(Certificate updatedCertificate) {
        updateTags(updatedCertificate);
        int result = jdbcTemplate.update(selectiveUpdateSQL, updatedCertificate.getName(),
                updatedCertificate.getDescription(), updatedCertificate.getPrice(), updatedCertificate.getDuration());
        return result == 1;
    }

    private void saveTags(Certificate certificate) {
        List<String> tags = certificate.getTags();
        tags.stream().forEach(tag -> {
            createTagIfNotExists(tag);
            int tagId = tagDao.getByName(tag).get().getTagId();
            jdbcTemplate.update(addTagSQL, certificate.getCertificateId(), tagId);
        });
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