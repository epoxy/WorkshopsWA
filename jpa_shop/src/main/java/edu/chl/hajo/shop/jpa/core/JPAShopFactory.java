package edu.chl.hajo.shop.jpa.core;

/**
 * Static factory for Shops
 *
 * @author hajo
 */
public class JPAShopFactory {

    private JPAShopFactory() {
    }

    // If initTestData there will be some data to use
    public static IShop getShop(String puName) {
        JPAShop s = new JPAShop(puName);
        return s;
    }//rätt? labPM säger JPAShopFactory.getShop(String persistenceUnitName)
    

    // If we have no database we can use this
    private static void initTestData(JPAShop shop) {

        // Add new data
        shop.getProductCatalogue().add(new Product(1L, "banana", 11.11));
        shop.getProductCatalogue().add(new Product("apple", 22.22));
        shop.getProductCatalogue().add(new Product("pear", 33.33));
        shop.getProductCatalogue().add(new Product("pineapple", 44.44));
        shop.getProductCatalogue().add(new Product("orange", 55.55));
        shop.getProductCatalogue().add(new Product("pear", 66.66));
        
        shop.getCustomerRegistry().add(new Customer(new Address("aaa", 1, "aaa"),
                "arne", "arnesson", "arne@gmail.com"));
        shop.getCustomerRegistry().add(new Customer(new Address("bbbb", 2, "bbb"),
                "berit", "beritsson", "berit@gmail.com"));
        shop.getCustomerRegistry().add(new Customer(new Address("ccc", 3, "ccc"),
                "cecilia", "cecilia", "cecila@gmail.com"));

      
        Customer c = shop.getCustomerRegistry().getByName("arne").get(0);
        c.addProductToCart(shop.getProductCatalogue().getByName("banana").get(0));
        c.addProductToCart(shop.getProductCatalogue().getByName("apple").get(0));
        c.addProductToCart(shop.getProductCatalogue().getByName("pear").get(0));

        shop.getOrderBook().add(new PurchaseOrder(c, c.getCart().getAsOrderItems()));

    }
}
