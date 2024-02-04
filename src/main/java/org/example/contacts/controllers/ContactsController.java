package org.example.contacts.controllers;

import lombok.AllArgsConstructor;
import org.example.contacts.repository.Contact;
import org.example.contacts.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ContactsController {

    private final ContactService service;

    @ModelAttribute(name = "contactList")
    public List<Contact> all() {
        return service.findAll();
    }

    @GetMapping
    public String mainPage() {
        return "main-page";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "edit-contact";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("contact", service.findById(id));
        return "edit-contact";
    }

    @PostMapping
    public String saveContact(@ModelAttribute Contact contact) {
        service.createOrEdit(contact);
        return "redirect:/";
    }

    @PostMapping("/remove/{id}")
    public String removeContact(@PathVariable Long id) {
        service.remove(id);
        return "redirect:/";
    }
}
