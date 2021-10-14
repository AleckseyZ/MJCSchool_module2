package com.epam.esm.zotov.module2.dal.dao.tag;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.esm.zotov.module2.dal.model.Tag;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:tag.sql.properties")
public class TagMapper implements RowMapper<Tag> {
    @Value("${tag.id}")
    private String idColumn;
    @Value("${tag.name}")
    private String nameColumn;

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();

        tag.setTagId(rs.getInt(idColumn));
        tag.setTagName(rs.getString(nameColumn));

        return tag;
    }
}