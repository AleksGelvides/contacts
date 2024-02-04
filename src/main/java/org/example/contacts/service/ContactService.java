package org.example.contacts.service;

import lombok.AllArgsConstructor;
import org.example.contacts.repository.Contact;
import org.example.contacts.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactService {

    private final ContactRepository repository;

    public List<Contact> findAll() {
        return repository.getAll();
    }

    public void createOrEdit(Contact contact) {
        repository.saveOrUpdate(contact);
    }

    public Contact findById(Long id) {
        return repository.findById(id);
    }

    public void remove(Long id) {
        repository.remove(id);
    }

}
