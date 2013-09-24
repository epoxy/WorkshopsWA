/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.bb;

import edu.chl.hajo.shop.core.Product;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tomassellden
 */
@Named("editProduct")
@ConversationScoped
public class EditProductBB extends ConversationalBase {

    @Override
    protected void execute() {
        shop.getProductCatalogue().update(new Product(getId(), getName(), Double.parseDouble(getPrice())));



    }
}
