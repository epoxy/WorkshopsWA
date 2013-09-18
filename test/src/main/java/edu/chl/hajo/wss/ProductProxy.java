package edu.chl.hajo.wss;


import edu.chl.hajo.shop.core.Product;
import javax.xml.bind.annotation.*;


/**
 * Need this because translation from XML to JSON
 * @author hajo
 */
@XmlRootElement(name="Product")
@XmlAccessorType(XmlAccessType.PROPERTY)

public class ProductProxy {

    // The wrapped product
    private Product product;

    protected ProductProxy() { // Must have
    }
    
    
    public ProductProxy(Product product) { 
        this.product = product; 
    }
    
   @XmlElement(name="name")
    public String getName() {
        return product.getName();
    }

    @XmlElement(name="id")
    public Long getId() {
        return product.getId();
    }

    @XmlElement(name="price")
    public double getPrice() {
        return product.getPrice();
    }
}
