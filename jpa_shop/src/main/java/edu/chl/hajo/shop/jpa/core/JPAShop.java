package edu.chl.hajo.shop.jpa.core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Shop is a container for other containers
 * NOTE: Uses Java 1.7
 *
 * @author hajo
 */
public class JPAShop implements IShop {

    private final IProductCatalogue productCatalogue;
    private final ICustomerRegistry customerRegistry;
    private final IOrderBook orderBook;

    public JPAShop(String puName) {
        productCatalogue =  new ProductCatalogue(puName);
        customerRegistry = new CustomerRegistry(puName);
        orderBook = new OrderBook(puName);
        Logger.getAnonymousLogger().log(Level.INFO, "Shop alive {0}", this.hashCode());
    }

    @Override
    public ICustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

    @Override
    public IOrderBook getOrderBook() {
        return orderBook;
    }

    @Override
    public IProductCatalogue getProductCatalogue() {
        return productCatalogue;
    }
}
