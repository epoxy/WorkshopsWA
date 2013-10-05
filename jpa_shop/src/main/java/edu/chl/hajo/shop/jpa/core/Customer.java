package edu.chl.hajo.shop.jpa.core;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * A Customer
 * @author hajo
 */
@Entity
public class Customer extends AbstractEntity {

    @Transient
    private transient Cart cart = new Cart();
    @Embedded
    private Address address;
    @Column(nullable = false)
    private String fname;
    @Column(nullable = false)
    private String lname;
    @Column(nullable = false)
    private String email;
    
    public Customer() {
        
    }
    public Customer(Address address, String fname,
            String lname, String email) {
        this.address = address;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
    
    public Customer(Long id, Address address, String fname,
            String lname, String email) {
        super(id);
        this.address = address;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public void addProductToCart(Product product) {
        cart.add(product);
    }

    public void removeProductFromCart(Product product) {
        cart.remove(product);
    }

    public void emptyCart() {
        cart = new Cart();
    }

    public Address getAddress() {
        return address;
    }

    public Cart getCart() {
        return cart;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + getId() + ", address=" + address + ", fname=" + fname + ", lname=" + lname + ", email=" + email + '}';
    }
}
