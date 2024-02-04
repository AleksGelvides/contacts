package org.example.contacts.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ContactRepository {

    private final JdbcTemplate template;
    public void saveOrUpdate(Contact contact) {
        Contact existedContact = findById(contact.getId());
        String sql;
        if (Objects.isNull(existedContact)) {
            String idSql = "SELECT MAX(id) FROM contacts";
            Long lastId = template.queryForObject(idSql, Long.class);
            sql = "insert into contacts (id, firstName, lastName, email, phone) values (?, ?, ?, ?, ?)";
            template.update(sql, ++lastId, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
        } else {
            sql = "update contacts set firstName = ?, lastName = ?, email = ?, phone = ? where id = ?";
            template.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());
        }
    }


    public List<Contact> getAll() {
        String sql = "select * from contacts order by id desc";
        return template.query(sql, new ContactRowMapper());
    }

    public void remove(Long id) {
        String sql = "delete from contacts where id = ?";
        template.update(sql, id);
    }

    public Contact findById(Long id) {
        String sql = "select * from contacts where id = ?";
        return DataAccessUtils.singleResult(
                template.query(sql,
                        new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1))
        );
    }
}
