package com.epam.esm.zotov.module2.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * Interface that defines basic create, read and delete RESTful methods.
 * <p>
 * * @param T - type of the objects that controller works with.
 */
public interface CrdController<T> {
    /**
     * Gets all objects of <code>T</code> type. Might return an error message if the
     * list is empty.
     * 
     * @return <code>List</code> of all objects or an error message.
     */
    @GetMapping
    public List<T> getAll();

    /**
     * Gets an object with a specified id. Might return an error message if no
     * object with specified id found.
     * 
     * @param targetId id of desired object.
     * @return object with a specified id or an error message.
     * 
     */
    @RequestMapping(value = "/{targetId}", method = RequestMethod.GET)
    public T getById(@PathVariable long targetId);

    /**
     * Deletes object with a specified id.
     * 
     * @param targetId id of an object to be deleted.
     * @return <code>true</code> if deletion was successful.
     */
    @RequestMapping(value = "/{targetId}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable long targetId);

    /**
     * Saves object of <code>T</code> type passed in the body.
     * 
     * @param object object to be saved
     * @return <code>true</code> if object was successfuly saved
     */
    @PostMapping
    public boolean save(@RequestBody T object);
}