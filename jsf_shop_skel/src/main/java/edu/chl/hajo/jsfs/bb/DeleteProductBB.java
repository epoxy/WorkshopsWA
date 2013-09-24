/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.bb;

import edu.chl.hajo.jsfs.mb.ShopBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tomassellden
 */
@Named("delProduct")
@ConversationScoped
public class DeleteProductBB extends ConversationalBase {
   
    @Override
    protected void execute() {
       Logger.getAnonymousLogger().log(Level.INFO, "DeleteProductBB");
       shop.getProductCatalogue().remove(getId());
    }
    
}
