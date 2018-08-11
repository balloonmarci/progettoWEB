/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

/**
 *
 * @author Marcello
 */
import java.util.List;
import model.mo.VirtualFlight;
import model.mo.Airport;
public interface VirtualFlightDAO {
    
    public VirtualFlight insert (
        String flightCode,
        float priceFirst,
        float priceSecond,
        Airport departureAirport,
        Airport arrivalAirport);
    
    public void update(VirtualFlight virtualFlight);
    public void delete(VirtualFlight virtualFlight);
    public VirtualFlight findByFlightCode(String flightCode); 
    public List<VirtualFlight> findAllVirtualFlights();
    
}
