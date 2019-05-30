package ru.eltech.sapr.web.contactsapp.dao;

import ru.eltech.sapr.web.contactsapp.model.Phone;
import ru.eltech.sapr.web.contactsapp.model.PhoneType;

import java.util.List;

public interface PhonesDao {
    List<Phone> getPhonesForContact(long contactId);

    Phone createPhone(String number, PhoneType type, long contactId);

    boolean removePhone(long phoneId, long contactId);

    void updatePhone(Phone phone, long contactId);
}
