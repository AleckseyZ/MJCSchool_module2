package com.epam.esm.zotov.module2.service.certificate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.esm.zotov.module2.dal.dao.certificate.CertificateDao;
import com.epam.esm.zotov.module2.dal.model.Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:search.properties")
public class CertificateServiceImpl implements CertificateService {
    @Value("${search.tagName}")
    String tagNameParam;
    @Value("${search.name}")
    String nameParam;
    @Value("${search.description}")
    String descriptionParam;
    @Value("${search.sortByName}")
    String sortByNameParam;
    @Value("${search.sortByDate}")
    String sortByDateParam;
    @Value("${search.ascending}")
    String ascending;
    @Value("${search.descending}")
    String descending;
    @Autowired
    private CertificateDao certificateDao;

    @Override
    public List<Certificate> getAll() {
        return certificateDao.getAll();
    }

    @Override
    public Optional<Certificate> getById(long id) {
        return certificateDao.getById(id);
    }

    @Override
    public boolean save(Certificate object) {
        return certificateDao.save(object);
    }

    @Override
    public boolean delete(long id) {
        return certificateDao.delete(id);
    }

    @Override
    public boolean selectiveUpdate(Certificate updatedCertificate) {
        return certificateDao.selectiveUpdate(updatedCertificate);
    }

    // TODO Refractor this
    @Override
    public List<Certificate> search(Map<String, String> searchParams) {
        List<Certificate> fittingCertificates = certificateDao.getAll();

        String tagName = searchParams.get(tagNameParam);
        if (tagName != null) {
            fittingCertificates = fittingCertificates.stream()
                    .filter(certificate -> certificate.getTags().contains(tagName)).collect(Collectors.toList());
        }

        String name = searchParams.get(nameParam);
        if (name != null) {
            fittingCertificates = fittingCertificates.stream()
                    .filter(certificate -> certificate.getName().contains(name)).collect(Collectors.toList());
        }

        String description = searchParams.get(descriptionParam);
        if (description != null) {
            fittingCertificates = fittingCertificates.stream()
                    .filter(certificate -> certificate.getDescription().contains(description))
                    .collect(Collectors.toList());
        }

        return fittingCertificates;
    }
}