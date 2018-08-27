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
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedUser;

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
}
