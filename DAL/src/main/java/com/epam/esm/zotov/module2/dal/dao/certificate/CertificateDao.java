package com.epam.esm.zotov.module2.dal.dao.certificate;

import java.util.List;

import com.epam.esm.zotov.module2.dal.dao.crdDao;
import com.epam.esm.zotov.module2.dal.model.Certificate;

public interface CertificateDao extends crdDao<Certificate> {
    public boolean selectiveUpdate(Certificate updatedCertificate, List<String> updatedFields);
}