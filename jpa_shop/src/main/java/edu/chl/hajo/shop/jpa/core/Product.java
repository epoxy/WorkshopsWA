package edu.chl.hajo.shop.jpa.core;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * A Product
 * @author hajo
 */
@Entity
public class Product extends AbstractEntity {
    
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    
    public Product() {
        ;
    }
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
     
    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", name=" + name + ", price=" + price + '}';
    }
}
