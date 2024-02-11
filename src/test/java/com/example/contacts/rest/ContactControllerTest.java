package com.example.contacts.rest;

import com.example.contacts.dto.ContactDto;
import com.example.contacts.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    private ContactDto contactDto;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        contactDto = new ContactDto();
        contactDto.setName("John Doe");
        contactDto.setCodeName("JD");
        contactDto.setPhoneNumber("1234343434");
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void getAllContactsReturnsPageOfContactsWhenContactsExist() {
        Page<ContactDto> contactPage = new PageImpl<>(Collections.singletonList(contactDto));
        when(contactService.getAllContacts(pageable)).thenReturn(contactPage);

        ResponseEntity<Page<ContactDto>> result = contactController.getAllContacts(pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().getContent().size());
        assertEquals(contactDto, result.getBody().getContent().get(0));
    }

    @Test
    void getAllContactsReturnsNotFoundWhenNoContactsExist() {
        Page<ContactDto> contactPage = Page.empty();
        when(contactService.getAllContacts(pageable)).thenReturn(contactPage);

        ResponseEntity<Page<ContactDto>> result = contactController.getAllContacts(pageable);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void createContactReturnsCreatedContact() {
        when(contactService.createContact(contactDto)).thenReturn(contactDto);

        ResponseEntity<ContactDto> result = contactController.createContact(contactDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(contactDto, result.getBody());
    }

}