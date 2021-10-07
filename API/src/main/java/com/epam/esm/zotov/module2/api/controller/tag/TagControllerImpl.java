package com.epam.esm.zotov.module2.api.controller.tag;

import java.util.List;
import java.util.Optional;

import com.epam.esm.zotov.module2.dal.model.Tag;
import com.epam.esm.zotov.module2.service.tag.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO Exception handling
@RestController
@RequestMapping("/tags")
public class TagControllerImpl implements TagController {
    @Autowired
    TagService tagService;

    @Override
    public List<Tag> getAll() {
        return tagService.getAll();
    }

    @Override
    public Tag getById(long targetId) {
        Optional<Tag> tag = tagService.getById(Math.toIntExact(targetId));
        return tag.get();
    }

    @Override
    public boolean save(Tag object) {
        return tagService.save(object);
    }

    @Override
    public boolean delete(long targetId) {
        return tagService.delete(Math.toIntExact(targetId));
    }
}