/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.bb;

import edu.chl.hajo.jsfs.mb.ShopBean;
import edu.chl.hajo.jsfs.utils.ContainerNavigator;
import edu.chl.hajo.shop.core.LongIdComparator;

import edu.chl.hajo.shop.core.Product;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Must have in session scope, has state (in ContainerNavigator)!
 *
 * @author hajo
 */
@Named("products")
@SessionScoped  // NOTE enterprise package, else disaster!!!
public class ProductsBB implements Serializable {

    private ContainerNavigator cn;
   //
    @Inject private ShopBean shop;
    
        
    @PostConstruct
    public void post() {
        // We know all injection are done so shop not null (hopefully)
        cn = new ContainerNavigator(0, 3, shop.getProductCatalogue());
    }

    public List<Product> getRange() {
        // For now later database
        shop.getProductCatalogue().sort(new LongIdComparator<Product>());
        List<Product> ps = cn.getRange();
        return ps;
    }

    public void next() {
        cn.next();
    }

    public void prev() {
        cn.previous();
    }

    public String navigate(String target) {
        return target;
    }
}
