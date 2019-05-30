package ru.eltech.sapr.web.contactsapp.dao;

import ru.eltech.sapr.web.contactsapp.model.Contact;

import java.util.List;
import java.util.function.Consumer;

public interface ContactsDao {
    List<Contact> getAll();

    Contact getById(long id);

    Contact create(String firstName, String lastName);

    void update(Contact contact);

    boolean delete(long id);
}
