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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AirportDAO;
import model.dao.CheckInDAO;
import model.dao.ConcreteFlightDAO;
import model.dao.DAOFactory;
import model.dao.PrenotationDAO;
import model.dao.PushedFlightDAO;
import model.dao.VirtualFlightDAO;

import model.mo.Airport;
import model.mo.ConcreteFlight;
import model.mo.Prenotation;
import model.mo.PrenotationView;
import model.mo.PushedFlight;
import model.mo.User;
import model.mo.VirtualFlight;

import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedUser;

import org.joda.time.DateTime;
import services.config.Configuration;
import services.logservice.LogService;

/**
 *
 * @author Filippo
 */
public class PrenotationManager {
    private PrenotationManager(){
    }
    
    public static void view (HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        LoggedUserDAO loggedUserDAO;
        LoggedUser loggedUser;
        DAOFactory daoFactory = null;
        SessionDAOFactory sessionDAOFactory;
        
        sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);
        
        loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
        loggedUser = loggedUserDAO.find();
        
        int numeroPosti = Integer.parseInt(request.getParameter("numeroposti"));
        
        String departureFlightCode = request.getParameter("departureflightcode");
        DateTime departureFlightDepartureDate = new DateTime(request.getParameter("departureflightdeparturedate"));
        DateTime departureFlightArrivalDate = new DateTime(request.getParameter("departureflightarrivaldate"));
        
        String returnFlightCode = request.getParameter("returnflightcode");
        DateTime returnFlightDepartureDate = new DateTime(request.getParameter("departuredate"));
        DateTime returnFlightArrivalDate = new DateTime(request.getParameter("arrivaldate"));
        
