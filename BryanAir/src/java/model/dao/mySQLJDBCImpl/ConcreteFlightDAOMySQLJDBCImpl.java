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
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import model.dao.ConcreteFlightDAO;
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
            concreteFlight.setDate(rs.getTimestamp("date"));
        } catch (SQLException sqle) {
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
    public ConcreteFlight insert (Timestamp date, float multiplier, VirtualFlight virtualflight, boolean push, int seatFirst, int seatSecond){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(ConcreteFlight concreteFlight){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(ConcreteFlight concreteFlight){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<ConcreteFlight> findByDate(String departureCity, String arrivalCity,
            Timestamp departuredate, Timestamp returndate){
        PreparedStatement ps;
        ArrayList<ConcreteFlight> concreteFlights = new ArrayList<ConcreteFlight>();
        
        try{
            String sql
                    = "SELECT cf.* "
                    + "FROM virtualflight as vf join concreteflight as cf "
                    + "ON vf.flightcode = cf.flightcode "
                    + "WHERE vf.departureairport = (SELECT iata from airport where city = ?) "
                    + "AND vf.arrivalairport = (SELECT iata from airport where city = ?) "
                    + "AND cf.date BETWEEN ? AND (SELECT DATE_ADD( ?, INTERVAL 1 DAY)) "
                    + "AND cf.deleted = FALSE "
                    + "AND vf.deleted = FALSE";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, departureCity);
            ps.setString(2, arrivalCity);
            ps.setTimestamp(3, departuredate);
            ps.setTimestamp(4, departuredate);
            
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                concreteFlights.add(read(resultSet));
            }
            
            sql
             = "SELECT cf.* "
             + "FROM virtualflight as vf join concreteflight as cf "
             + "ON vf.flightcode = cf.flightcode "
             + "WHERE vf.departureairport = (SELECT iata from airport where city = ?) "
             + "AND vf.arrivalairport = (SELECT iata from airport where city = ?) "
             + "AND cf.date BETWEEN ? AND (SELECT DATE_ADD( ?, INTERVAL 1 DAY)) "
             + "AND cf.deleted = FALSE "
             + "AND vf.deleted = FALSE";
                    
            ps = conn.prepareStatement(sql);
            ps.setString(1, arrivalCity);
            ps.setString(2, departureCity);
            ps.setTimestamp(3, returndate);
            ps.setTimestamp(4, returndate);
            
            resultSet = ps.executeQuery();
            
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
                    + "flightcode = ?";
            
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
}
