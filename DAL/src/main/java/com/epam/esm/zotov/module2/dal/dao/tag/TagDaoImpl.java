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
@PropertySource("classpath:db.properties")
public class TagDaoImpl implements TagDao {
    @Value("tag.sql.all")
    private String getAllQuerry;
    @Value("tag.sql.findById")
    private String findByIdQuerry;
    @Value("tag.sql.insert")
    private String insertQuerry;
    @Value("tag.sql.delete")
    private String deleteQuerry;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> getAll() {
        List<Tag> tags = jdbcTemplate.query(getAllQuerry, tagMapper);
        return tags;
    }

    @Override
    public Optional<Tag> getById(int id) {
        Optional<Tag> tag = Optional.of(jdbcTemplate.queryForObject(findByIdQuerry, tagMapper, id));
        return tag;
    }

    @Override
    public boolean save(Tag object) {
        int result = jdbcTemplate.update(insertQuerry, object.getTagName());
        return result == 1;
    }

    @Override
    public boolean delete(int id) {
        int result = jdbcTemplate.update(deleteQuerry, id);
        return result == 1;
    }
}