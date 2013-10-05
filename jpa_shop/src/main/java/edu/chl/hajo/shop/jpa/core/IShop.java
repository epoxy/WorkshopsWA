package edu.chl.hajo.shop.jpa.core;

/**
 * Public interface for the shop
 * @author hajo
 */
public interface IShop {

    public ICustomerRegistry getCustomerRegistry();

    public IOrderBook getOrderBook();

    public IProductCatalogue getProductCatalogue();
}
