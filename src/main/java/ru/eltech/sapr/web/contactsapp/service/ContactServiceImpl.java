package ru.eltech.sapr.web.contactsapp.service;

import ru.eltech.sapr.web.contactsapp.dao.ContactsDao;
import ru.eltech.sapr.web.contactsapp.dao.PhonesDao;
import ru.eltech.sapr.web.contactsapp.model.Contact;
import ru.eltech.sapr.web.contactsapp.model.Phone;
import ru.eltech.sapr.web.contactsapp.model.PhoneType;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    private final ContactsDao contactsDao;
    private final PhonesDao phonesDao;

    public ContactServiceImpl(ContactsDao contactsDao, PhonesDao phonesDao) {
        this.contactsDao = contactsDao;
        this.phonesDao = phonesDao;
    }

    @Override
    public List<Contact> getAll() {
        // TODO: add logging
        return contactsDao.getAll();
    }

    @Override
    public Contact getContactById(long id) {
        return contactsDao.getById(id);
    }

    @Override
    public Contact createContact(String name, String phone) {
        return contactsDao.create(name, phone);
    }

    @Override
    public void updateContact(Contact contact) {
        contactsDao.update(contact);
    }

    @Override
    public boolean removeContact(Contact contact) {
        for (Phone phone : phonesDao.getPhonesForContact(contact.getId())) {
            phonesDao.removePhone(phone.getId(), contact.getId());
        }
        return contactsDao.delete(contact.getId());
    }

    @Override
    public List<Phone> getPhonesForContact(Contact contact) {
        return phonesDao.getPhonesForContact(contact.getId());
    }

    @Override
    public Phone addPhone(Contact contact, String number, PhoneType type) {
        return phonesDao.createPhone(number, type, contact.getId());
    }

    @Override
    public void updatePhone(Contact contact, Phone phone) {
        phonesDao.updatePhone(phone, contact.getId());
    }

    @Override
    public boolean removePhone(Contact contact, Phone phone) {
        return phonesDao.removePhone(phone.getId(), contact.getId());
    }
}
