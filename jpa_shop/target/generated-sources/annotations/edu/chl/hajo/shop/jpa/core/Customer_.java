package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.core.Address;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-10-04T17:05:10")
@StaticMetamodel(Customer.class)
public class Customer_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Customer, String> lname;
    public static volatile SingularAttribute<Customer, Address> address;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, String> fname;

}