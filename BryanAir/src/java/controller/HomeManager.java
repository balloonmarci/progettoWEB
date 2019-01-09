package controller;

/**
 *
 * @author Marcello
 */

import java.sql.Timestamp;
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
import model.session.mo.LoggedAdmin;
import model.session.dao.LoggedUserDAO;
import model.session.dao.LoggedAdminDAO;
import model.session.dao.SessionDAOFactory;

import model.dao.AirportDAO;
import model.mo.Airport;

import java.util.List;
import java.util.ArrayList;
import model.dao.ConcreteFlightDAO;
import model.dao.PushedFlightDAO;
import model.mo.PushedFlight;
import org.joda.time.DateTime;

public class HomeManager {

    private HomeManager(){
    }

    public static void view(HttpServletRequest request, HttpServletResponse response) {

      SessionDAOFactory sessionDAOFactory;
      DAOFactory daoFactory = null;
      LoggedUser loggedUser;
      LoggedAdmin loggedAdmin;

      Logger logger = LogService.getApplicationLogger();

      try{

          sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
          sessionDAOFactory.initSession(request, response);

          LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
          loggedUser = loggedUserDAO.find();

          LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
          loggedAdmin = loggedAdminDAO.find();

          if(loggedAdmin != null){
              loggedAdminDAO.destroy();
              loggedAdmin = null;
          }

          daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
          daoFactory.beginTransaction();

          commonView(daoFactory, request);          
          PushedFlightDAO pushedFlightDAO = daoFactory.getPushedFlightDAO();
          List<PushedFlight> pushedFlights = pushedFlightDAO.getPushedFlights();
          List<PushedFlight> wishlist = new ArrayList<PushedFlight>();
          if(loggedUser != null){
              wishlist = pushedFlightDAO.getWishlist(loggedUser);
          }
          
          daoFactory.commitTransaction();

          request.setAttribute("loggedOn", loggedUser != null);
          request.setAttribute("loggedUser", loggedUser);
          request.setAttribute("pushedFlights", pushedFlights);
          request.setAttribute("wishlist", wishlist);

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
            PushedFlightDAO pushedFlightDAO = daoFactory.getPushedFlightDAO();
            List<PushedFlight> pushedFlights = pushedFlightDAO.getPushedFlights();
            List<PushedFlight> wishlist = new ArrayList<PushedFlight>();
            
            if(user == null || !user.getPassword().equals(password)){
                loggedUserDAO.destroy();
                applicationMessage = "Username e password errati";
                loggedUser=null;
            } else {
                applicationMessage = "Username e password corretti";
                loggedUser = loggedUserDAO.create(user.getUserId(), user.getUsername(), user.getFirstname(), user.getLastname());
                wishlist = pushedFlightDAO.getWishlist(loggedUser);
            }            

            daoFactory.commitTransaction();
            request.setAttribute("wishlist", wishlist);
            request.setAttribute("pushedFlights", pushedFlights);
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
            
            PushedFlightDAO pushedFlightDAO = daoFactory.getPushedFlightDAO();
            List<PushedFlight> pushedFlights = pushedFlightDAO.getPushedFlights();
            
            daoFactory.commitTransaction();

            request.setAttribute("pushedFlights", pushedFlights);
        }catch(Exception e){
            logger.log(Level.SEVERE,"Controller Error", e);
            throw new RuntimeException(e);
        }
        List<PushedFlight> wishlist = new ArrayList<PushedFlight>();
        request.setAttribute("wishlist", wishlist);
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
    
    public static void deleteFromWishlist(HttpServletRequest request, HttpServletResponse response) {

      SessionDAOFactory sessionDAOFactory;
      DAOFactory daoFactory = null;
      LoggedUser loggedUser;
      LoggedAdmin loggedAdmin;

      Logger logger = LogService.getApplicationLogger();

      try{

          sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
          sessionDAOFactory.initSession(request, response);

          LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
          loggedUser = loggedUserDAO.find();

          LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
          loggedAdmin = loggedAdminDAO.find();

          if(loggedAdmin != null){
              loggedAdminDAO.destroy();
              loggedAdmin = null;
          }

          daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
          daoFactory.beginTransaction();

          commonView(daoFactory, request);   
          
          PushedFlightDAO pushedFlightDAO = daoFactory.getPushedFlightDAO();
          
          String flightcode = request.getParameter("flightcodeDelete");
          DateTime departure = new DateTime (Long.parseLong(request.getParameter("departuredateDelete")));
          DateTime arrival = new DateTime (Long.parseLong(request.getParameter("arrivaldateDelete")));
          pushedFlightDAO.deleteFromWishlist(loggedUser, flightcode, departure, arrival);
                    
          List<PushedFlight> pushedFlights = pushedFlightDAO.getPushedFlights();
          List<PushedFlight> wishlist = new ArrayList<PushedFlight>();
          if(loggedUser != null){
              wishlist = pushedFlightDAO.getWishlist(loggedUser);
          }
          
          daoFactory.commitTransaction();

          request.setAttribute("loggedOn", loggedUser != null);
          request.setAttribute("loggedUser", loggedUser);
          request.setAttribute("pushedFlights", pushedFlights);
          request.setAttribute("wishlist", wishlist);

          request.setAttribute("viewUrl", "homeManager/view");

      }catch(Exception e){
          logger.log(Level.SEVERE, "Controller Error", e);
          throw new RuntimeException(e);
      }
    }
    
    public static void addToWishlist(HttpServletRequest request, HttpServletResponse response) {

      SessionDAOFactory sessionDAOFactory;
      DAOFactory daoFactory = null;
      LoggedUser loggedUser;
      LoggedAdmin loggedAdmin;

      Logger logger = LogService.getApplicationLogger();

      try{

          sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
          sessionDAOFactory.initSession(request, response);

          LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
          loggedUser = loggedUserDAO.find();

          LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
          loggedAdmin = loggedAdminDAO.find();

          if(loggedAdmin != null){
              loggedAdminDAO.destroy();
              loggedAdmin = null;
          }

          daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
          daoFactory.beginTransaction();

          commonView(daoFactory, request);   
          
          PushedFlightDAO pushedFlightDAO = daoFactory.getPushedFlightDAO();
          
          String flightcode = request.getParameter("flightcodeAdd");
          DateTime departure = new DateTime (Long.parseLong(request.getParameter("departuredateAdd")));
          DateTime arrival = new DateTime (Long.parseLong(request.getParameter("arrivaldateAdd")));
          pushedFlightDAO.addToWishlist(loggedUser, flightcode, departure, arrival);
                    
          List<PushedFlight> pushedFlights = pushedFlightDAO.getPushedFlights();
          List<PushedFlight> wishlist = new ArrayList<PushedFlight>();
          if(loggedUser != null){
              wishlist = pushedFlightDAO.getWishlist(loggedUser);
          }
          
          daoFactory.commitTransaction();

          request.setAttribute("loggedOn", loggedUser != null);
          request.setAttribute("loggedUser", loggedUser);
          request.setAttribute("pushedFlights", pushedFlights);
          request.setAttribute("wishlist", wishlist);

          request.setAttribute("viewUrl", "homeManager/view");

      }catch(Exception e){
          logger.log(Level.SEVERE, "Controller Error", e);
          throw new RuntimeException(e);
      }
    }

    private static void commonView(DAOFactory daoFactory, HttpServletRequest request){

        List<Airport> airports = new ArrayList();

        AirportDAO airportDAO = daoFactory.getAirportDAO();
        airports = airportDAO.findAllAirport();

        request.setAttribute("airports", airports);
    }
}
