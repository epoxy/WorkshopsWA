/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.mb;

import java.io.Serializable;

/**
 *
 * @author hajo
 */
public class User implements Serializable {

    private final String name;
   
    User(String name) {
       this.name = name;
    }

    public String getName() {
        return name;
    }
}
