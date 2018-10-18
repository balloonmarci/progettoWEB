/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import model.dao.AirportDAO;

import services.config.Configuration;
import services.logservice.LogService;

import model.dao.exception.DuplicatedObjectException;
import model.dao.DAOFactory;
import model.dao.PushedFlightDAO;
import model.dao.UserDAO;
import model.mo.Airport;
import model.mo.PushedFlight;
import model.mo.User;

import model.session.mo.LoggedUser;
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;
/**
 *
 * @author Filippo
 */
public class UserManager {
    
    private UserManager(){
    }
    
    public static void view(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
                sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
                sessionDAOFactory.initSession(request, response);

                LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
                loggedUser = loggedUserDAO.find();

                daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
                daoFactory.beginTransaction();

                UserDAO userDAO = daoFactory.getUserDAO();
                User user = userDAO.findByUserId(loggedUser.getUserId());
                
                daoFactory.commitTransaction();
                
                user.setPassword(null);
                request.setAttribute("user", user);
                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("actionPage", "account");
                request.setAttribute("viewUrl", "userManager/profile");
            }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);

            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } 
            catch (Throwable t) {
            }
            throw new RuntimeException(e);
        } 
        finally {
            try {
                    if (daoFactory != null) {
                        daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
    
    public static void viewReg(HttpServletRequest request, HttpServletResponse response){
        
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
            
            request.setAttribute("viewUrl", "userManager/registration");
        }
        catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
        }
    }
    
    public static void register(HttpServletRequest request, HttpServletResponse response) {
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        Logger logger = LogService.getApplicationLogger();
        
        User user = null;
        LoggedUser loggedUser;
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            User u = new User();
            u.setUsername(request.getParameter("username"));
            u.setPassword(request.getParameter("password"));
            u.setEmail(request.getParameter("email"));
            u.setFirstname(request.getParameter("firstname"));
            u.setLastname(request.getParameter("lastname"));
            
            UserDAO userDAO = daoFactory.getUserDAO();
            
            try{
                
                user = userDAO.insert(u.getUsername(), u.getEmail(), u.getPassword(), u.getFirstname(), u.getLastname());
                LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
                //utilizza find come costruttore a null
                loggedUser = loggedUserDAO.find();
                loggedUser = loggedUserDAO.create(user.getUserId(), user.getUsername(), user.getFirstname(), user.getLastname());
                
                commonView(daoFactory, request);

                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("viewUrl", "homeManager/view");
                
            } catch (DuplicatedObjectException e) {
                
                u.setPassword(null);
                applicationMessage = "Username o email già esistenti";
                
                request.setAttribute("user", u);
                request.setAttribute("applicationMessage", applicationMessage);
                request.setAttribute("viewUrl", "userManager/registration");
                
                logger.log(Level.INFO, "Tentativo di inserimento di un utente già esistente");
            }
            
            daoFactory.commitTransaction();
            
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);

            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } 
            catch (Throwable t) {
            }
            throw new RuntimeException(e);
        } 
        finally {
            try {
                    if (daoFactory != null) {
                        daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
    
    public static void modify(HttpServletRequest request, HttpServletResponse response){
        
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
      
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();

            UserDAO userDAO = daoFactory.getUserDAO();
            User user = new User();
            user.setUserId(new Long(request.getParameter("userId")));
            user.setUsername(request.getParameter("username"));
            user.setFirstname(request.getParameter("nome"));
            user.setLastname(request.getParameter("cognome"));
            user.setEmail(request.getParameter("email"));

            try {
                userDAO.update(user);
                loggedUser.setFirstname(user.getFirstname());
                loggedUser.setLastname(user.getLastname());
                loggedUser.setUsername(user.getUsername());
            } catch (DuplicatedObjectException e) {
                
                applicationMessage = "Username o email già esistenti";
                logger.log(Level.INFO, "Tentativo di inserimento di utente già esistente");
            }
            
            daoFactory.commitTransaction();
            
            request.setAttribute("viewUrl", "userManager/profile");
            request.setAttribute("user",user);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("actionPage", "account");
            request.setAttribute("applicationMessage", applicationMessage);
            

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) {
                    daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
    
    public static void modifyPassword(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            String oldPassword = request.getParameter("oldpassword");
            String newPassword = request.getParameter("newpassword");
      
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.findByUserId(loggedUser.getUserId());
            
            if(user.getPassword().equals(oldPassword)){
                user.setPassword(newPassword);
                userDAO.updatePassword(user);
            } else {
                applicationMessage = "Password errata";
                request.setAttribute("applicationMessage", applicationMessage);
            }
            
            daoFactory.commitTransaction();
            
            user.setPassword(null);
            
            request.setAttribute("user",user);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("actionPage", "setpassword");
            request.setAttribute("viewUrl", "userManager/profile");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) {
                    daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
    
    public static void deleteAccount(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            String password = request.getParameter("password");
      
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.findByUserId(loggedUser.getUserId());
            
            if(user.getPassword().equals(password)){
                
                userDAO.delete(user);
                loggedUserDAO.destroy();
                loggedUser = null;
                commonView(daoFactory, request);
                
                request.setAttribute("viewUrl", "homeManager/view");
                
            } else {
                user.setPassword(null);
                applicationMessage = "Password errata";
                request.setAttribute("applicationMessage", applicationMessage);
                request.setAttribute("user",user);
                request.setAttribute("actionPage", "delete");
                request.setAttribute("viewUrl", "userManager/profile");
            }
            
            daoFactory.commitTransaction();
            
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) {
                    daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
    
    private static void commonView(DAOFactory daoFactory, HttpServletRequest request){

        List<Airport> airports = new ArrayList();
        
        AirportDAO airportDAO = daoFactory.getAirportDAO();
        airports = airportDAO.findAllAirport();
        
        PushedFlightDAO pushedFlightDAO = daoFactory.getPushedFlightDAO();
        List<PushedFlight> pushedFlights = pushedFlightDAO.getPushedFlights();

        request.setAttribute("airports", airports);
   }
    
    public static void prenotationView(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
                sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
                sessionDAOFactory.initSession(request, response);

                LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
                loggedUser = loggedUserDAO.find();

                //daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
                //daoFactory.beginTransaction();
                //daoFactory.commitTransaction();
                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("viewUrl", "userManager/prenotations");
            }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);

            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } 
            catch (Throwable t) {
            }
            throw new RuntimeException(e);
        } 
        finally {
            try {
                    if (daoFactory != null) {
                        daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
    
    public static void prenotationViewDetails(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
                sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
                sessionDAOFactory.initSession(request, response);

                LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
                loggedUser = loggedUserDAO.find();

                //daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
                //daoFactory.beginTransaction();
                //daoFactory.commitTransaction();
                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("viewUrl", "userManager/prenotationsDetails");
            }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);

            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } 
            catch (Throwable t) {
            }
            throw new RuntimeException(e);
        } 
        finally {
            try {
                    if (daoFactory != null) {
                        daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
}
    





