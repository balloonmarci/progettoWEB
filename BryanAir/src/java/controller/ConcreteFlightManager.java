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
import model.dao.AdminDAO;

import services.config.Configuration;
import services.logservice.LogService;

import model.dao.DAOFactory;
import model.dao.AirportDAO;
import model.mo.VirtualFlight;
import model.mo.Admin;
import model.dao.VirtualFlightDAO;
import model.mo.ConcreteFlight;
import model.dao.ConcreteFlightDAO;
import model.dao.exception.DuplicatedObjectException;
import model.session.dao.LoggedAdminDAO;
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedAdmin;
import model.session.mo.LoggedUser;
import org.joda.time.DateTime;

/**
 *
 * @author Filippo
 */
public class ConcreteFlightManager {
    private ConcreteFlightManager(){
    }
    
    public static void view (HttpServletRequest request, HttpServletResponse response){
        
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        LoggedAdmin loggedAdmin;
        
        List<ConcreteFlight> concreteFlights;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        
        sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);
            
        LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
        loggedAdmin = loggedAdminDAO.find();
        
        try{
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
          
            String flightCode = (String)request.getParameter("flightcode");
            
            daoFactory.beginTransaction();
            commonView(daoFactory, request, flightCode);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("flightcode", flightCode);
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("viewUrl", "flightManager/concreteFlightsFactory");
            
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
    
    /*public static void viewConcreteFlightPerReturnDate (HttpServletRequest request, HttpServletResponse response){
        
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        LoggedUser loggedUser = null;
        
        List<ConcreteFlight> concreteDepartureFlights;
        List<ConcreteFlight> concreteReturnFlights;
        
        VirtualFlight virtualFlight;
        DAOFactory daoFactory = null;
        
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            AirportDAO airportDAO = daoFactory.getAirportDAO();
            
            String departureairportname = (String)request.getParameter("departureAirportName");
            String arrivalairportname = (String)request.getParameter("arrivalAirportName");
            
            String date = request.getParameter("departuredate");
            DateTime departuredate = new DateTime(date);
            
            date = request.getParameter("returndate");
            DateTime returndate = new DateTime(date);
            
            concreteDepartureFlights = concreteFlightDAO.findByDate(departureairportname, arrivalairportname, departuredate);
            concreteReturnFlights = concreteFlightDAO.findByDate(arrivalairportname, departureairportname, returndate);
            
            for(int i = 0; i < concreteDepartureFlights.size(); i++){
                virtualFlight = concreteDepartureFlights.get(i).getVirtualFlight();
                String flightcode = virtualFlight.getFlightCode();
                concreteDepartureFlights.get(i).setVirtualFlight(virtualFlightDAO.findByFlightCode(flightcode));
                String depiata = concreteDepartureFlights.get(i).getVirtualFlight().getDepartureAirport().getIata();
                String arriata = concreteDepartureFlights.get(i).getVirtualFlight().getArrivalAirport().getIata();
                concreteDepartureFlights.get(i).getVirtualFlight().setDepartureAirport(airportDAO.findByIata(depiata));
                concreteDepartureFlights.get(i).getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arriata));
            }
            
            for(int i = 0; i < concreteReturnFlights.size(); i++){
                virtualFlight = concreteReturnFlights.get(i).getVirtualFlight();
                String flightcode = virtualFlight.getFlightCode();
                concreteReturnFlights.get(i).setVirtualFlight(virtualFlightDAO.findByFlightCode(flightcode));
                String depiata = concreteReturnFlights.get(i).getVirtualFlight().getDepartureAirport().getIata();
                String arriata = concreteReturnFlights.get(i).getVirtualFlight().getArrivalAirport().getIata();
                concreteReturnFlights.get(i).getVirtualFlight().setDepartureAirport(airportDAO.findByIata(depiata));
                concreteReturnFlights.get(i).getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arriata));
            }
            daoFactory.commitTransaction();
            
            if(concreteDepartureFlights.isEmpty() || concreteReturnFlights.isEmpty())
                request.setAttribute("noflights", "No flights found");
            
            request.setAttribute("departureflights", concreteDepartureFlights);
            request.setAttribute("returnflights", concreteReturnFlights);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
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
    }*/
    
    public static void viewConcreteFlightPerDepartureDate (HttpServletRequest request, HttpServletResponse response){
        
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        LoggedUser loggedUser = null;
        
        List<ConcreteFlight> concreteDepartureFlights;
        
        VirtualFlight virtualFlight;
        DAOFactory daoFactory = null;
        
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            AirportDAO airportDAO = daoFactory.getAirportDAO();
            
            int numeroPosti = Integer.parseInt(request.getParameter("numeroposti"));
            String departureairportname = (String)request.getParameter("departureAirportName");
            String arrivalairportname = (String)request.getParameter("arrivalAirportName");
            
            String date = request.getParameter("departuredate");
            DateTime departuredate = new DateTime(date);
            
            concreteDepartureFlights = concreteFlightDAO.findByDate(departureairportname, arrivalairportname, departuredate, numeroPosti);
            
            for(int i = 0; i < concreteDepartureFlights.size(); i++){
                virtualFlight = concreteDepartureFlights.get(i).getVirtualFlight();
                String flightcode = virtualFlight.getFlightCode();
                concreteDepartureFlights.get(i).setVirtualFlight(virtualFlightDAO.findByFlightCode(flightcode));
                String depiata = concreteDepartureFlights.get(i).getVirtualFlight().getDepartureAirport().getIata();
                String arriata = concreteDepartureFlights.get(i).getVirtualFlight().getArrivalAirport().getIata();
                concreteDepartureFlights.get(i).getVirtualFlight().setDepartureAirport(airportDAO.findByIata(depiata));
                concreteDepartureFlights.get(i).getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arriata));
            }
            
            
            daoFactory.commitTransaction();
            
