package com.epam.esm.zotov.module2.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CrdController<T> {
    @GetMapping
    public List<T> getAll();

    @RequestMapping(value = "/{targetId}", method = RequestMethod.GET)
    public T getById(@PathVariable long targetId);

    @RequestMapping(value = "/{targetId}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable long targetId);

    @PostMapping
    public boolean save(T object);
}