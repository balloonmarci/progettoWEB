package controller;

/**
 *
 * @author Marcello
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import services.config.Configuration;
import services.logservice.LogService;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.mo.User;

import model.session.mo.LoggedUser;
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;

public class HomeManager {
    
    private HomeManager(){
    }
    
    public static void view(HttpServletRequest request, HttpServletResponse response) {
        
        SessionDAOFactory sessionDAOFactory;
        LoggedUser loggedUser;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            
            request.setAttribute("viewUrl", "homeManager/view");         
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
        }
    }
    
    
    public static void login(HttpServletRequest request, HttpServletResponse response) {
        
        Logger logger = LogService.getApplicationLogger();
        
        SessionDAOFactory sessionDAOFactory;
        LoggedUser loggedUser;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            //utilizza find come costruttore a null
            loggedUser = loggedUserDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.findByUsername(username);
            
            daoFactory.commitTransaction();
            
            if(user == null || !user.getPassword().equals(password)){
                loggedUserDAO.destroy();
                applicationMessage = "Username e password errati";
                loggedUser=null;
            } else {
                applicationMessage = "Username e password corretti";
                loggedUser = loggedUserDAO.create(user.getUserId(), user.getUsername(), user.getFirstname(), user.getLastname());
            }
            
            
            
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("loggedOn",loggedUser!=null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("viewUrl", "homeManager/view");
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            
            
            try {
                if(daoFactory != null){
                    daoFactory.rollbackTransaction();
                }
            }catch (Throwable t){
        }
        throw new RuntimeException(e);
        } finally {
            try {
                if(daoFactory != null) {
                    daoFactory.closeTransaction();
                }
            }catch(Throwable t){
            }
        }
    }  

    public static void logout(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        Logger logger = LogService.getApplicationLogger();
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUserDAO.destroy();
            
        }catch(Exception e){
            logger.log(Level.SEVERE,"Controller Error", e);
            throw new RuntimeException(e);
        }
        request.setAttribute("loggedOn", false);
        request.setAttribute("loggedUser", null);
        request.setAttribute("viewUrl", "homeManager/view");
    }
    
    public static void findAirport(HttpServletRequest request, HttpServletResponse response){
        //TODO
    }
}
