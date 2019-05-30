package ru.eltech.sapr.web.contactsapp.dao;

import ru.eltech.sapr.web.contactsapp.model.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public enum InMemoryContacts implements ContactsDao {
    INSTANCE;

    private AtomicLong idGenerator = new AtomicLong();
    private Map<Long, Contact> allContacts = new HashMap<>();

    @Override
    public List<Contact> getAll() {
        return new ArrayList<>(allContacts.values());
    }

    @Override
    public Contact getById(long id) {
        return allContacts.get(id);
    }

    @Override
    public Contact create(String firstName, String lastName) {
        Contact contact = new Contact(idGenerator.incrementAndGet(), firstName, lastName);
        allContacts.put(contact.getId(), contact);
        return contact;
    }

    @Override
    public void update(Contact contact) {
        allContacts.put(contact.getId(), contact);
    }

    @Override
    public boolean delete(long id) {
        Contact remove = allContacts.remove(id);
        return remove != null;
    }
}
