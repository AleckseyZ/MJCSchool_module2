package com.epam.esm.zotov.module2.api.controller.certificate;

import java.util.List;
import java.util.Map;

import com.epam.esm.zotov.module2.api.controller.CrdController;
import com.epam.esm.zotov.module2.dal.model.Certificate;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Defines RESTful methods for the <code>Certificate</code> type.
 * <p>
 * 
 * @see CrdController
 */
public interface CertificateController extends CrdController<Certificate> {
    /**
     * Searches the certificates that fit desired criteria. If thereis no such
     * certificates, might return an error.
     * 
     * @param searchParams <code>Map</code> of search parameters and their values.
     * @return <code>List</code> of fitting certificates.
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Certificate> search(@RequestParam Map<String, String> searchParams);

    /**
     * @param updatedCertificate updated object. Must contain id. Unupdated fields
     *                           might be <code>null</code>
     * @return <code>true</code> if object was successfuly updated
     */
    @PutMapping
    public boolean selectiveUpdate(@RequestBody Certificate updatedCertificate);
}