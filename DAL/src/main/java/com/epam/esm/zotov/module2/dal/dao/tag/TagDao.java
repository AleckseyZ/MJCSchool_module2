package com.epam.esm.zotov.module2.dal.dao.tag;

import java.util.Optional;

import com.epam.esm.zotov.module2.dal.dao.CrdDao;
import com.epam.esm.zotov.module2.dal.model.Tag;

public interface TagDao extends CrdDao<Tag> {
    public Optional<Tag> getByName(String name);
}