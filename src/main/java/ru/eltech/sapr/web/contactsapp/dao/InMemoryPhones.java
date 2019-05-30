package ru.eltech.sapr.web.contactsapp.dao;

import ru.eltech.sapr.web.contactsapp.model.Phone;
import ru.eltech.sapr.web.contactsapp.model.PhoneType;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public enum InMemoryPhones implements PhonesDao {
    INSTANCE;

    private AtomicLong idGenerator = new AtomicLong();
    private Map<Long, Map<Long, Phone>> allPhones = new HashMap<>();

    @Override
    public List<Phone> getPhonesForContact(long contactId) {
        Collection<Phone> phones = allPhones.getOrDefault(contactId, Collections.emptyMap()).values();
        return new ArrayList<>(phones);
    }

    @Override
    public Phone createPhone(String number, PhoneType type, long contactId) {
        long phoneId = idGenerator.incrementAndGet();
        Phone newPhone = new Phone(phoneId, number, type);
        allPhones.computeIfAbsent(contactId, id -> new LinkedHashMap<>()).put(phoneId, newPhone);
        return newPhone;
    }

    @Override
    public boolean removePhone(long phoneId, long contactId) {
        Phone removed = allPhones.getOrDefault(contactId, Collections.emptyMap()).remove(phoneId);
        return removed != null;
    }

    @Override
    public void updatePhone(Phone phone, long contactId) {
        allPhones.get(contactId).put(phone.getId(), phone);
    }
}