        try{
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            
            daoFactory.beginTransaction();
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            AirportDAO airportDAO = daoFactory.getAirportDAO();
            
            ConcreteFlight departureFlight = concreteFlightDAO.findByFlightCodeAndDate(departureFlightCode, 
                                                departureFlightDepartureDate, departureFlightArrivalDate);
            departureFlight.setVirtualFlight(virtualFlightDAO.findByFlightCode(departureFlightCode));
            String departureIata = departureFlight.getVirtualFlight().getDepartureAirport().getIata();
            String arrivalIata = departureFlight.getVirtualFlight().getArrivalAirport().getIata();
            departureFlight.getVirtualFlight().setDepartureAirport(airportDAO.findByIata(departureIata));
            departureFlight.getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arrivalIata));
            
            ConcreteFlight returnFlight = concreteFlightDAO.findByFlightCodeAndDate(returnFlightCode, 
                                                 returnFlightDepartureDate, returnFlightArrivalDate);
            returnFlight.setVirtualFlight(virtualFlightDAO.findByFlightCode(returnFlightCode));
            departureIata = returnFlight.getVirtualFlight().getDepartureAirport().getIata();
            arrivalIata = returnFlight.getVirtualFlight().getArrivalAirport().getIata();
            returnFlight.getVirtualFlight().setDepartureAirport(airportDAO.findByIata(departureIata));
            returnFlight.getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arrivalIata));
            
            daoFactory.commitTransaction();
            
            request.setAttribute("departureflight", departureFlight);
            request.setAttribute("returnflight", returnFlight);
            request.setAttribute("numeroposti", numeroPosti);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("applicationMessage", null);
            request.setAttribute("viewUrl", "prenotationManager/view");
            
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
    
    public static void onlyDepartureView (HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        LoggedUserDAO loggedUserDAO;
        LoggedUser loggedUser;
        DAOFactory daoFactory = null;
        SessionDAOFactory sessionDAOFactory;
        
        sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);
        
        loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
        loggedUser = loggedUserDAO.find();
        
        int numeroPosti = Integer.parseInt(request.getParameter("numeroposti"));
        
        String flightCode = request.getParameter("flightcode");
        DateTime departureDate = new DateTime(request.getParameter("departuredate"));
        DateTime arrivalDate = new DateTime(request.getParameter("arrivaldate"));
        
        try{
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            
            daoFactory.beginTransaction();
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            AirportDAO airportDAO = daoFactory.getAirportDAO();
            
            ConcreteFlight selectedFlight = concreteFlightDAO.findByFlightCodeAndDate(flightCode, 
                                                 departureDate, arrivalDate);
            selectedFlight.setVirtualFlight(virtualFlightDAO.findByFlightCode(flightCode));
            String departureIata = selectedFlight.getVirtualFlight().getDepartureAirport().getIata();
            String arrivalIata = selectedFlight.getVirtualFlight().getArrivalAirport().getIata();
            selectedFlight.getVirtualFlight().setDepartureAirport(airportDAO.findByIata(departureIata));
            selectedFlight.getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arrivalIata));
            
            daoFactory.commitTransaction();
            
            request.setAttribute("departureflight", selectedFlight);
            request.setAttribute("numeroposti", numeroPosti);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("applicationMessage", null);
            request.setAttribute("viewUrl", "prenotationManager/view");
            
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
    
    public static void onlyDepartureViewMillis (HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        LoggedUserDAO loggedUserDAO;
        LoggedUser loggedUser;
        DAOFactory daoFactory = null;
        SessionDAOFactory sessionDAOFactory;
        
        sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);
        
        loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
        loggedUser = loggedUserDAO.find();
        
        int numeroPosti = Integer.parseInt(request.getParameter("numeroposti"));
        
        String flightCode = request.getParameter("flightcode");
        DateTime departureDate = new DateTime(Long.parseLong(request.getParameter("departuredate")));
        DateTime arrivalDate = new DateTime(Long.parseLong(request.getParameter("arrivaldate")));
        
        try{
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            
            daoFactory.beginTransaction();
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            AirportDAO airportDAO = daoFactory.getAirportDAO();
            
            ConcreteFlight selectedFlight = concreteFlightDAO.findByFlightCodeAndDate(flightCode, 
                                                 departureDate, arrivalDate);
            selectedFlight.setVirtualFlight(virtualFlightDAO.findByFlightCode(flightCode));
            String departureIata = selectedFlight.getVirtualFlight().getDepartureAirport().getIata();
            String arrivalIata = selectedFlight.getVirtualFlight().getArrivalAirport().getIata();
            selectedFlight.getVirtualFlight().setDepartureAirport(airportDAO.findByIata(departureIata));
            selectedFlight.getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arrivalIata));
            
            daoFactory.commitTransaction();
            
            request.setAttribute("departureflight", selectedFlight);
            request.setAttribute("numeroposti", numeroPosti);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("applicationMessage", null);
            request.setAttribute("viewUrl", "prenotationManager/view");
            
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
    
    public static void createPrenotation(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        LoggedUserDAO loggedUserDAO;
        LoggedUser loggedUser;
        DAOFactory daoFactory = null;
        SessionDAOFactory sessionDAOFactory;
        Prenotation prenotation;
        
        try{
        sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);
        
        loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
        loggedUser = loggedUserDAO.find();
        
        int numeroPosti = Integer.parseInt(request.getParameter("numeroposti"));
        int departureClass = Integer.parseInt(request.getParameter("departureclass"));
        float departurePrice = Float.parseFloat(request.getParameter("departureprice"));
        ConcreteFlight departureFlight = new ConcreteFlight();
        departureFlight.setVirtualFlight(new VirtualFlight());
        departureFlight.getVirtualFlight().setFlightCode(request.getParameter("departureflightcode"));
        departureFlight.setDate(new DateTime(request.getParameter("departureflightdeparturedate")));
        departureFlight.setArrivalDate(new DateTime(request.getParameter("departureflightarrivaldate")));
        
        int returnClass = Integer.parseInt(request.getParameter("returnclass"));
        float returnPrice = Float.parseFloat(request.getParameter("returnprice"));
        ConcreteFlight returnFlight = new ConcreteFlight();
        returnFlight.setVirtualFlight(new VirtualFlight());
        returnFlight.getVirtualFlight().setFlightCode(request.getParameter("returnflightcode"));
        returnFlight.setDate(new DateTime(request.getParameter("returnflightdeparturedate")));
        returnFlight.setArrivalDate(new DateTime(request.getParameter("returnflightarrivaldate")));
        
        User user = new User();
        user.setUserId(loggedUser.getUserId());
        
        List<Prenotation> departurePrenotations = new ArrayList();
        
        for(int i=1; i <= numeroPosti; i++){
            prenotation = new Prenotation();
            prenotation.setClas(departureClass);
            prenotation.setPricetotal(departurePrice);
            prenotation.setPassengerfirstname(request.getParameter("passengerfirstname"+i));
            prenotation.setPassengerlastname(request.getParameter("passengerlastname"+i));
            prenotation.setPassengerTitle(request.getParameter("passengertitle"+i));
            prenotation.setPrenotationDate(DateTime.now());
            prenotation.setConcreteFlight(departureFlight);
            prenotation.setUser(user);
            departurePrenotations.add(prenotation);
        }
        
        List<Prenotation> returnPrenotations = new ArrayList();
        
        for(int i=0; i < numeroPosti; i++){
            prenotation = new Prenotation();
            prenotation.setClas(returnClass);
            prenotation.setPricetotal(returnPrice);
            prenotation.setPassengerfirstname(departurePrenotations.get(i).getPassengerfirstname());
            prenotation.setPassengerlastname(departurePrenotations.get(i).getPassengerlastname());
            prenotation.setPassengerTitle(departurePrenotations.get(i).getPassengerTitle());
            prenotation.setPrenotationDate(DateTime.now());
            prenotation.setConcreteFlight(returnFlight);
            prenotation.setUser(user);
            returnPrenotations.add(prenotation);
        }
        
        daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
        
        daoFactory.beginTransaction();
        PrenotationDAO prenotationDAO = daoFactory.getPrenotationDAO();
        prenotationDAO.newPrenotation(departurePrenotations);
        prenotationDAO.newPrenotation(returnPrenotations);
        
        ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
        
        ConcreteFlight concreteDepartureFlight = concreteFlightDAO.findByFlightCodeAndDate(
              departureFlight.getVirtualFlight().getFlightCode(), departureFlight.getDate(), departureFlight.getArrivalDate());
        ConcreteFlight concreteReturnFlight = concreteFlightDAO.findByFlightCodeAndDate(
              returnFlight.getVirtualFlight().getFlightCode(), returnFlight.getDate(), returnFlight.getArrivalDate());
        
        if(departureClass == 1)
            concreteDepartureFlight.setSeatFirst(concreteDepartureFlight.getSeatFirst() - numeroPosti);
        else
            concreteDepartureFlight.setSeatSecond(concreteDepartureFlight.getSeatSecond() - numeroPosti);
        
        if(returnClass == 1)
            concreteReturnFlight.setSeatFirst(concreteReturnFlight.getSeatFirst() - numeroPosti);
        else
            concreteReturnFlight.setSeatSecond(concreteReturnFlight.getSeatSecond() - numeroPosti);
        
        concreteFlightDAO.update(concreteDepartureFlight);
        concreteFlightDAO.update(concreteReturnFlight);
        
        commonView(daoFactory, request, loggedUser);
        
        
        
        
        daoFactory.commitTransaction();
        
        request.setAttribute("viewUrl", "homeManager/view");
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedOn", true);
        request.setAttribute("applicationMessage", null); 
        
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
    
    public static void createOnlyDeparturePrenotation(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        LoggedUserDAO loggedUserDAO;
        LoggedUser loggedUser;
        DAOFactory daoFactory = null;
        SessionDAOFactory sessionDAOFactory;
        Prenotation prenotation;
        
        try{
        sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);
        
        loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
        loggedUser = loggedUserDAO.find();
        
        int numeroPosti = Integer.parseInt(request.getParameter("numeroposti"));
        int departureClass = Integer.parseInt(request.getParameter("departureclass"));
        float departurePrice = Float.parseFloat(request.getParameter("departureprice"));
        ConcreteFlight departureFlight = new ConcreteFlight();
        departureFlight.setVirtualFlight(new VirtualFlight());
        departureFlight.getVirtualFlight().setFlightCode(request.getParameter("departureflightcode"));
        departureFlight.setDate(new DateTime(request.getParameter("departureflightdeparturedate")));
        departureFlight.setArrivalDate(new DateTime(request.getParameter("departureflightarrivaldate")));
        
        User user = new User();
        user.setUserId(loggedUser.getUserId());
        
        List<Prenotation> prenotations = new ArrayList();
        
        for(int i=1; i <= numeroPosti; i++){
            prenotation = new Prenotation();
            prenotation.setClas(departureClass);
            prenotation.setPricetotal(departurePrice);
            prenotation.setPassengerfirstname(request.getParameter("passengerfirstname"+i));
            prenotation.setPassengerlastname(request.getParameter("passengerlastname"+i));
            prenotation.setPassengerTitle(request.getParameter("passengertitle"+i));
            prenotation.setPrenotationDate(DateTime.now());
            prenotation.setConcreteFlight(departureFlight);
            prenotation.setUser(user);
            prenotations.add(prenotation);
        }
         
        
        daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
        
        daoFactory.beginTransaction();
        PrenotationDAO prenotationDAO = daoFactory.getPrenotationDAO();
        prenotationDAO.newPrenotation(prenotations);
        
        ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
        
        ConcreteFlight concreteFlight = concreteFlightDAO.findByFlightCodeAndDate(
              departureFlight.getVirtualFlight().getFlightCode(), departureFlight.getDate(), departureFlight.getArrivalDate());
        if(departureClass == 1)
            concreteFlight.setSeatFirst(concreteFlight.getSeatFirst() - numeroPosti);
        else
            concreteFlight.setSeatSecond(concreteFlight.getSeatSecond() - numeroPosti);
        
        concreteFlightDAO.update(concreteFlight);
        commonView(daoFactory, request, loggedUser);
        daoFactory.commitTransaction();
        
        request.setAttribute("viewUrl", "homeManager/view");
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedOn", true);
        request.setAttribute("applicationMessage", null); 
        
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
    
    private static void commonView(DAOFactory daoFactory, HttpServletRequest request, LoggedUser loggedUser){

        List<Airport> airports = new ArrayList();
        PushedFlightDAO pushedFlightDAO = daoFactory.getPushedFlightDAO();
        List<PushedFlight> pushedFlights = pushedFlightDAO.getPushedFlights();

        AirportDAO airportDAO = daoFactory.getAirportDAO();
        airports = airportDAO.findAllAirport();
        
        List<PushedFlight> wishlist = new ArrayList<PushedFlight>();
        if(loggedUser != null){
            wishlist = pushedFlightDAO.getWishlist(loggedUser);
        }
        request.setAttribute("wishlist", wishlist);
        request.setAttribute("airports", airports);
        request.setAttribute("pushedFlights", pushedFlights);
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

                daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
                
                daoFactory.beginTransaction();
                
                PrenotationDAO prenotationDAO = daoFactory.getPrenotationDAO();
                
                List<PrenotationView> prenotations = prenotationDAO.findUserPrenotations(loggedUser);
                
                List<PrenotationView> checkPrenotations = prenotationDAO.findUserPrenotationsCheckIn(loggedUser);
                
                daoFactory.commitTransaction();
                request.setAttribute("prenotations", prenotations);
                request.setAttribute("checkprenotations", checkPrenotations);
                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("viewUrl", "prenotationManager/prenotations");
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
                
                String flightcode = request.getParameter("flightcode");
                DateTime departuredate = new DateTime(Long.parseLong(request.getParameter("departuredate")));
                DateTime arrivaldate = new DateTime(Long.parseLong(request.getParameter("arrivaldate")));
                daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
                
                daoFactory.beginTransaction();
                
                PrenotationDAO prenotationDAO = daoFactory.getPrenotationDAO();
                List<Prenotation> prenotations = prenotationDAO.findPrenotationDetail(loggedUser, flightcode, departuredate, arrivaldate);
                
                daoFactory.commitTransaction();
                
                request.setAttribute("prenotations", prenotations);
                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("viewUrl", "prenotationManager/prenotationsDetails");
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
    
    public static void prenotationViewCheckIn(HttpServletRequest request, HttpServletResponse response){
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
                
                String flightcode = request.getParameter("flightcode");
                DateTime departuredate = new DateTime(Long.parseLong(request.getParameter("departuredate")));
                DateTime arrivaldate = new DateTime(Long.parseLong(request.getParameter("arrivaldate")));
                daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
                
                daoFactory.beginTransaction();
                
                PrenotationDAO prenotationDAO = daoFactory.getPrenotationDAO();
                List<Prenotation> prenotations = prenotationDAO.findPrenotationDetail(loggedUser, flightcode, departuredate, arrivaldate);
                
                daoFactory.commitTransaction();
                
                request.setAttribute("prenotations", prenotations);
                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("viewUrl", "prenotationManager/prenotationsCheckIn");
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
    
    public static void insertCheckIn(HttpServletRequest request, HttpServletResponse response){
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
                
                int passengers = Integer.parseInt(request.getParameter("passengers"));
                ArrayList<String> doctype = new ArrayList<String>(passengers);
                ArrayList<String> doccode = new ArrayList<String>(passengers);
                ArrayList<Long> prencode = new ArrayList<Long>(passengers);
                
                for(int i=0; i<passengers; i++){
                    doctype.add(request.getParameter("documento"+Integer.toString(i)));
                    doccode.add(request.getParameter("documentocodice"+Integer.toString(i)));
                    prencode.add(Long.parseLong(request.getParameter("prencode"+Integer.toString(i))));
                }                
                
                daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
                
                daoFactory.beginTransaction();
                
                PrenotationDAO prenotationDAO = daoFactory.getPrenotationDAO();
                CheckInDAO checkInDAO = daoFactory.getCheckInDAO();
                for(int i=0; i<passengers; i++){
                    checkInDAO.insertCheckIns(doctype.get(i), doccode.get(i), prencode.get(i));
                }
                List<PrenotationView> prenotations = prenotationDAO.findUserPrenotations(loggedUser);
                
                List<PrenotationView> checkPrenotations = prenotationDAO.findUserPrenotationsCheckIn(loggedUser);
                
                daoFactory.commitTransaction();
                request.setAttribute("prenotations", prenotations);
                request.setAttribute("checkprenotations", checkPrenotations);
                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("viewUrl", "prenotationManager/prenotations");
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
