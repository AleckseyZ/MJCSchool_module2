package com.epam.esm.zotov.module2.api.controller.certificate;

import java.util.List;
import java.util.Map;

import com.epam.esm.zotov.module2.api.controller.CrdController;
import com.epam.esm.zotov.module2.dal.model.Certificate;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


public interface CertificateController extends CrdController<Certificate> {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Object> search(@RequestParam Map<String,String> requestParams);

    @PutMapping
    public boolean selectiveUpdate(Certificate updatedCertificate);
}