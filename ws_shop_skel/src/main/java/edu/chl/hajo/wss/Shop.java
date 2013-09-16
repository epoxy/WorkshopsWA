
package edu.chl.hajo.wss;

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.ShopFactory;

/**
 * This is a wrapper to make the shop a singleton
 * @author hajo
 */
public enum Shop {
    INSTANCE;
    
    private final IShop s;
    
    private Shop(){
       s = ShopFactory.getShop(true);
    }
    public IProductCatalogue getProductCatalogue(){
        return s.getProductCatalogue(); 
    }
   
}
