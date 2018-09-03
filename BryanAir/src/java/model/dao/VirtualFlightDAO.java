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
import model.dao.exception.DuplicatedObjectException;
import model.mo.VirtualFlight;
import model.mo.Airport;
public interface VirtualFlightDAO {
    
    public VirtualFlight insert (
        String flightCode,
        Float priceFirst,
        Float priceSecond,
        Airport departureAirport,
        Airport arrivalAirport) throws DuplicatedObjectException;
    
    public void update(VirtualFlight virtualFlight) throws DuplicatedObjectException;
    public void delete(VirtualFlight virtualFlight);
    public VirtualFlight findByFlightCode(String flightCode); 
    public List<VirtualFlight> findAllVirtualFlights();
    
}
