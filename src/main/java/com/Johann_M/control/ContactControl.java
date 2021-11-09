package com.Johann_M.control;

import com.Johann_M.exceptions.ContactNotFoundException;
import com.Johann_M.entities.Contact;
import com.Johann_M.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactControl {

    @Autowired
    private ContactService service;

    //Zeigt alle gespeicherten Kontakte an
    @GetMapping("/contacts")
    public String showContactList(Model model){
        List<Contact> contactList = service.listAll();
        model.addAttribute("contactList", contactList);
        return "contacts";
    }

    //Zeigt Formular für Anlegen eines neuen Kontaktes
    @GetMapping("/contacts/new")
    public String showNewForm(Model model){
        model.addAttribute("contact", new Contact());  //für <form th:action="@{/contacts/save}" method="post" th:object="${contact}">
        model.addAttribute("pagetitle", "Neuen Kontakt erstellen");
        return "contactForm";
    }

    //Speichert ein Objekt von Typ Contact
    @PostMapping("/contacts/save")
    public String saveContact(Contact contact){
        service.save(contact);
        return "redirect:/contacts";
    }

    //Zeigt Formular zum Ändern eines Kontakteintrages
    @GetMapping("/contacts/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Contact con = service.getContactById(id);
            model.addAttribute("contact", con);
            model.addAttribute("pagetitle", "Kontakt ändern");      //Es wird das selbe Formular wie für das Erstellen benutzt
            return "contactForm";
        } catch (ContactNotFoundException e) {
            ra.addFlashAttribute("Fehler");
            return "redirect:/contacts";
        }
    }

    //Löscht einen Kontakteintrag via id
    @GetMapping("/contacts/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id, RedirectAttributes ra){
        service.delete(id);
        ra.addFlashAttribute("Kontakt mit ID " + id + " erfolgreich gelöscht.");
        return "redirect:/contacts";
    }
}
