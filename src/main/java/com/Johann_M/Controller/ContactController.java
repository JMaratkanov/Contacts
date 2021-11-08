package com.Johann_M.Controller;

import com.Johann_M.entities.Contact;
import com.Johann_M.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private ContactService service;

    @GetMapping("/contacts")
    public String showContactList(Model model){
        List<Contact> contactList = service.listAll();
        model.addAttribute("contactList", contactList);
        return "contacts";
    }

    @GetMapping("/contacts/new")
    public String showNewForm(Model model){
        model.addAttribute("contact", new Contact());  //für <form th:action="@{/contacts/save}" method="post" th:object="${contact}">
        return "contactForm";
    }

    @PostMapping("/contacts/save")
    public String saveContact(Contact contact){
        service.save(contact);
        return "redirect:/contacts";
    }
}
