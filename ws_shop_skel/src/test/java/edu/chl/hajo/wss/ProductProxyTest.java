/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.wss;

import edu.chl.hajo.shop.core.Product;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.Test;

/**
 * Will output product as XML in test window (and possible
 * some junk after?). Use this to test your annotations
 * @author hajo
 */
public class ProductProxyTest {

    @Test
    public void testMarchalProductProxy() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(ProductProxy.class);
        ProductProxy pp = new ProductProxy(new Product("test", 111));
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(pp, System.out);
    }
}