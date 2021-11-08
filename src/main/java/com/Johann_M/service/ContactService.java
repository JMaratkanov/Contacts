package com.Johann_M.service;

import com.Johann_M.entities.Contact;
import com.Johann_M.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repo;

    public List<Contact> listAll(){
        return (List<Contact>) repo.findAll();
    }

    public void save(Contact contact) {
        repo.save(contact);
    }
}
