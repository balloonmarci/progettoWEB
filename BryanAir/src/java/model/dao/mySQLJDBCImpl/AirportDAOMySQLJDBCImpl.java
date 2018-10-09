/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mySQLJDBCImpl;

/**
 *
 * @author Marcello
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.mo.Airport;
import model.dao.AirportDAO;

public class AirportDAOMySQLJDBCImpl implements AirportDAO{
    Connection conn;
    
    public AirportDAOMySQLJDBCImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public Airport insert(String iata, String airportname, String city, String country) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Airport airport) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Airport airport) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Airport findByIata(String iata) {
        
        PreparedStatement ps;
        Airport airport = null;
        
        try {
            
            String sq1
                    = " SELECT * "
                    + " FROM airport "
                    + " WHERE "
                    + " iata = ? ";
            
            ps = conn.prepareStatement(sq1);
            ps.setString(1, iata);
            
            ResultSet resultSet = ps.executeQuery();
            
            if(resultSet.next()){
                airport = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return airport;
    }
    
    @Override
    public List<Airport> findAllAirport()
    {
       PreparedStatement ps;
       List<Airport> airports = new ArrayList();
        
        try {
            
            String sq1
                    = " SELECT * "
                    + " FROM airport ";
            
            ps = conn.prepareStatement(sq1);
            
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                airports.add(read(resultSet));
            }
            resultSet.close();
            ps.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return airports;
    }
    
    @Override
    public Airport[] findByCity(String city) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Airport read(ResultSet rs){
        
        Airport airport = new Airport();
        
        try {
            airport.setIata(rs.getString("iata"));
        }catch(SQLException sqle){
        
        }
        
        try {
            airport.setAirportname(rs.getString("airportname"));
        }catch(SQLException sqle){
        
        }
        
        try {
            airport.setCity(rs.getString("city"));
        }catch(SQLException sqle){
        
        }
        
        try {
            airport.setCountry(rs.getString("country"));
        }catch(SQLException sqle){
        
        }
        
        try {
            airport.setDeleted(rs.getBoolean("deleted"));
        }catch(SQLException sqle){
        
        }
        
        return airport;
    }
    
    
    
}
