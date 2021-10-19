package com.epam.esm.zotov.module2.dataaccess.dao.certificate;

import com.epam.esm.zotov.module2.dataaccess.dao.CrdDao;
import com.epam.esm.zotov.module2.dataaccess.model.Certificate;

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
    boolean selectiveUpdate(Certificate updatedCertificate);
}