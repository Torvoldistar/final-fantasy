package ru.eltech.sapr.web.contactsapp.model;

public class Phone {
    private final long id;

    private final String number;
    private final PhoneType type;

    public Phone(long id, String number, PhoneType type) {
        this.id = id;
        this.number = number;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public PhoneType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        if (id != phone.id) return false;
        if (!number.equals(phone.number)) return false;
        return type == phone.type;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + number.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type=" + type +
                '}';
    }
}
