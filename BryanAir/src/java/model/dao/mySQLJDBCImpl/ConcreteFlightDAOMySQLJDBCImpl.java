/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

import model.dao.ConcreteFlightDAO;
import model.dao.exception.DuplicatedObjectException;
import model.mo.VirtualFlight;
import model.mo.ConcreteFlight;
import model.mo.Admin;
import model.mo.Airport;
/**
 *
 * @author Filippo
 */
public class ConcreteFlightDAOMySQLJDBCImpl implements ConcreteFlightDAO{
    Connection conn;
    
    public ConcreteFlightDAOMySQLJDBCImpl(Connection conn){
        this.conn = conn;
    }
    
    ConcreteFlight read(ResultSet rs){
        ConcreteFlight concreteFlight = new ConcreteFlight();
        VirtualFlight virtualFlight = new VirtualFlight();
        Admin admin = new Admin();
        concreteFlight.setVirtualFlight(virtualFlight);
        concreteFlight.setAdmin(admin);
        
        try {
            concreteFlight.setDate(new DateTime(rs.getTimestamp("departuredate")));
        } catch (SQLException sqle) {
        }
        
        try{
            concreteFlight.setArrivalDate(new DateTime(rs.getTimestamp("arrivaldate")));
        }catch(SQLException sqle){
        }
        
        try {
            concreteFlight.setMultiplier(rs.getFloat("multiplier"));
        } catch (SQLException sqle) {
        }
        
        try{
            concreteFlight.setPush(rs.getBoolean("push"));
        }catch(SQLException sqle){    
        }
        
        try{
            concreteFlight.getVirtualFlight().setFlightCode(rs.getString("flightcode"));
        }catch(SQLException sqle){
        }
        
        try{
            concreteFlight.getAdmin().setId(rs.getLong("adminid"));
        }catch(SQLException sqle){
        }
        
        try{
            concreteFlight.setSeatFirst(rs.getInt("seatfirst"));
        }catch(SQLException sqle){
        }
        
        try{
            concreteFlight.setSeatSecond(rs.getInt("seatsecond"));
        }catch(SQLException sqle){
        }
        
        try{
            concreteFlight.setDeleted(rs.getBoolean("deleted"));
        }catch(SQLException sqle){
        }
        
        return concreteFlight;
    }
    
