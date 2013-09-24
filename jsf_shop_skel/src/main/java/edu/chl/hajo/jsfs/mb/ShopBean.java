/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.mb;

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.ShopFactory;
import java.io.Serializable;
import javax.inject.Singleton;

/**
 * Wrapper for shop
 *
 * @author hajo
 */
@Singleton
public class ShopBean implements Serializable { // implements Serializable?
   
    private final IShop s = ShopFactory.getShop(true);
    
    private ShopBean() {
        ;
    }
   
    public IProductCatalogue getProductCatalogue() {
        return s.getProductCatalogue();
    }
    
    
    
    
    
    
    
    
    
   /* public static synchronized ShopBean getInstance() {
        if (instance == null) {
            instance = new ShopBean();
        }
        return instance;
    }*/
    
    
}
/*public class ShopBean {
 // INSTANCE;
    
 private final IShop s;

 private ShopBean() {
 s = ShopFactory.getShop(true);
 }

 public IProductCatalogue getProductCatalogue() {
 return s.getProductCatalogue();
 }
 }*/
