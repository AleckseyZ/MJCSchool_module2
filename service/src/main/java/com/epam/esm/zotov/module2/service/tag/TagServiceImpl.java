package com.epam.esm.zotov.module2.service.tag;

import java.util.List;
import java.util.Optional;

import com.epam.esm.zotov.module2.dal.dao.tag.TagDao;
import com.epam.esm.zotov.module2.dal.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    @Override
    public List<Tag> getAll() {
        return tagDao.getAll();
    }

    @Override
    public Optional<Tag> getById(long id) {
        return tagDao.getById(id);
    }

    @Override
    public boolean save(Tag object) {
        return tagDao.save(object);
    }

    @Override
    public boolean delete(long id) {
        return tagDao.delete(id);
    }
}