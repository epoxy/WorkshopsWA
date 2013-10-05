
package edu.chl.hajo.shop.jpa.db;

import java.util.Comparator;
import java.util.List;

/**
 * Basic contract for containers of Products, Customers, etc
 * 
 * T is type of elements in container
 * K is type of id (primary key)
 * 
 * @author hajo
 */
public interface IDAO<T, K> {

    public void add(T t);

    public void remove(K id);

    public T update(T t);

    public T find(K id);

    // Later done by database
    
    public List<T> getRange(int first, int nItems);

    public int getCount();
    
    public void removeAllShit();
}
