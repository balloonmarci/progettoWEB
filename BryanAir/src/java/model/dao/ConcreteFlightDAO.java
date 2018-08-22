/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import model.mo.VirtualFlight;
import model.mo.ConcreteFlight;

/**
 *
 * @author Filippo
 */
public interface ConcreteFlightDAO {
    
    public ConcreteFlight insert (
        Timestamp date,
        float multiplier,
        VirtualFlight virtualflight,
        boolean push,
        int seatFirst,
        int seatSecond);
    
    public void update(ConcreteFlight concreteFlight);
    public void delete(ConcreteFlight concreteFlight);
    public List<ConcreteFlight> findByDate(String departureCity, String arrivalCity, Timestamp departuredate, Timestamp returndate); 
    public List<ConcreteFlight> findConcreteFlightsByFlightCode(String flightCode);
}
