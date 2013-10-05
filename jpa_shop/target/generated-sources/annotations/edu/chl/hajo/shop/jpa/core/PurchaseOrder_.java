package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.core.Customer;
import edu.chl.hajo.shop.jpa.core.OrderItem;
import edu.chl.hajo.shop.jpa.core.PurchaseOrder.State;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-10-04T17:05:10")
@StaticMetamodel(PurchaseOrder.class)
public class PurchaseOrder_ extends AbstractEntity_ {

    public static volatile ListAttribute<PurchaseOrder, OrderItem> items;
    public static volatile SingularAttribute<PurchaseOrder, State> state;
    public static volatile SingularAttribute<PurchaseOrder, Date> date;
    public static volatile SingularAttribute<PurchaseOrder, Customer> customer;

}