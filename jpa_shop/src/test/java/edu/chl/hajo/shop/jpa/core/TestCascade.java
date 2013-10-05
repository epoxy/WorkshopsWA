package edu.chl.hajo.shop.jpa.core;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Persisting a PurchaseOrder will cascade and persist OrderItems
 * but not other way round
 *
 * @author hajo
 */
public class TestCascade {


    static IShop shop;
    final static String PU = "shop_pu";
    final static String TEST_PU = "shop_test_pu";

    @BeforeClass
    public static void beforeClass() {
        // If using PU *must* clean tables
        shop = JPAShopFactory.getShop(TEST_PU);
    }

    // Note: Following test possible depends on this (bad, but hard to avoid)
    @Test
    public void testPersistAPurchaseOrder() {
        // Must have a Customer and a Product to be able persist a PurchaseOrder
        Address address = new Address();
        Customer c = new Customer(address, "fname", "lname", "email");
        shop.getCustomerRegistry().add(c);
        Product p1 = new Product("product1", 11.11);
        Product p2 = new Product("product2", 22.22);
        shop.getProductCatalogue().add(p1);
        shop.getProductCatalogue().add(p2);

        // Now persist the PurchaseOrder (should cascade) 
        c.addProductToCart(p1);
        c.addProductToCart(p1);
        c.addProductToCart(p2);
        List<OrderItem> items = c.getCart().getAsOrderItems();//rätt?
        PurchaseOrder o = new PurchaseOrder(c, items);
        shop.getOrderBook().add(o);

        // If persisted should have an id
        PurchaseOrder o1 = shop.getOrderBook().find(o.getId());
        assertTrue(o != o1);
        assertTrue(o.equals(o1));
        System.out.println("testdeleteItemFromPurchaseOrder");
        List<Customer> cs = shop.getCustomerRegistry().getByName("fname");
        System.out.println("cs is: " + cs.size());
        assertTrue(cs.size() == 1);
        List<PurchaseOrder> pos = shop.getOrderBook().getByCustomer(cs.get(0));
        assertTrue(pos.size() == 1);
        PurchaseOrder po =  pos.get(0);
        items = po.getItems();
        // If run test before
        assertTrue(items.size() == 2);
        
        // Must remove this manually from database
        OrderItem oi = items.get(0);
        List<OrderItem> itemsUpdate = new ArrayList<>();
        itemsUpdate.add(items.get(1));
        PurchaseOrder poUpdate = new PurchaseOrder(po.getId(), po.getCustomer(), itemsUpdate);
        
        // Updated ok 
        shop.getOrderBook().update(poUpdate);
        // Remove item from database
        shop.getOrderBook().remove(oi.getId()); //rätt?
        
        assertTrue(true);
    }
}
