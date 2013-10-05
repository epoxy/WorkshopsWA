package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.core.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-10-04T17:05:10")
@StaticMetamodel(OrderItem.class)
public class OrderItem_ extends AbstractEntity_ {

    public static volatile SingularAttribute<OrderItem, Product> product;
    public static volatile SingularAttribute<OrderItem, Integer> quantity;

}