package com.Johann_M.service;

import com.Johann_M.exceptions.ContactNotFoundException;
import com.Johann_M.entities.Contact;
import com.Johann_M.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void delete(Integer id){
        repo.deleteById(id);            //löscht zugehörige Adressen automatisch wegen FK(contact_fk) delete on cascade
    }

    public Contact getContactById(Integer id) throws ContactNotFoundException {
        Optional<Contact> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ContactNotFoundException("Datenbankfehler: Kein Kontakt mit dieser ID gefunden!");
    }
}
