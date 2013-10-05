package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.db.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * All products
 *
 * @author hajo
 */
public class ProductCatalogue extends AbstractDAO<Product, Long>
        implements IProductCatalogue {
    
    public ProductCatalogue(String puName) {
        super(Product.class, puName);
    }

    //Factory method
    public static IProductCatalogue newInstance(String puName) {
        return new ProductCatalogue(puName);
    }

    @Override
    public List<Product> getByName(String name) {
        List<Product> found = new ArrayList<>();
        for (Product p : getRange(0, getCount())) {
            if (p.getName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }
}
