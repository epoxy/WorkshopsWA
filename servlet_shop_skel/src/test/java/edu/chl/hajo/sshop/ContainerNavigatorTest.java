/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.sshop;

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;
import edu.chl.hajo.shop.core.ShopFactory;
import org.junit.Test;

/**
 *
 * @author hajo
 */
public class ContainerNavigatorTest {

    @Test
    public void testEvenPreNext() {
        IShop s = ShopFactory.getShop(true);
        IProductCatalogue p = s.getProductCatalogue();
        ContainerNavigator<Product> n = new ContainerNavigator<>(0, 3, p);
        assert (p.getCount() == 6);
        assert (n.getFst() == 0);
        n.next();
        assert (n.getFst() == 3);
        n.next();
        assert (n.getFst() == 3);
        n.previous();
        assert (n.getFst() == 0);
        n.previous();
        assert (n.getFst() == 0);
    }

    @Test
    public void testOddPrevNext() {
        IShop s = ShopFactory.getShop(true);
        IProductCatalogue p = s.getProductCatalogue();
        ContainerNavigator<Product> n = new ContainerNavigator<>(0, 3, p);
        p.add(new Product("a", 0));
        assert (p.getCount() == 7);
        assert (n.getFst() == 0);
        n.next();
        assert (n.getFst() == 3);
        n.next();
        assert (n.getFst() == 6);
        n.previous();
        assert (n.getFst() == 3);
        n.previous();
        assert (n.getFst() == 0);

    }
}