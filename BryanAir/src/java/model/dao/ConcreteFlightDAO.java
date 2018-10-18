/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;
import java.util.List;
import org.joda.time.DateTime;
import model.mo.VirtualFlight;
import model.mo.ConcreteFlight;
import model.mo.Admin;
import model.dao.exception.DuplicatedObjectException;
import model.mo.Airport;
/**
 *
 * @author Filippo
 */
public interface ConcreteFlightDAO {

  public ConcreteFlight insert (
          DateTime departureDate,
          DateTime arrivalDate,
          float multiplier,
          VirtualFlight virtualflight,
          Admin admin,
          boolean push,
          int seatFirst,
          int seatSecond) throws DuplicatedObjectException;

    public void update(ConcreteFlight concreteFlight) throws DuplicatedObjectException;
    public void delete(ConcreteFlight concreteFlight);
    public List<ConcreteFlight> findByDate(String departureAirportName, String arrivalAirportName, DateTime flightdate, int numeroPosti); 
    public List<ConcreteFlight> findConcreteFlightsByFlightCode(String flightCode);
    public ConcreteFlight findByFlightCodeAndDate(String flightCode, DateTime departureDate, DateTime arrivalDate);
    public List<ConcreteFlight> findByMonth(String flightcode, String month, DateTime minDepartureDate, int numeroPosti);
    public List<ConcreteFlight> findByAirportsName(String departureAirportName, String arrivalAirportName, DateTime minDepartureDate, int numeroPosti);
}
