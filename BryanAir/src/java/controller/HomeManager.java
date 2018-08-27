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

import model.dao.AirportDAO;
import model.mo.Airport;

import java.util.List;
import java.util.ArrayList;

public class HomeManager {
    
    private HomeManager(){
    }
    
    public static void view(HttpServletRequest request, HttpServletResponse response) {
        
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            commonView(daoFactory, request);
            
            daoFactory.commitTransaction();
            
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
            
            commonView(daoFactory, request);
            
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
        DAOFactory daoFactory;
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUserDAO.destroy();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            commonView(daoFactory, request);
            daoFactory.commitTransaction();
            
        }catch(Exception e){
            logger.log(Level.SEVERE,"Controller Error", e);
            throw new RuntimeException(e);
        }
        request.setAttribute("loggedOn", false);
        request.setAttribute("loggedUser", null);
        request.setAttribute("viewUrl", "homeManager/view");
    }
    
    public static void findAirport(HttpServletRequest request, HttpServletResponse response){
        
        SessionDAOFactory sessionDAOFactory;
        LoggedUser loggedUser;        
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            String iata = request.getParameter("iata");
            
            if(loggedUser != null && iata != null) {
                
                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                
                daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
                daoFactory.beginTransaction();
                
                AirportDAO airportDAO = daoFactory.getAirportDAO();
                
                Airport airport = airportDAO.findByIata(iata);
                
                
                
                daoFactory.commitTransaction();
                
                if(airport == null){
                    applicationMessage = "Aeroporto non esistente";                    
                } else {
                    applicationMessage = "Aeroporto trovato";
                    request.setAttribute("iata", airport.getIata());
                    request.setAttribute("airportName", airport.getAirportname());
                    request.setAttribute("city", airport.getCity());
                }
                
                
                request.setAttribute("applicationMessage", applicationMessage);
                request.setAttribute("loggedOn",loggedUser!=null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("viewUrl", "homeManager/view");
                
            } 
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
    
    private static void commonView(DAOFactory daoFactory, HttpServletRequest request){
        
        List<Airport> airports = new ArrayList();
        
        AirportDAO airportDAO = daoFactory.getAirportDAO();
        airports = airportDAO.findAllAirport();
        
        request.setAttribute("airports", airports);
    }
}
