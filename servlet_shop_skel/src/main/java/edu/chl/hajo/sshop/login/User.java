
package edu.chl.hajo.sshop.login;

/**
 * User of the application (used for login)
 * @author hajo
 */
public class User {

    private final String name;
    private final String passwd;
    public User(String name, String passwd) {
        this.passwd = passwd;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    } 
    
}
