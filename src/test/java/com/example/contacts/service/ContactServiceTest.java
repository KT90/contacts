package com.example.contacts.service;

import com.example.contacts.dto.ContactDto;
import com.example.contacts.model.Contact;
import com.example.contacts.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ContactService contactService;

    private Contact contact;
    private ContactDto contactDto;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        contact = new Contact();
        contact.setName("John Doe");
        contact.setCodeName("JD");
        contact.setPhoneNumber("1234343434");
        contactDto = new ContactDto();
        contactDto.setName("John Doe");
        contactDto.setCodeName("JD");
        contactDto.setPhoneNumber("1234343434");
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void getAllContactsReturnsPageOfContacts() {
        Page<Contact> contactPage = new PageImpl<>(Collections.singletonList(contact));
        when(contactRepository.findAll(pageable)).thenReturn(contactPage);
        when(modelMapper.map(contact, ContactDto.class)).thenReturn(contactDto);

        Page<ContactDto> result = contactService.getAllContacts(pageable);

        assertEquals(1, result.getContent().size());
        assertEquals(contactDto, result.getContent().getFirst());
    }

    @Test
    void createContactReturnsCreatedContact() {
        when(modelMapper.map(contactDto, Contact.class)).thenReturn(contact);
        when(contactRepository.save(contact)).thenReturn(contact);
        when(modelMapper.map(contact, ContactDto.class)).thenReturn(contactDto);

        ContactDto result = contactService.createContact(contactDto);

        assertEquals(contactDto, result);
    }

}