package controller;

/**
 *
 * @author Marcello
 */


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

public class FlightManager {
    private FlightManager(){
        
    }
    
    public static void view (HttpServletRequest request, HttpServletResponse response){
        
        Logger logger = LogService.getApplicationLogger();
        
        List<VirtualFlight> virtualFlights;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        
        try{
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            
            virtualFlights = virtualFlightDAO.findAllVirtualFlights();
            
            daoFactory.commitTransaction();
            
            //request.setAttribute("flights", virtualFlights);
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
    
    public static void viewConcreteFlight (HttpServletRequest request, HttpServletResponse response){
        
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
        
        List<ConcreteFlight> concreteFlights;
        VirtualFlight virtualFlight;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        
        try{
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ConcreteFlightDAO concreteFlightDAO = daoFactory.getConcreteFlightDAO();
            VirtualFlightDAO virtualFlightDAO = daoFactory.getVirtualFlightDAO();
            
            String departurecity = (String)request.getParameter("departurecity");
            String arrivalcity = (String)request.getParameter("arrivalcity");
            
            String date = request.getParameter("departuredate");
            Timestamp departuredate = getTimestampDate(date);
            
            date = request.getParameter("returndate");
            Timestamp returndate = getTimestampDate(date);
            
            concreteFlights = concreteFlightDAO.findByDate(departurecity, arrivalcity, departuredate, returndate);
            
            for(int i = 0; i < concreteFlights.size(); i++){
                virtualFlight = concreteFlights.get(i).getVirtualFlight();
                String flightcode = virtualFlight.getFlightCode();
                concreteFlights.get(i).setVirtualFlight(virtualFlightDAO.findByFlightCode(flightcode));
            }
            
            daoFactory.commitTransaction();
            
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
    
    private static Timestamp getTimestampDate(String date) throws ParseException{
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(date);
        Timestamp departuredate = new java.sql.Timestamp(parsedDate.getTime());
        
        return departuredate;
    }
}
