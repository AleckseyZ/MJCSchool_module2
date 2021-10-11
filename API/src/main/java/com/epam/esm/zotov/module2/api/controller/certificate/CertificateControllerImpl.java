package com.epam.esm.zotov.module2.api.controller.certificate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.epam.esm.zotov.module2.dal.model.Certificate;
import com.epam.esm.zotov.module2.service.certificate.CertificateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO Exception handling
@RestController
@RequestMapping("/certificates")
public class CertificateControllerImpl implements CertificateController {
    @Autowired
    private CertificateService certificateService;

    @Override
    public List<Certificate> getAll() {
        return certificateService.getAll();
    }

    @Override
    public Certificate getById(long targetId) {
        Optional<Certificate> certificate = certificateService.getById(targetId);
        return certificate.get();
    }

    @Override
    public boolean save(Certificate object) {
        return certificateService.save(object);
    }

    @Override
    public boolean delete(long targetId) {
        return certificateService.delete(targetId);
    }

    @Override
    public boolean selectiveUpdate(Certificate updatedCertificate) {
        return certificateService.selectiveUpdate(updatedCertificate);
    }

    @Override
    public List<Certificate> search(Map<String, String> requestParams) {
        return certificateService.search(requestParams);
    }
}