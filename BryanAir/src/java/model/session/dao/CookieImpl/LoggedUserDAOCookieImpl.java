package model.session.dao.CookieImpl;

/**
 *
 * @author Marcello
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.session.mo.LoggedUser;
import model.session.dao.LoggedUserDAO;

public class LoggedUserDAOCookieImpl implements LoggedUserDAO {
    
    HttpServletRequest request;
    HttpServletResponse response;
    
    public LoggedUserDAOCookieImpl(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    @Override
    public LoggedUser create(Long userId, String username, String firstname, String lastname) {
        LoggedUser loggedUser = new LoggedUser();
        loggedUser.setUserId(userId);
        loggedUser.setUsername(username);
        loggedUser.setFirstname(firstname);
        loggedUser.setLastname(lastname);
        
        Cookie cookie;
        cookie = new Cookie("loggedUser", encode(loggedUser));
        cookie.setPath("/");
        response.addCookie(cookie);
        
        return loggedUser;        
    }

    @Override
    public void update(LoggedUser loggedUser) {
        Cookie cookie;
        cookie = new Cookie("loggedUser", encode(loggedUser));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void destroy() {
        Cookie cookie;
        cookie = new Cookie("loggedUser","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        
    }

    @Override
    public LoggedUser find() {
        Cookie[] cookies = request.getCookies();
        LoggedUser loggedUser = null;
        
        if(cookies != null){
            for(int i=0; i<cookies.length && loggedUser == null; i++){
                if(cookies[i].getName().equals("loggedUser")){
                    loggedUser = decode(cookies[i].getValue());
                }
            }
        }
        
        return loggedUser;
    }
    
    private String encode(LoggedUser loggedUser){
        String encodedUser;
        encodedUser = loggedUser.getUserId()+ "#"+ loggedUser.getUsername() + "#" +loggedUser.getFirstname() +"#"+ loggedUser.getLastname();
        return encodedUser;
    }
    
    private LoggedUser decode(String encodedUser){
        LoggedUser loggedUser = new LoggedUser();
        
        String[] values = encodedUser.split("#");
        loggedUser.setUserId(Long.parseLong(values[0]));
        loggedUser.setUsername(values[1]);
        loggedUser.setFirstname(values[2]);
        loggedUser.setLastname(values[3]);
        
        return loggedUser;        
    }    
}
