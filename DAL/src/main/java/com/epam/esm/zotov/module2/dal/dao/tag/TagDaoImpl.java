package com.epam.esm.zotov.module2.dal.dao.tag;

import java.util.List;
import java.util.Optional;

import com.epam.esm.zotov.module2.dal.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:tag.sql.properties")
public class TagDaoImpl implements TagDao {
    @Value("tag.sql.all")
    private String getAllQuery;
    @Value("tag.sql.findById")
    private String findByIdQuery;
    @Value("tag.sql.findByName")
    private String findByNameQuery;
    @Value("tag.sql.insert")
    private String insertQuery;
    @Value("tag.sql.delete")
    private String deleteQuery;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> getAll() {
        List<Tag> tags = jdbcTemplate.query(getAllQuery, tagMapper);
        return tags;
    }

    @Override
    public Optional<Tag> getById(long id) {
        Optional<Tag> tag = Optional.of(jdbcTemplate.queryForObject(findByIdQuery, tagMapper, id));
        return tag;
    }

    @Override
    public Optional<Tag> getByName(String name) {
        Optional<Tag> tag = Optional.of(jdbcTemplate.queryForObject(findByNameQuery, tagMapper, name));
        return tag;
    }

    @Override
    public boolean save(Tag object) {
        long result = jdbcTemplate.update(insertQuery, object.getTagName());
        return result == 1;
    }

    @Override
    public boolean delete(long id) {
        long result = jdbcTemplate.update(deleteQuery, id);
        return result == 1;
    }
}