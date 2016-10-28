package com.mycompany.service;

import com.mycompany.model.Contact;

import java.util.Collection;
import java.util.List;


public interface IContactService {

    public List<Contact> getFilteredContacts(String regex);
}
