package com.Johann_M;

import com.Johann_M.entities.Adress;
import com.Johann_M.entities.Contact;
import com.Johann_M.repository.AdressRepository;
import com.Johann_M.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdressRepositoryTest {

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private ContactRepository contactRepository;

    //auto-generierte ID des Kontaktes
    int contactIdTmp;

    @BeforeEach
    public void setUp(){
        //Anlegen eines Kontaktes mit Adresse, FK(Kontakt-Id) in Adresse muss in Tabelle Kontakt existieren
        Contact con = new Contact();
        con.setfName("Vorname_Test");
        con.setlName("Nachname_Test");
        con.seteMail("Email_Test");
        contactRepository.save(con);

        //auto-generierte ID des Kontaktes merken
        contactIdTmp = con.getId();
    }

    /*
     *     Round Triping Test mit (C-R-Ass-D) Strecke
     */
    @Test
    void createReadAndDeleteAdress() {
        //Anlegen einer Adresse die dem Kontakt zugeordnet wird
        Adress adr = new Adress();
        adr.setContactID(contactIdTmp);
        adr.setPlace("Ort_Test");
        adr.setPostcode(12345);
        adr.setStreet("Teststraße");
        adr.setsNumber(1);
        adressRepository.save(adr);

        //auto-generierte ID merken
        int adrIdTmp = adr.getId();

        //Read via find
        Optional<Adress> wrapper = adressRepository.findById(adrIdTmp);
        Adress adrAfterCreate = null;
        if (wrapper.isPresent()) {
            adrAfterCreate = wrapper.get();
        }

        //Assertion: Vergleich der vorhandenen Objekte auf Gleichheit
        assert adrAfterCreate != null;
        assertEquals(adrAfterCreate.getContactID(), contactIdTmp);
        assertEquals(adrAfterCreate.getPlace(), "Ort_Test");
        assertEquals(adrAfterCreate.getPostcode(), 12345);
        assertEquals(adrAfterCreate.getStreet(), "Teststraße");
        assertEquals(adrAfterCreate.getsNumber(), 1);

        // .. auf Identität
        assertNotSame(adr, adrAfterCreate);

        //Delete
        adressRepository.deleteById(adrIdTmp);
        Optional<Adress> wrapperAfterDelete = adressRepository.findById(adrIdTmp);
        System.out.println("Wrapper: " + wrapperAfterDelete);
        assertFalse( wrapperAfterDelete.isPresent() );
    }

    @AfterEach
    public void cleanUp(){
        contactRepository.deleteById(contactIdTmp);
    }
}
