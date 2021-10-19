package com.epam.esm.zotov.module2.api.controller.certificate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.epam.esm.zotov.module2.api.exception.NoResourceFoundException;
import com.epam.esm.zotov.module2.dataaccess.model.Certificate;
import com.epam.esm.zotov.module2.service.certificate.CertificateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificates")
public class CertificateControllerImpl implements CertificateController {
    private CertificateService certificateService;

    @Autowired
    public CertificateControllerImpl(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @Override
    public List<Certificate> getAll() {
        List<Certificate> certificates = certificateService.getAll();
        if (certificates.isEmpty()) {
            throw new NoResourceFoundException();
        }
        return certificates;
    }

    @Override
    public Certificate getById(long targetId) {
        Optional<Certificate> certificate = certificateService.getById(targetId);
        if (certificate.isEmpty()) {
            throw new NoResourceFoundException();
        }
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
    public List<Certificate> search(Map<String, String> searchParams) {
        List<Certificate> certificates = certificateService.search(searchParams);
        if (certificates.isEmpty()) {
            throw new NoResourceFoundException();
        }
        return certificates;
    }
}