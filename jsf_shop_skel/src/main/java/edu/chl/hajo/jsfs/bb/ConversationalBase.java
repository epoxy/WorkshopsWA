package edu.chl.hajo.jsfs.bb;

import edu.chl.hajo.jsfs.mb.ShopBean;
import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.Product;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Common code for delete and edit (which uses conversational scope) 
 * @author hajo
 */
@ConversationScoped
public abstract class ConversationalBase implements Serializable {

    private Long id;
    
    @NotNull(message = "{common.notEmpty}")
    @Pattern(regexp="[a-zA_Z0-9]{4,20}", message="{product.name}")
    private String name;
    
    @NotNull(message = "{common.notEmpty}")
    @DecimalMax(value = "10000", message = "{product.price}")
    @DecimalMin(value = "0", message = "{product.price}")
    private String price;
    
    
    @Inject protected ShopBean shop;
    
    @Inject
    private Conversation conversation;
    
    
    // Must have String???
    public void setSelected(String id) {
        Logger.getAnonymousLogger().log(Level.INFO, "setSelected id={0}", id);
        if (conversation.isTransient()) {
            conversation.begin();
            
        }
        Product p = shop.getProductCatalogue().find(Long.valueOf(id));
        Logger.getAnonymousLogger().log(Level.INFO, "setSelected p={0}", p);
        this.id = p.getId();
        this.name = p.getName();
        this.price = String.valueOf(p.getPrice());
    }

    @PreDestroy  // Must have for back button etc.
    public void destroy() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public String actOnSelected() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        execute();
        return "products";
    }

    
    // Implemented by subclasses
    protected abstract void execute();

    protected IProductCatalogue getProductCatalogue() {
        return shop.getProductCatalogue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
