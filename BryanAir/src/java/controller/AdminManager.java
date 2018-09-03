/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.config.Configuration;
import services.logservice.LogService;
import java.util.logging.Logger;
import java.util.logging.Level;

import model.mo.Admin;

import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedAdminDAO;

import model.dao.DAOFactory;
import model.dao.AdminDAO;

import model.session.mo.LoggedAdmin;


/**
 *
 * @author Filippo
 */
public class AdminManager {
    
    private AdminManager(){
    }
    
    public static void view (HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        LoggedAdmin loggedAdmin;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            if(loggedAdmin != null){
                loggedAdminDAO.destroy();
                loggedAdmin = null;
            }
            
            
            request.setAttribute("viewUrl", "adminManager/login");
            
            request.setAttribute("loggedadmin", loggedAdmin);
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
        }
    }
    
    public static void viewHome (HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        LoggedAdmin loggedAdmin;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            request.setAttribute("viewUrl", "adminManager/home");
            request.setAttribute("loggedadmin", loggedAdmin);
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
        }
    }
    
    public static void login (HttpServletRequest request, HttpServletResponse response){
        
        Logger logger = LogService.getApplicationLogger();
        
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedAdmin loggedAdmin;
        String applicationMessage;
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            
            daoFactory.beginTransaction();
            
            AdminDAO adminDAO = daoFactory.getAdminDAO();
            
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            Long id = new Long(request.getParameter("adminId"));
            
            Admin admin = adminDAO.findByAdminFirstnameAndLastname(firstname, lastname);
            
            daoFactory.commitTransaction();
            
            if(admin == null || !admin.getId().equals(id)){
                loggedAdminDAO.destroy();
                applicationMessage = "Nome, cognome o id errati";
                loggedAdmin=null;
                
                request.setAttribute("admin", admin);
                request.setAttribute("viewUrl", "adminManager/login");
            } else {
                applicationMessage = "Username e password corretti";
                loggedAdmin = loggedAdminDAO.create(admin.getFirstname(), admin.getLastname());
                
                request.setAttribute("loggedadmin", loggedAdmin);
                request.setAttribute("loggedAdminOn",loggedAdmin!=null);
                request.setAttribute("viewUrl", "adminManager/home");
            }
            
            request.setAttribute("adminApplicationMessage", applicationMessage);
          
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
}
