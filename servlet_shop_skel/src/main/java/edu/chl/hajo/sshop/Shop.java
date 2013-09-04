
package edu.chl.hajo.sshop; 

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.ShopFactory; 

/**
 * This is a wrapper to make the shop a singleton
 * Could have used CDI (?) but too much for now, introduced later
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
