package com.epam.esm.zotov.module2.api.controller.tag;

import java.util.List;
import java.util.Optional;

import com.epam.esm.zotov.module2.api.exception.NoResourceFoundException;
import com.epam.esm.zotov.module2.dataaccess.model.Tag;
import com.epam.esm.zotov.module2.service.tag.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagControllerImpl implements TagController {
    private TagService tagService;

    @Autowired
    public TagControllerImpl(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public List<Tag> getAll() {
        List<Tag> tag = tagService.getAll();
        if (tag.isEmpty()) {
            throw new NoResourceFoundException();
        }
        return tag;
    }

    @Override
    public Tag getById(long targetId) {
        Optional<Tag> tag = tagService.getById(targetId);
        if (tag.isEmpty()) {
            throw new NoResourceFoundException();
        }
        return tag.get();
    }

    @Override
    public boolean save(Tag object) {
        return tagService.save(object);
    }

    @Override
    public boolean delete(long targetId) {
        return tagService.delete(targetId);
    }
}