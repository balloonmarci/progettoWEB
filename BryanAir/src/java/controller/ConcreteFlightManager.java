/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import model.mo.ConcreteFlight;
import model.dao.ConcreteFlightDAO;
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;
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
        
        List<ConcreteFlight> concreteFlights;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        
        try{
            
            
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            ConcreteFlightDAO virtualFlightDAO = daoFactory.getConcreteFlightDAO();
            
            String flightCode = (String)request.getParameter("flightCod");
            
            daoFactory.beginTransaction();
            concreteFlights = virtualFlightDAO.findConcreteFlightsByFlightCode(flightCode);
            daoFactory.commitTransaction();
            
            
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("flights", concreteFlights);
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
    
    public static void viewConcreteFlightPerDate (HttpServletRequest request, HttpServletResponse response){
        
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
            }
            
            for(int i = 0; i < concreteReturnFlights.size(); i++){
                virtualFlight = concreteReturnFlights.get(i).getVirtualFlight();
                String flightcode = virtualFlight.getFlightCode();
                concreteReturnFlights.get(i).setVirtualFlight(virtualFlightDAO.findByFlightCode(flightcode));
            }
            
            daoFactory.commitTransaction();
            
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
    }
    
    /*private static Timestamp getTimestampDate(String date) throws ParseException{
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(date);
        Timestamp departuredate = new java.sql.Timestamp(parsedDate.getTime());
        
        return departuredate;
    }*/
}
