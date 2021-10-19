package com.epam.esm.zotov.module2.service;

import java.util.List;
import java.util.Optional;

/**
 * Defines create, read and delete service methods.
 */
public interface CrdService<T> {
    /**
     * Gets <code>T</code> type object with specified id and wraps it in the
     * <code>Optional</code>.
     * 
     * @param id - id of desired object.
     * @return <code>Optional</code> containing object with specified id.
     */
    Optional<T> getById(long id);

    /**
     * Gets <code>List</code> of all objects with type <code>T</code>.
     * 
     * @return <code>List</code> of all <code>T</code> objects.
     */
    List<T> getAll();

    /**
     * Saves <code>T</code> object. Returns boolean indicating whether saving is
     * successful or not.
     * 
     * @param object - object to be saved.
     * @return <code>true</code> if object successfuly saved.
     */
    boolean save(T object);

    /**
     * Deletes object with specified id. Returns boolean indicating whether deletion
     * is successful or not.
     * 
     * @param object - object to be deleted.
     * @return <code>true</code> if object successfuly deleted.
     */
    boolean delete(long id);
}