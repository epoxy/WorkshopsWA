package edu.chl.hajo.shop.jpa.core;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A purchase order
 *
 * @author hajo
 */
@Entity
public class PurchaseOrder extends AbstractEntity {
    // State of order
    /**
     *
     */
   // @Enumerated(EnumType.STRING)?!?!?
    
    public enum State {

        ACCEPTED,
        CANCELLED,
        INVOICED,
        UNINVOIDED,
        SHIPPED,
    }
    
    //@Temporal(Date)
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
   
    @JoinColumn
    @OneToMany(cascade = {CascadeType.ALL})
    private List<OrderItem> items;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Customer customer;
    
    private State state = State.ACCEPTED;

    public PurchaseOrder() {
        ;
    }
    public PurchaseOrder(Customer customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
    }

    public PurchaseOrder(Long id, Customer customer, List<OrderItem> items) {
        super(id);
        this.customer = customer;
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "id=" + getId() + ", date=" + date
                + ", items=" + items + ", customer=" + customer
                + ", state=" + state + '}';
    }
}
