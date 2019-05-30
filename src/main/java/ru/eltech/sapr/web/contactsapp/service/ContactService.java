package ru.eltech.sapr.web.contactsapp.service;

import ru.eltech.sapr.web.contactsapp.model.Contact;
import ru.eltech.sapr.web.contactsapp.model.Phone;
import ru.eltech.sapr.web.contactsapp.model.PhoneType;

import java.util.List;

public interface ContactService {
    String SERVICE_NAME = "ContactService";

    List<Contact> getAll();

    Contact getContactById(long id);

    Contact createContact(String name, String phone);

    void updateContact(Contact contact);

    boolean removeContact(Contact contact);

    List<Phone> getPhonesForContact(Contact contact);

    Phone addPhone(Contact contact, String number, PhoneType type);

    void updatePhone(Contact contact, Phone phone);

    boolean removePhone(Contact contact, Phone phone);
}
