package com.mycompany.dao;

import com.mycompany.model.Contact;

import java.util.List;


public interface IContactDao {

    public List<Contact> getContacts();

    public List<Contact> getContacts(int offset, int fetch);

}
