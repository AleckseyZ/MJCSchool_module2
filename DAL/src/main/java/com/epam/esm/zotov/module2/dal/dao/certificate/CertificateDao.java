package com.epam.esm.zotov.module2.dal.dao.certificate;

import com.epam.esm.zotov.module2.dal.dao.CrdDao;
import com.epam.esm.zotov.module2.dal.model.Certificate;

public interface CertificateDao extends CrdDao<Certificate> {
    public boolean selectiveUpdate(Certificate updatedCertificate);
}