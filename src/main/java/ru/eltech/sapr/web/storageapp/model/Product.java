package ru.eltech.sapr.web.storageapp.model;

public class Product {
    private long id;
    private String name;
    private int price;
    private String comments;


    public Product(long input_id, String input_name, int input_price, String input_comments) {
        this.id = input_id;
        this.name = input_name;
        this.price = input_price;
        this.comments = input_comments;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;

        if (id != product.id)
            return false;
        if (!name.equals(product.name))
            return false;
        if (price != product.price)
            return false;
        if (!comments.equals(product.comments))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        result = 31 * result + comments.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", price = '" + price + '\'' +
                ", comments = '" + comments + '\'' +
                '}';
    }
}