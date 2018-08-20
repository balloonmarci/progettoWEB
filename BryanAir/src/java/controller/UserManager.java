/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import services.config.Configuration;
import services.logservice.LogService;

import model.dao.exception.DuplicatedObjectException;
import model.dao.DAOFactory;
import model.dao.UserDAO;
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
            User user = userDAO.findByUserId(new Long(request.getParameter("userId")));

            user.setUsername(request.getParameter("username"));
            user.setFirstname(request.getParameter("firstname"));
            user.setLastname(request.getParameter("lastname"));
            user.setEmail(request.getParameter("email"));

            try {
                userDAO.update(user);
                
            } catch (DuplicatedObjectException e) {
                
                applicationMessage = "Username o email già esistenti";
                logger.log(Level.INFO, "Tentativo di inserimento di utente già esistente");
                request.setAttribute("user",user);
                
            }
            
            daoFactory.commitTransaction();
            request.setAttribute("viewUrl", "userManager/userProfile");
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
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
}

