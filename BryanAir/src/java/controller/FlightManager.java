package controller;

/**
 *
 * @author Marcello
 */


import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import model.dao.exception.DuplicatedObjectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import services.config.Configuration;
import services.logservice.LogService;

import model.dao.DAOFactory;
import model.dao.AirportDAO;
import model.mo.Airport;
import model.mo.VirtualFlight;
import model.dao.VirtualFlightDAO;
import model.session.dao.LoggedAdminDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedAdmin;

public class FlightManager {
    private FlightManager(){
        
    }
    
    public static void view (HttpServletRequest request, HttpServletResponse response){
        
        Logger logger = LogService.getApplicationLogger();
        List<VirtualFlight> virtualFlights;
        DAOFactory daoFactory = null;
        
        try{
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            virtualFlights = virtualFlightDAO.findAllVirtualFlights();
            
            daoFactory.commitTransaction();
            
            request.setAttribute("flights", virtualFlights);
            request.setAttribute("viewUrl", "flightManager/view");
            
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
    
    public static void viewCreatePage(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        
        SessionDAOFactory sessionDAOFactory;
        LoggedAdmin loggedAdmin;
        DAOFactory daoFactory = null;
        AirportDAO airportDAO;
        List<Airport>airports;
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            airportDAO = daoFactory.getAirportDAO();
            airports = airportDAO.findAllAirport();
            
            daoFactory.commitTransaction();
            
            request.setAttribute("viewUrl", "flightManager/abstractFlightsFactory");
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("airports", airports);
            
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
    
    public static void createAbstractFlights(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        Logger logger = LogService.getApplicationLogger();
        
        VirtualFlight vFlight = new VirtualFlight();
        LoggedAdmin loggedAdmin = null;
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            
            
            vFlight.setFlightCode(request.getParameter("flightCode"));
            vFlight.setPriceFirst(new Float(request.getParameter("priceFirst")));
            vFlight.setPriceSecond(new Float(request.getParameter("priceSecond")));
            
            Airport depAirport = new Airport();
            depAirport.setIata(request.getParameter("departureAirportIata"));
            vFlight.setDepartureAirport(depAirport);
            
            Airport arrAirport = new Airport();
            arrAirport.setIata(request.getParameter("arrivalAirportIata"));
            vFlight.setArrivalAirport(arrAirport);
            
            
            daoFactory.beginTransaction();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            
            try{
                
                VirtualFlight virtualFlight = virtualFlightDAO.insert(vFlight.getFlightCode(), vFlight.getPriceFirst(), vFlight.getPriceSecond(), 
                                                                      vFlight.getDepartureAirport(), vFlight.getArrivalAirport());

            } catch (DuplicatedObjectException e) {
                
                applicationMessage = "Codice Iata o tratta già esistente";
                request.setAttribute("virtualFlight", vFlight);
                
                logger.log(Level.INFO, "Tentativo di inserimento di uno iata già esistente");
            }
            
            commonView(daoFactory, request);
            daoFactory.commitTransaction();
            
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("viewUrl", "flightManager/abstractFlightsFactory");
            
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
    
    private static void commonView(DAOFactory daoFactory, HttpServletRequest request){
        
        List<Airport> airports = new ArrayList();
        
        AirportDAO airportDAO = daoFactory.getAirportDAO();
        airports = airportDAO.findAllAirport();
        
        request.setAttribute("airports", airports);
    }
}
