package com.epam.esm.zotov.module2.service.certificate;

import java.util.List;
import java.util.Map;

import com.epam.esm.zotov.module2.dal.model.Certificate;
import com.epam.esm.zotov.module2.service.CrdService;

public interface CertificateService extends CrdService<Certificate> {
    public boolean selectiveUpdate(Certificate updatedCertificate);

    public List<Certificate> search(Map<String, String> searchParams);
}