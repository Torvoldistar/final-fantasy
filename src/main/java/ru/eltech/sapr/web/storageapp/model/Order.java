package ru.eltech.sapr.web.storageapp.model;


public class Order {
    private long id;
    private String customerName;
    private String customerPhone;
    private String customerOrder;


    public Order(long input_id, String input_customerName, String input_customerPhone, String input_customerOrder) {
        this.id = input_id;
        this.customerName = input_customerName;
        this.customerPhone = input_customerPhone;
        this.customerOrder = input_customerOrder;
    }

    public long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerOrder() {
        return customerOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Order order = (Order) o;

        if (id != order.id)
            return false;
        if (!customerName.equals(order.customerName))
            return false;
        if (customerPhone.equals(order.customerPhone))
            return false;
        if (!customerOrder.equals(order.customerOrder))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + customerName.hashCode();
        result = 31 * result + customerPhone.hashCode();
        result = 31 * result + customerOrder.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id = " + id +
                ", customerName = '" + customerName + '\'' +
                ", customerPhone = '" + customerPhone + '\'' +
                ", customerOrder = '" + customerOrder + '\'' +
                '}';
    }

}