            if(concreteDepartureFlights.isEmpty())
                request.setAttribute("noflights", "No flights found");
            
            request.setAttribute("numeroposti", numeroPosti);
            request.setAttribute("departureflights", concreteDepartureFlights);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("viewUrl", "flightManager/newView");
            
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
    
    public static void viewConcreteFlightsPerAirportsName(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        LoggedUser loggedUser = null;
        
        List<ConcreteFlight> concreteDepartureFlights = new ArrayList();
        
        VirtualFlight virtualFlight;
        DAOFactory daoFactory = null;
        
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            String departureairportname = (String)request.getParameter("departureAirportName");
            String arrivalairportname = (String)request.getParameter("arrivalAirportName");
            int numeroPosti = Integer.parseInt(request.getParameter("numeroposti"));
            Boolean returnFlight = Boolean.parseBoolean(request.getParameter("return"));
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            AirportDAO airportDAO = daoFactory.getAirportDAO();
            
            
            concreteDepartureFlights = concreteFlightDAO.findByAirportsName(departureairportname, arrivalairportname, DateTime.now(), numeroPosti);
            
            for(int i = 0; i < concreteDepartureFlights.size(); i++){
                virtualFlight = concreteDepartureFlights.get(i).getVirtualFlight();
                String flightcode = virtualFlight.getFlightCode();
                concreteDepartureFlights.get(i).setVirtualFlight(virtualFlightDAO.findByFlightCode(flightcode));
                String depiata = concreteDepartureFlights.get(i).getVirtualFlight().getDepartureAirport().getIata();
                String arriata = concreteDepartureFlights.get(i).getVirtualFlight().getArrivalAirport().getIata();
                concreteDepartureFlights.get(i).getVirtualFlight().setDepartureAirport(airportDAO.findByIata(depiata));
                concreteDepartureFlights.get(i).getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arriata));
            }
            
            request.setAttribute("numeroposti", numeroPosti);
            request.setAttribute("return", returnFlight);
            request.setAttribute("departureflights", concreteDepartureFlights);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("viewUrl", "flightManager/newView");
            
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
    
    public static void viewReturnConcreteFlightsPerAirportsName(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        LoggedUser loggedUser = null;
        
        List<ConcreteFlight> concreteDepartureFlights = new ArrayList();
        
        VirtualFlight virtualFlight;
        DAOFactory daoFactory = null;
        
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            String flightcode = request.getParameter("flightcode");
            DateTime departureDate = new DateTime(request.getParameter("departuredate"));
            DateTime arrivalDate = new DateTime(request.getParameter("arrivaldate"));
            
            String departureAirportName = request.getParameter("departureAirportName");
            String arrivalAirportName = request.getParameter("arrivalAirportName");
            int numeroPosti = Integer.parseInt(request.getParameter("numeroposti"));
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            AirportDAO airportDAO = daoFactory.getAirportDAO();
            
            ConcreteFlight selectedDepartureFlight = concreteFlightDAO.findByFlightCodeAndDate(flightcode, departureDate, arrivalDate);
            concreteDepartureFlights = concreteFlightDAO.findByAirportsName(departureAirportName, arrivalAirportName, departureDate, numeroPosti);
                    
            for(int i = 0; i < concreteDepartureFlights.size(); i++){
                virtualFlight = concreteDepartureFlights.get(i).getVirtualFlight();
                String flightCode = virtualFlight.getFlightCode();
                concreteDepartureFlights.get(i).setVirtualFlight(virtualFlightDAO.findByFlightCode(flightCode));
                String depiata = concreteDepartureFlights.get(i).getVirtualFlight().getDepartureAirport().getIata();
                String arriata = concreteDepartureFlights.get(i).getVirtualFlight().getArrivalAirport().getIata();
                concreteDepartureFlights.get(i).getVirtualFlight().setDepartureAirport(airportDAO.findByIata(depiata));
                concreteDepartureFlights.get(i).getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arriata));
            }
            
            request.setAttribute("numeroposti", numeroPosti);
            request.setAttribute("return", false);
            request.setAttribute("selectedDepartureFlight", selectedDepartureFlight);
            request.setAttribute("departureflights", concreteDepartureFlights);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("viewUrl", "flightManager/newView");
            
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
            
    public static void viewConcreteFlightsPerMonth(HttpServletRequest request, HttpServletResponse response){
       Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        LoggedUser loggedUser = null;
        
        List<ConcreteFlight> concreteDepartureFlights = new ArrayList();
        
        VirtualFlight virtualFlight;
        DAOFactory daoFactory = null;
        
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            String flightCode = request.getParameter("flightcode");
            String month = request.getParameter("month");
            DateTime minDepartureDate = new DateTime(request.getParameter("firstDepartureDate"));
            Boolean returnFlight = Boolean.parseBoolean(request.getParameter("return"));
            int numeroPosti = Integer.parseInt(request.getParameter("numeroposti"));
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            AirportDAO airportDAO = daoFactory.getAirportDAO();
            
            concreteDepartureFlights = concreteFlightDAO.findByMonth(flightCode, month, minDepartureDate, numeroPosti);
                    
            for(int i = 0; i < concreteDepartureFlights.size(); i++){
                virtualFlight = concreteDepartureFlights.get(i).getVirtualFlight();
                String flightcode = virtualFlight.getFlightCode();
                concreteDepartureFlights.get(i).setVirtualFlight(virtualFlightDAO.findByFlightCode(flightcode));
                String depiata = concreteDepartureFlights.get(i).getVirtualFlight().getDepartureAirport().getIata();
                String arriata = concreteDepartureFlights.get(i).getVirtualFlight().getArrivalAirport().getIata();
                concreteDepartureFlights.get(i).getVirtualFlight().setDepartureAirport(airportDAO.findByIata(depiata));
                concreteDepartureFlights.get(i).getVirtualFlight().setArrivalAirport(airportDAO.findByIata(arriata));
            }
            
            request.setAttribute("numeroposti", numeroPosti);
            request.setAttribute("return", returnFlight);
            request.setAttribute("departureflights", concreteDepartureFlights);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("viewUrl", "flightManager/newView");
            
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
    
    public static void createConcreteFlight(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        ConcreteFlight concreteFlight = null;
        String applicationMessage;
        
        LoggedAdmin loggedAdmin = null;
        
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            VirtualFlight virtualFlight = new VirtualFlight();
            String flightCode = request.getParameter("flightcode");
            virtualFlight.setFlightCode(flightCode);
            
            Admin admin = new Admin();
            admin.setId(new Long(request.getParameter("adminId")));
            
            String departuredate = request.getParameter("departureDate");
            String departureTime = request.getParameter("departureTime");
            DateTime departureDate = getDateTime(departuredate, departureTime);
            
            String arrivaldate = request.getParameter("arrivalDate");
            String arrivalTime = request.getParameter("arrivalTime");
            DateTime arrivalDate = getDateTime(arrivaldate, arrivalTime);
            
            int seatFirst = Integer.parseInt(request.getParameter("seatFirst"));
            int seatSecond = Integer.parseInt(request.getParameter("seatSecond"));
            
            float multiplier = Float.parseFloat(request.getParameter("multiplier"));
            boolean push = Boolean.parseBoolean(request.getParameter("push"));
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            
            try{
                concreteFlight = concreteFlightDAO.insert(departureDate, arrivalDate, multiplier, virtualFlight, admin, push, seatFirst, seatSecond);
            }catch(DuplicatedObjectException e){
                logger.log(Level.INFO, "Tentativo di inserimento di un volo già esistente");
                applicationMessage = "Volo già esistente";
                
                request.setAttribute("applicationmessage", applicationMessage);
            }
            
            commonView(daoFactory, request, flightCode);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("flightcode", flightCode);
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("viewUrl", "flightManager/concreteFlightsFactory");
        
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
    
    public static void modifyConcreteFlight(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        ConcreteFlight concreteFlight = new ConcreteFlight();
        
        LoggedAdmin loggedAdmin = null;
        
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            VirtualFlight virtualFlight = new VirtualFlight();
            String flightCode = request.getParameter("flightcode");
            virtualFlight.setFlightCode(flightCode);
            concreteFlight.setVirtualFlight(virtualFlight);
            
            Admin admin = new Admin();
            admin.setId(new Long(request.getParameter("adminId")));
            concreteFlight.setAdmin(admin);
            
            String departuredate = request.getParameter("departureDate");
            String departureTime = request.getParameter("departureTime");
            concreteFlight.setDate(getDateTime(departuredate, departureTime));
            
            String arrivaldate = request.getParameter("arrivalDate");
            String arrivalTime = request.getParameter("arrivalTime");
            concreteFlight.setArrivalDate(getDateTime(arrivaldate, arrivalTime));
            
            concreteFlight.setSeatFirst(Integer.parseInt(request.getParameter("seatFirst")));
            concreteFlight.setSeatSecond(Integer.parseInt(request.getParameter("seatSecond")));
            
            concreteFlight.setMultiplier(Float.parseFloat(request.getParameter("multiplier")));
            concreteFlight.setPush(Boolean.parseBoolean(request.getParameter("push")));
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            
            try{
                concreteFlightDAO.update(concreteFlight);
            }catch(DuplicatedObjectException e){
                logger.log(Level.INFO, "Tentativo di inserimento di un volo già esistente");
            }
            
            commonView(daoFactory, request, flightCode);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("flightcode", flightCode);
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("viewUrl", "flightManager/concreteFlightsFactory");
        
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
    
    public static void deleteConcreteFlight(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        ConcreteFlight concreteFlight;
        
        LoggedAdmin loggedAdmin = null;
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSessionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            String flightCode = (String)request.getParameter("flightcode");
            DateTime departureDate = new DateTime(request.getParameter("departuredate"));
            DateTime arrivalDate = new DateTime(request.getParameter("arrivaldate"));
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            AdminDAO adminDAO = daoFactory.getAdminDAO();
            
            concreteFlight = concreteFlightDAO.findByFlightCodeAndDate(flightCode, departureDate, arrivalDate);
            concreteFlight.setVirtualFlight(virtualFlightDAO.findByFlightCode(flightCode));
            Long adminId = concreteFlight.getAdmin().getId();
            concreteFlight.setAdmin(adminDAO.findAdminById(adminId));
            
            concreteFlightDAO.delete(concreteFlight);
            
            commonView(daoFactory, request, flightCode);
            
            daoFactory.commitTransaction();
            
            request.setAttribute("flightcode", flightCode);
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("viewUrl", "flightManager/concreteFlightsFactory");
            
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
    
    private static void commonView(DAOFactory daoFactory, HttpServletRequest request, String flightCode){
        
        List<ConcreteFlight> concreteFlights;
        
        ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
        VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
        //AdminDAO adminDAO = daoFactory.getAdminDAO();
        
        concreteFlights = concreteFlightDAO.findConcreteFlightsByFlightCode(flightCode);
        
        for(int i = 0; i < concreteFlights.size(); i++){
            //Long adminId = concreteFlights.get(i).getAdmin().getId();
            concreteFlights.get(i).setVirtualFlight(virtualFlightDAO.findByFlightCode(flightCode));
            //concreteFlights.get(i).setAdmin(adminDAO.findAdminById(adminId));
        }
        
        request.setAttribute("concreteflights", concreteFlights);
    }
    
    private static DateTime getDateTime(String date, String time) {
        
        DateTime dt;
        String [] dateParts = date.split("-");
        String [] timeParts = time.split(":");
        
        int a = Integer.parseInt(dateParts[0]);
        int m = Integer.parseInt(dateParts[1]);
        int g = Integer.parseInt(dateParts[2]);
        
        int h = Integer.parseInt(timeParts[0]);
        int min = Integer.parseInt(timeParts[1]);
        
        dt = new DateTime(a,m,g,h,min);
        
        return dt;
    }
}
