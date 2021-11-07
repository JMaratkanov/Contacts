package com.Johann_M;

import com.Johann_M.entities.Contact;
import com.Johann_M.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    /*
     *     Round Triping Test mit (C-R-Ass-D) Strecke
     */
    @Test
    void createReadAndDeleteContact() {

        //Create via save
        // Anlegen eines Kontaktes. ID wird automatisch erzeugt
        Contact con = new Contact();
        con.setfName("Vorname_Test");
        con.setlName("Nachname_Test");
        con.seteMail("Email_Test");
        contactRepository.save(con);

        //auto-generierte ID merken
        int idTmp = con.getId();

        //Read via find
        Optional<Contact> wrapper = contactRepository.findById( idTmp );
        Contact conAfterCreate = null;
        if ( wrapper.isPresent() ) {
            conAfterCreate = wrapper.get();
        }

        //Assertion: Vergleich der vorhandenen Objekte auf Gleichheit
        assertEquals( conAfterCreate.getfName() , "Vorname_Test" );
        assertEquals( conAfterCreate.getlName() , "Nachname_Test" );
        assertEquals( conAfterCreate.geteMail() , "Email_Test" );
        // .. auf Identität
        assertNotSame( con , conAfterCreate );

        //Delete
        contactRepository.deleteById(idTmp);
        Optional<Contact> wrapperAfterDelete = contactRepository.findById(idTmp);
        System.out.println("Wrapper: " + wrapperAfterDelete);
        assertFalse( wrapperAfterDelete.isPresent() );
    }
}
