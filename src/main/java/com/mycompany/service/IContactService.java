package com.mycompany.service;

import com.mycompany.model.Contact;
import java.util.Collection;



public interface IContactService {
    public Collection<Contact> getContacts(String regex);

}
