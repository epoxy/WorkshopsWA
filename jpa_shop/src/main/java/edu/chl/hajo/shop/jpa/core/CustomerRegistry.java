package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.db.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * All customers
 *
 * @author hajo
 */
public class CustomerRegistry extends AbstractDAO<Customer, Long>
        implements ICustomerRegistry {

    // Factory method
   public static ICustomerRegistry newInstance(String puName) {
        return new CustomerRegistry(puName);
    } 

    public CustomerRegistry(String puName) {
        super(Customer.class, puName);
    }

    @Override
    public List<Customer> getByName(String name) {
        List<Customer> found = new ArrayList<>();
        for (Customer c : getRange(0, getCount())) {
            if (c.getFname().equals(name) || c.getLname().equals(name)) {
                found.add(c);
            }
        }
        return found;
    }
}
