package com.epam.esm.zotov.module2.dal.dao;

import java.util.List;
import java.util.Optional;

public interface CrdDao<T> {
    public Optional<T> getById(int id);

    public List<T> getAll();

    public boolean save(T object);

    public boolean delete(int id);
}