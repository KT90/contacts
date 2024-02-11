package com.example.contacts.rest;

import com.example.contacts.dto.ContactDto;
import com.example.contacts.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> getAllContacts(@PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<ContactDto> contacts = contactService.getAllContacts(pageable);
            if (contacts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ContactDto> createContact(@Valid @RequestBody ContactDto contactDto) {
        try {
            ContactDto createdContact = contactService.createContact(contactDto);
            return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}