    @Override
    public ConcreteFlight insert (DateTime departureDate, DateTime arrivalDate, float multiplier, VirtualFlight virtualflight, 
                                  Admin admin, boolean push, int seatFirst, int seatSecond)
     throws DuplicatedObjectException {
        PreparedStatement ps;
        
        ConcreteFlight concreteFlight = new ConcreteFlight();
        
        concreteFlight.setVirtualFlight(virtualflight);
        concreteFlight.setAdmin(admin);
        concreteFlight.setDate(departureDate);
        concreteFlight.setArrivalDate(arrivalDate);
        concreteFlight.setMultiplier(multiplier);
        concreteFlight.setPush(push);
        concreteFlight.setSeatFirst(seatFirst);
        concreteFlight.setSeatSecond(seatSecond);
        try{
            try {
                    String sql
                    = " INSERT INTO concreteflight "
                    + "   ( departuredate,"
                    + "     multiplier,"
                    + "     push,"
                    + "     flightcode,"
                    + "     adminid,"   
                    + "     seatfirst,"      
                    + "     seatsecond,"
                    + "     deleted,"
                    + "     arrivaldate "
                    + "   ) "
                    + " VALUES (?,?,?,?,?,?,?,'0',?)";
            
                    ps = conn.prepareStatement(sql);
            
                    ps.setTimestamp(  1, new Timestamp(concreteFlight.getDate().getMillis()));
                    ps.setFloat(2, concreteFlight.getMultiplier());
                    ps.setInt(3, (concreteFlight.getPush())? 1:0);
                    ps.setString(4, concreteFlight.getVirtualFlight().getFlightCode());
                    ps.setLong(5, concreteFlight.getAdmin().getId());
                    ps.setInt(6, concreteFlight.getSeatFirst());
                    ps.setInt(7, concreteFlight.getSeatSecond());
                    ps.setTimestamp(8, new Timestamp(concreteFlight.getArrivalDate().getMillis()));
            
                    ps.executeUpdate();
                    
                    ps.close();
                }
                catch(SQLIntegrityConstraintViolationException e){
                    throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un volo già esistente.");
                }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        
        return concreteFlight;
    }
    
    @Override
    public void update(ConcreteFlight concreteFlight) throws DuplicatedObjectException {
        PreparedStatement ps;
        
        try {
            try{
                String sql 
                = "UPDATE concreteflight "
                + "SET "
                + "multiplier = ?, "
                + "push = ?, "
                + "adminid = ?, "
                + "seatfirst = ?, "
                + "seatsecond = ? "
                + "WHERE "
                + "departuredate = ? AND "
                + "flightcode = ? AND "
                + "arrivaldate = ?";

                ps = conn.prepareStatement(sql);
            
                ps.setFloat(1, concreteFlight.getMultiplier());
                ps.setInt(2, (concreteFlight.getPush())? 1:0);
                ps.setLong(3, concreteFlight.getAdmin().getId());
                ps.setInt(4, concreteFlight.getSeatFirst());
                ps.setInt(5, concreteFlight.getSeatSecond());
                ps.setTimestamp(6, new Timestamp(concreteFlight.getDate().getMillis()));
                ps.setString(7, concreteFlight.getVirtualFlight().getFlightCode());
                ps.setTimestamp(8, new Timestamp(concreteFlight.getArrivalDate().getMillis()));
               
                ps.executeUpdate();
                    
                ps.close();
            }
            catch(SQLIntegrityConstraintViolationException e){
                throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un utente già esistente.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void delete(ConcreteFlight concreteFlight){
        PreparedStatement ps;

        try {

            String sql
                    = "UPDATE concreteflight SET deleted = '1' "
                    + "WHERE "
                    + "`concreteflight`.`departuredate` = ? AND "
                    + "`concreteflight`.`flightcode` = ? AND "
                    + "`concreteflight`.`arrivaldate` = ? ;";
           
            ps = conn.prepareStatement(sql);
            
            ps.setTimestamp(1, new Timestamp(concreteFlight.getDate().getMillis()));
            ps.setString(2, concreteFlight.getVirtualFlight().getFlightCode());
            ps.setTimestamp(3, new Timestamp(concreteFlight.getArrivalDate().getMillis()));
            
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public List<ConcreteFlight> findByDate(String departureAirport, String arrivalAirport,
            DateTime flightDate){
        PreparedStatement ps;
        ArrayList<ConcreteFlight> concreteFlights = new ArrayList<ConcreteFlight>();
        
        try{
            String sql
                    = "SELECT cf.* "
                    + "FROM virtualflight as vf join concreteflight as cf "
                    + "ON vf.flightcode = cf.flightcode "
                    + "WHERE vf.departureairport = (SELECT iata from airport where airportname = ?) "
                    + "AND vf.arrivalairport = (SELECT iata from airport where airportname = ?) "
                    + "AND cf.departuredate BETWEEN ? AND (SELECT DATE_ADD( ?, INTERVAL 1 DAY)) "
                    + "AND cf.deleted = FALSE "
                    + "AND vf.deleted = FALSE";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, departureAirport);
            ps.setString(2, arrivalAirport);
            ps.setTimestamp(3, new Timestamp(flightDate.getMillis()));
            ps.setTimestamp(4, new Timestamp(flightDate.getMillis()));
            
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                concreteFlights.add(read(resultSet));
            }
            
            resultSet.close();
            ps.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return concreteFlights;
    }
    
    @Override
    public List<ConcreteFlight> findConcreteFlightsByFlightCode(String flightCode){
        PreparedStatement ps;
        ArrayList<ConcreteFlight> concreteFlights = new ArrayList<ConcreteFlight>();
        ConcreteFlight concreteFlight = new ConcreteFlight();
        
        try {
            String sq1
                    = "SELECT * "
                    + "FROM concreteflight "
                    + "WHERE "
                    + "flightcode = ? AND "
                    + "deleted = '0' "
                    + "ORDER BY departuredate DESC";
            
            ps = conn.prepareStatement(sq1);
            ps.setString(1, flightCode);
            
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                
                concreteFlights.add(read(resultSet));
            }
            
            resultSet.close();
            ps.close();
        
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return concreteFlights;
    }
    
    @Override
    public ConcreteFlight findByFlightCodeAndDate(String flightCode, DateTime departureDate, DateTime arrivalDate){
        PreparedStatement ps;
        ConcreteFlight concreteFlight = new ConcreteFlight();
        
        try {
            String sq1
                    = "SELECT * "
                    + "FROM concreteflight "
                    + "WHERE "
                    + "flightcode = ? AND "
                    + "departuredate = ? AND "
                    + "arrivaldate = ? AND "
                    + "deleted = '0' ";
            
            ps = conn.prepareStatement(sq1);
            ps.setString(1, flightCode);
            ps.setTimestamp(2, new Timestamp(departureDate.getMillis()));
            ps.setTimestamp(3, new Timestamp(arrivalDate.getMillis()));
            
            ResultSet resultSet = ps.executeQuery();
            
            if(resultSet.next()){
                
                concreteFlight = read(resultSet);
            }
            
            resultSet.close();
            ps.close();
        
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return concreteFlight;
    }
}
