
package edu.chl.hajo.shop.jpa.core;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Base class for all entities (later to be stored in database), 
 * Product, Order, etc
 * @author hajo
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{ // implements serialzable?
    
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; 

    
    /**
     *
     */
    public AbstractEntity() {
        ;
    }
   
    
    
    protected AbstractEntity(Long id){
        this.id = id;
    }
    
   // @Override
    public Long getId(){
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
