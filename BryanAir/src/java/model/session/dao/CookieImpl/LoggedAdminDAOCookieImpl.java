/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.session.dao.CookieImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.session.mo.LoggedAdmin;
import model.session.dao.LoggedAdminDAO;
/**
 *
 * @author Filippo
 */
public class LoggedAdminDAOCookieImpl implements LoggedAdminDAO{
    HttpServletRequest request;
    HttpServletResponse response;
    
    public LoggedAdminDAOCookieImpl(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
    
    @Override
    public LoggedAdmin create(String firstname, String lastname) {
        LoggedAdmin loggedAdmin = new LoggedAdmin();
        loggedAdmin.setFirstname(firstname);
        loggedAdmin.setLastname(lastname);
        
        Cookie cookie;
        cookie = new Cookie("loggedAdmin", encode(loggedAdmin));
        cookie.setPath("/");
        response.addCookie(cookie);
        
        return loggedAdmin;        
    }
    
    @Override
    public void update(LoggedAdmin loggedAdmin) {
        Cookie cookie;
        cookie = new Cookie("loggedAdmin", encode(loggedAdmin));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void destroy() {
        Cookie cookie;
        cookie = new Cookie("loggedAdmin","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        
    }

    @Override
    public LoggedAdmin find() {
        Cookie[] cookies = request.getCookies();
        LoggedAdmin loggedAdmin = null;
        
        if(cookies != null){
            for(int i=0; i<cookies.length && loggedAdmin == null; i++){
                if(cookies[i].getName().equals("loggedAdmin")){
                    loggedAdmin = decode(cookies[i].getValue());
                }
            }
        }
        
        return loggedAdmin;
    }
    
    private String encode(LoggedAdmin loggedAdmin){
        String encodedAdmin;
        encodedAdmin = loggedAdmin.getFirstname() +"#"+ loggedAdmin.getLastname();
        return encodedAdmin;
    }
    
    private LoggedAdmin decode(String encodedAdmin){
        LoggedAdmin loggedAdmin = new LoggedAdmin();
        
        String[] values = encodedAdmin.split("#");
        loggedAdmin.setFirstname(values[0]);
        loggedAdmin.setLastname(values[1]);
        
        return loggedAdmin;        
    }
}
