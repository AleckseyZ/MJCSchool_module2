package com.epam.esm.zotov.module2.service.tag;

import java.util.List;
import java.util.Optional;

import com.epam.esm.zotov.module2.dataaccess.dao.tag.TagDao;
import com.epam.esm.zotov.module2.dataaccess.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    private TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> getAll() {
        return tagDao.getAll();
    }

    @Override
    public Optional<Tag> getById(long id) {
        return tagDao.getById(id);
    }

    @Override
    public boolean save(Tag tag) {
        return tagDao.save(tag);
    }

    @Override
    public boolean delete(long id) {
        return tagDao.delete(id);
    }
}