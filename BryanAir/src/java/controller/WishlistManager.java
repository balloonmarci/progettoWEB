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
import model.dao.DAOFactory;
import model.dao.PushedFlightDAO;
import model.dao.WishlistDAO;
import model.mo.PushedFlight;
import model.mo.Wishlist;
import model.session.dao.LoggedAdminDAO;
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedAdmin;
import model.session.mo.LoggedUser;
import services.config.Configuration;
import services.logservice.LogService;

/**
 *
 * @author Marcello
 */
public class WishlistManager {
    
    
    private WishlistManager(){
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

                if(loggedUser != null){
                    PushedFlightDAO pushedFlightDAO = daoFactory.getPushedFlightDAO();
                    List<PushedFlight> list = pushedFlightDAO.getWishlist(loggedUser);
                    request.setAttribute("wishlist", list);
                }
    

            daoFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);

            request.setAttribute("viewUrl", "wishlistManager/view");

        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
    }
}
    
    
}
