/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.sshop;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Epoxy
 */
@WebListener()
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Logger.getAnonymousLogger().log(Level.INFO, 
            "Session created. ID:" + se.getSession().getId());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Logger.getAnonymousLogger().log(Level.INFO, 
            "Session destroyed. ID:" + se.getSession().getId());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
