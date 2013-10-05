package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.db.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * All orders
 *
 * @author hajo
 */
public class OrderBook extends AbstractDAO<PurchaseOrder, Long>
        implements IOrderBook {

    // Factory method
    public static IOrderBook newInstance(String puName) {
        return new OrderBook(puName);
    }
    
    public OrderBook(String puName) {
        super(PurchaseOrder.class, puName);
    }

    @Override
    public List<PurchaseOrder> getByCustomer(Customer c) {
        List<PurchaseOrder> found = new ArrayList<>();
        for (PurchaseOrder po : getRange(0, getCount())) {
            if (po.getCustomer().equals(c)) {
                found.add(po);
            }
        }
        return found;
    }
}
