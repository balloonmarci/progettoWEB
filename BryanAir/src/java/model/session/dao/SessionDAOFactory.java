package model.session.dao;

/**
 *
 * @author Marcello
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.session.dao.CookieImpl.CookieSessionDAOFactory;

public abstract class SessionDAOFactory {
    
    //Lista di DAO supportati
    public static final String COOKIEIMPL = "CookieImpl";
    
    public abstract void initSession(HttpServletRequest request, HttpServletResponse response);
    public abstract LoggedUserDAO getLoggedUserDAO();
    public abstract LoggedAdminDAO getLoggedAdminDAO();
    
    public static SessionDAOFactory getSessionDAOFactory(String whichFactory){
        if(whichFactory.equals(COOKIEIMPL)){
            return new CookieSessionDAOFactory();
        } else {
            return null;
        }       
    }    
}
