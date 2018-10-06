/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ConversationDAO;
import model.dao.DAOFactory;
import model.dao.MessageDAO;
import model.mo.Conversation;
import model.mo.Message;
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedUser;
import services.config.Configuration;
import services.logservice.LogService;

/**
 *
 * @author Marcello
 */
public class SupportManager {
    
    private SupportManager(){
    }
    
    public static void view(HttpServletRequest request, HttpServletResponse response) {
        
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
            
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConversationDAO conversationDAO = daoFactory.getConversationDAO();
            List<Conversation> conversations =  conversationDAO.findUserConversations(loggedUser);
            
            daoFactory.commitTransaction();
            request.setAttribute("conversations",conversations);
            request.setAttribute("viewUrl", "supportManager/view");
            
            
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
    
    //METODO DI TEST
    public static void chat(HttpServletRequest request, HttpServletResponse response) {
        
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
            
            request.setAttribute("viewUrl", "supportManager/chat");         
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
        }
    }
    
    //METODO DI TEST
    public static void getConvoTest(HttpServletRequest request, HttpServletResponse response) {
        
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
            
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConversationDAO conversationDAO = daoFactory.getConversationDAO();
            List<Conversation> conversations =  conversationDAO.findNewConversations();
            
            daoFactory.commitTransaction();
            request.setAttribute("conversations",conversations);
            request.setAttribute("viewUrl", "supportManager/convotest");
            
            
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
    
    public static void startConversation(HttpServletRequest request, HttpServletResponse response) {
        
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
            
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            String title = request.getParameter("title");
            String question = request.getParameter("question");
            
            ConversationDAO conversationDAO = daoFactory.getConversationDAO();
            MessageDAO messageDAO = daoFactory.getMessageDAO();
            Long l = conversationDAO.start(loggedUser, title);
            messageDAO.send(l, question, "user");
            List<Conversation> conversations =  conversationDAO.findUserConversations(loggedUser);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("conversations",conversations);
            request.setAttribute("viewUrl", "supportManager/view");
            
            
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
    
    public static void viewChat(HttpServletRequest request, HttpServletResponse response){
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
            
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            Long id = Long.parseLong(request.getParameter("convid"));
            MessageDAO messageDAO = daoFactory.getMessageDAO();
            ConversationDAO conversationDAO = daoFactory.getConversationDAO();
            
            Conversation conversation = conversationDAO.findById(id);
            
            List<Message> messages = messageDAO.findMessagesFromConversation(conversation);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("conversation", conversation);
            request.setAttribute("messages", messages);
            request.setAttribute("viewUrl", "supportManager/chat");
            
            
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
    
    public static void newUserMessage(HttpServletRequest request, HttpServletResponse response){
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
            
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            Long id = Long.parseLong(request.getParameter("convid"));
            String text = request.getParameter("text");
            
            MessageDAO messageDAO = daoFactory.getMessageDAO();
            ConversationDAO conversationDAO = daoFactory.getConversationDAO();
            
            Conversation conversation = conversationDAO.findById(id);
            messageDAO.send(conversation.getIdconv(), text, "user");
            List<Message> messages = messageDAO.findMessagesFromConversation(conversation);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("conversation", conversation);
            request.setAttribute("messages", messages);
            request.setAttribute("viewUrl", "supportManager/chat");
            
            
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
