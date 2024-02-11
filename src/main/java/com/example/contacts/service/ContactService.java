package com.example.contacts.service;

import com.example.contacts.dto.ContactDto;
import com.example.contacts.model.Contact;
import com.example.contacts.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    public ContactService(ContactRepository userRepository, ModelMapper modelMapper) {
        this.contactRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Page<ContactDto> getAllContacts(Pageable pageable) {
        Page<Contact> contacts = contactRepository.findAll(pageable);
        return contacts.map(contact -> modelMapper.map(contact, ContactDto.class));
    }

    public ContactDto createContact(ContactDto contactDto) {
        Contact contact = modelMapper.map(contactDto, Contact.class);
        Contact createdContact = contactRepository.save(contact);
        return modelMapper.map(createdContact, ContactDto.class);
    }

}