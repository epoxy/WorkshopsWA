package edu.chl.hajo.shop.jpa.db;

import edu.chl.hajo.shop.jpa.db.IDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.exceptions.ExceptionHandler;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.tools.schemaframework.SchemaManager;
//isa richard maria floden filip christian
/**
 * A container for entities, base class for OrderBook, ProductCatalogue,
 * CustomerRegistry The fundamental common operations are here (CRUD).
 *
 * T is type for items in container K is type of id (primary key)
 *
 * @author hajo
 */
public abstract class AbstractDAO<T, K>
        implements IDAO<T, K> {

    private EntityManagerFactory emf;
    private final Class<T> clazz;

    protected AbstractDAO(Class<T> clazz, String puName) {
        this.clazz = clazz;
        emf = Persistence.createEntityManagerFactory(puName);
    }
    
    
    
    @Override
    public void add(T t) {
        EntityManager em = null;
        try {
            //create
            em = emf.createEntityManager();
           // Logger.getAnonymousLogger().log(Level.INFO, "AbstractDAO add: getEntityManager");
            // Must handle transaction, this is application managed 
            // transaction (RESOURCE_LOCAL)
            em.getTransaction().begin();
           // Logger.getAnonymousLogger().log(Level.INFO, "AbstractDAO add: getTransaction().begin()");
            em.persist(t);
            //Logger.getAnonymousLogger().log(Level.INFO, "AbstractDAO add: persist");
            em.getTransaction().commit();
            //Logger.getAnonymousLogger().log(Level.INFO, "AbstractDAO add: getTransaction commit");
        } catch (Exception ex) {
            System.out.println("add");
            System.out.println("getCount" + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public void remove(K id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            T t = em.getReference(clazz, id);
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("remove " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public T update(T t) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            t = em.merge(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("update " +  ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return t;
    }

    @Override
    public T find(K id) {
        EntityManager em = emf.createEntityManager();
        T t = em.find(clazz, id);
        return t;
    }

    @Override
    public List<T> getRange(int first, int nItems) {
        EntityManager em = emf.createEntityManager();
        List<T> found = new ArrayList<>();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clazz));
            Query q = em.createQuery(cq);
            found.addAll(q.getResultList());
        } catch (Exception ex) {
            System.out.println("get" + ex.getMessage());
        } finally {
            em.close();
        }
        
        List<T> realfound = new ArrayList<>();
        for(int i = 0; i < nItems; i++) 
            realfound.add(found.get(i));
        
        return realfound;
    }

    @Override
    public int getCount() {
        EntityManager em = null;
        int count = -1;
        try {
            em = emf.createEntityManager();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            //Logger.getAnonymousLogger().log(Level.INFO, "AbstractDAO getCount:  CriteriaQuery");
            Root<T> rt = cq.from(clazz);
            //Logger.getAnonymousLogger().log(Level.INFO, "AbstractDAO getCount:  root");
            cq.select(em.getCriteriaBuilder().count(rt));
            //Logger.getAnonymousLogger().log(Level.INFO, "AbstractDAO getCount:  cq.select");
            Query q = em.createQuery(cq);
            //Logger.getAnonymousLogger().log(Level.INFO, "AbstractDAO getCount:  createQuery");
            count = ((Long) q.getSingleResult()).intValue();
            Logger.getAnonymousLogger().log(Level.INFO, "AbstractDAO getCount:  count = " + count );
        } catch (Exception ex) {
            System.out.println("duude");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return count;
    }

    // @Override
    public void sort(Comparator<T> comp) {
        //Do we need this?
    }
    @Override
    public void removeAllShit() {
     
        EntityManager em;
        try {
            em = emf.createEntityManager();
            SchemaManager sm = new SchemaManager(em.unwrap(JpaEntityManager.class).getServerSession());
            //sm.dropTable("PURCHASEORDER_ORDERITEM");
            sm.dropTable("ORDERITEM");
            sm.dropTable("PURCHASEORDER");
            sm.dropTable("PRODUCT");
            sm.dropTable("CUSTOMER");
            sm.dropTable("SEQUENCE");
            sm.createDefaultTables(true);
        } catch (Exception ex) {
            System.out.println("RemoveAllShit={" + ex.getMessage() + "}");
        } finally {
 
        }
    }
}
