package com.epam.esm.zotov.module2.dal.dao.certificate;

import com.epam.esm.zotov.module2.dal.dao.CrdDao;
import com.epam.esm.zotov.module2.dal.model.Certificate;

/**
 * Defines data source manipulating methods for the Tag type
 * 
 * @see CrdDao
 */
public interface CertificateDao extends CrdDao<Certificate> {
    /**
     * Updates <code>Certificate</code> in the data source. Will only update
     * modified fields.
     * 
     * @param updatedCertificate - <code>Certificate</code> with updated fields.
     * @return <code>true</code> if update is successful.
     */
    public boolean selectiveUpdate(Certificate updatedCertificate);
}