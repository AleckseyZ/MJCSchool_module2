package com.epam.esm.zotov.module2.service.certificate;

import com.epam.esm.zotov.module2.dal.model.Certificate;
import com.epam.esm.zotov.module2.service.crdService;

public interface CertificateService extends crdService<Certificate> {
    public boolean selectiveUpdate(Certificate updatedCertificate);
}