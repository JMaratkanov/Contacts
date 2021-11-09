package com.Johann_M.control;

import com.Johann_M.exceptions.AdressNotFoundException;
import com.Johann_M.entities.Adress;
import com.Johann_M.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdressControl {

    @Autowired
    private AdressService service;

    //Zeigt alle gespeicherten Adressen an
    @GetMapping("/contacts/adress/{id}")
    public String showAdressList(@PathVariable("id") Integer id, Model model){
        List<Adress> adressList = service.listAll(id);
        model.addAttribute("adressList", adressList);
        model.addAttribute("conId",id);
        return "adress";
    }
    //Zeigt Formular für Anlegen einer neuen Adresse
    @GetMapping("/contacts/adress/{conId}/new")
    public String showNewForm(@PathVariable("conId") Integer conId, Model model){
        model.addAttribute("adress", new Adress());
        model.addAttribute("pagetitle", "Neue Adresse hinzufügen");
        return "adressForm";
    }

    //Speichert ein Objekt von Typ Adress
    @PostMapping("/contacts/adress/{conId}/new/save")
    public String saveContact(@PathVariable("conId") Integer conId, Adress adress){
        service.save(adress);
        return "redirect:/contacts/adress/" + conId;
    }

    //Zeigt Formular zum Ändern eines Kontakteintrages
    @GetMapping("/contacts/adress/{conId}/edit/{adrId}")
    public String showEditForm(@PathVariable("conId") Integer conId, @PathVariable("adrId") Integer adrId, Model model, RedirectAttributes ra){
        try {
            Adress adr = service.getAdressById(adrId);
            model.addAttribute("adress", adr);
            model.addAttribute("pagetitle", "Adresse ändern");
            return "adressForm";
        } catch (AdressNotFoundException e) {
            ra.addFlashAttribute("Fehler");
            return "redirect:/contacts/adress/{conId}";
        }
    }

    @GetMapping("/contacts/adress/{conId}/delete/{adrId}")
    public String deleteById(@PathVariable("conId") Integer conId, @PathVariable("adrId") Integer adrId, RedirectAttributes ra){
        service.delete(adrId);
        ra.addFlashAttribute("Adresse mit ID " + adrId + " erfolgreich gelöscht.");
        return "redirect:/contacts/adress/{conId}";
    }
}
