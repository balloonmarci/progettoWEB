package controller;

/**
 *
 * @author Marcello
 */


import java.util.ArrayList;
import java.util.List;
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
        List<Airport> airports;
        SessionDAOFactory sessionDAOFactory;
        LoggedAdmin loggedAdmin;
        DAOFactory daoFactory = null;
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            virtualFlights = virtualFlightDAO.findAllVirtualFlights();
            
            commonView(daoFactory, request);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("flights", virtualFlights);
            request.setAttribute("viewUrl", "abstractFlightManager/view");
            
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
            
            commonView(daoFactory, request);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("viewUrl", "abstractFlightManager/abstractFlightsFactory");
            request.setAttribute("loggedadmin", loggedAdmin);
            
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
    
    public static void viewVirtualFlight(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedAdmin loggedAdmin;
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            String flightCode = request.getParameter("flightcode");
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();           
            VirtualFlight virtualFlight = virtualFlightDAO.findByFlightCode(flightCode);
            commonView(daoFactory, request);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("viewUrl", "abstractFlightManager/abstractFlightsFactory");
            request.setAttribute("virtualFlight", virtualFlight);
            request.setAttribute("loggedadmin", loggedAdmin);
            
        }catch (Exception e) {
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
            vFlight.setPriceFirst(new Float(request.getParameter("pricefirst")));
            vFlight.setPriceSecond(new Float(request.getParameter("pricesecond")));
            
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
                
                applicationMessage = "Codice o tratta già esistente";
                
                logger.log(Level.INFO, "Tentativo di inserimento di uno iata già esistente");
            }
            
            commonView(daoFactory, request);
            daoFactory.commitTransaction();
            
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("viewUrl", "abstractFlightManager/abstractFlightsFactory");
            
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
    
    public static void searchVirtualFlights(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        List<VirtualFlight> virtualFlights;
        SessionDAOFactory sessionDAOFactory;
        LoggedAdmin loggedAdmin = null;
        DAOFactory daoFactory = null;
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            Airport depAirport = new Airport();
            depAirport.setAirportname(request.getParameter("departureairport"));
            depAirport.setCountry(request.getParameter("departurecountry"));
            
            Airport arrAirport = new Airport();
            arrAirport.setAirportname(request.getParameter("arrivalairport"));
            arrAirport.setCountry(request.getParameter("arrivalcountry"));
            
            String flightcode = request.getParameter("flightcode");
            String orderBy = request.getParameter("orderBy");
            String direction = request.getParameter("direction");
            
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            virtualFlights = virtualFlightDAO.findSelectedVirtualFlights(flightcode, depAirport, arrAirport, orderBy, direction);
            
            commonView(daoFactory, request);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("flights", virtualFlights);
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("viewUrl", "abstractFlightManager/view");
            
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
    
    public static void modifyVirtualFlight(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        VirtualFlight virtualFlight = new VirtualFlight();
        
        LoggedAdmin loggedAdmin = null;
        
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            String flightCode = request.getParameter("flightCode");
            virtualFlight.setFlightCode(flightCode);
            
            String departureAirportIata = request.getParameter("departureAirportIata");
            virtualFlight.setDepartureAirport(new Airport());
            virtualFlight.getDepartureAirport().setIata(departureAirportIata);
            
            String arrivalAirportIata = request.getParameter("arrivalAirportIata");
            virtualFlight.setArrivalAirport(new Airport());
            virtualFlight.getArrivalAirport().setIata(arrivalAirportIata);
            
            virtualFlight.setPriceFirst(Float.parseFloat(request.getParameter("pricefirst")));
            virtualFlight.setPriceSecond(Float.parseFloat(request.getParameter("pricesecond")));
            
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            
//            try{
            virtualFlightDAO.update(virtualFlight);
            List<VirtualFlight> virtualFlights;

            virtualFlights = virtualFlightDAO.findAllVirtualFlights();

//            request.setAttribute("flights", virtualFlights);
//            request.setAttribute("viewUrl", "abstractFlightManager/view");
                
//            }catch(DuplicatedObjectException e){
//                
//                logger.log(Level.INFO, "Tentativo di inserimento di un volo già esistente");
//                
//                String applicationMessage = "Tratta già esistente";
//                request.setAttribute("virtualFlight", virtualFlight);
//                request.setAttribute("applicationMessage", applicationMessage);
//                request.setAttribute("viewUrl", "flightManager/abstractFlightsFactory");
//            }
            
            commonView(daoFactory, request);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("flights", virtualFlights);
            request.setAttribute("viewUrl", "abstractFlightManager/view");
        
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
    
    public static void deleteVirtualFlight(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        VirtualFlight virtualFlight;
        
        LoggedAdmin loggedAdmin = null;
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            String flightCode = (String)request.getParameter("flightcode");
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            virtualFlight = virtualFlightDAO.findByFlightCode(flightCode);
            virtualFlightDAO.delete(virtualFlight);
            
            List<VirtualFlight> virtualFlights;
            virtualFlights = virtualFlightDAO.findAllVirtualFlights();
            
            commonView(daoFactory, request);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("flightcode", flightCode);
            request.setAttribute("flights", virtualFlights);
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("viewUrl", "abstractFlightManager/view");
            
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
