package controller;

/**
 *
 * @author Marcello
 */


import java.util.ArrayList;
import java.util.List;
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
