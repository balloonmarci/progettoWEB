package model.dao.mySQLJDBCImpl;

/**
 *
 * @author Marcello
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.mo.VirtualFlight;
import model.dao.VirtualFlightDAO;
import model.dao.exception.DuplicatedObjectException;

import model.mo.Airport;


public class VirtualFlightDAOMySQLJDBCImpl implements VirtualFlightDAO{
    Connection conn;
    
    public VirtualFlightDAOMySQLJDBCImpl(Connection conn){
        this.conn = conn;
    }
    
    VirtualFlight read(ResultSet rs){
        VirtualFlight virtualFlight = new VirtualFlight();
        
        try {
            virtualFlight.setFlightCode(rs.getString("flightcode"));
        } catch (SQLException sqle) {
        }
        
        try {
            virtualFlight.setPriceFirst(rs.getFloat("pricefirst"));
        } catch (SQLException sqle) {
        }
        
        try{
            virtualFlight.setPriceSecond(rs.getFloat("pricesecond"));
        }catch(SQLException sqle){    
        }
        
        try{
            virtualFlight.setDepartureAirport(getAirport(rs, "departure"));
        }catch(SQLException sqle){
        }
        
        try{
            virtualFlight.setArrivalAirport(getAirport(rs, "arrival"));
        }catch(SQLException sqle){
        }
        
        try{
            virtualFlight.setDeleted(rs.getBoolean("deleted"));
        }catch(SQLException sqle){
        }
        
        return virtualFlight;
    }
    
    private Airport getAirport(ResultSet rs, String location) throws SQLException{
        Airport airport = new Airport();
        
        airport.setIata(rs.getString(location+"iata"));
        airport.setAirportname(rs.getString(location+"airportname"));
        airport.setCity(rs.getString(location+"city"));
        airport.setCountry(rs.getString(location+"country"));
        airport.setDeleted(false);
        
        return airport;
    }

    @Override
    public VirtualFlight insert(String flightCode, Float priceFirst, Float priceSecond, Airport departureAirport, Airport arrivalAirport) 
            throws DuplicatedObjectException {
        
        PreparedStatement ps;
        ResultSet resultSet;
        VirtualFlight virtualFlight = new VirtualFlight();
        virtualFlight.setFlightCode(flightCode);
        virtualFlight.setPriceFirst(priceFirst);
        virtualFlight.setPriceSecond(priceSecond);
        virtualFlight.setDepartureAirport(departureAirport);
        virtualFlight.setArrivalAirport(arrivalAirport);
        
        try{
            String sql;
            sql = "SELECT * "
                + "FROM virtualflight "
                + "WHERE departureairport = ? AND "
                + "arrivalairport = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, virtualFlight.getDepartureAirport().getIata());
            ps.setString(2, virtualFlight.getArrivalAirport().getIata());
            
            resultSet = ps.executeQuery();
            
            if(resultSet.next())
                throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un volo già esistente.");
    
            try {
                sql
                = " INSERT INTO virtualflight "
                + "   ( flightcode,"
                + "     pricefirst,"
                + "     pricesecond,"
                + "     departureairport,"
                + "     arrivalairport,"        
                + "     deleted "
                + "   ) "
                + " VALUES (?,?,?,?,?,'0')";
            
                ps = conn.prepareStatement(sql);
            
                ps.setString(1, virtualFlight.getFlightCode());
                ps.setFloat(2, virtualFlight.getPriceFirst());
                ps.setFloat(3, virtualFlight.getPriceSecond());
                ps.setString(4, virtualFlight.getDepartureAirport().getIata());
                ps.setString(5, virtualFlight.getArrivalAirport().getIata());
            
                ps.executeUpdate();
            }
            catch(SQLIntegrityConstraintViolationException e){
                throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un volo già esistente.");
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        
        return virtualFlight;
        
    }

    @Override
    public void update(VirtualFlight virtualFlight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(VirtualFlight virtualFlight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VirtualFlight findByFlightCode(String flightCode) {
       PreparedStatement ps;
       VirtualFlight virtualFlight = new VirtualFlight();
       
       try {
            String sq1
                    = "SELECT flightcode, pricefirst, pricesecond, dep.iata AS departureiata, dep.airportname AS departureairportname, dep.city AS departurecity, dep.country AS departurecountry, "
                    + " arr.iata AS arrivaliata, arr.airportname AS arrivalairportname, arr.city AS arrivalcity, arr.country AS arrivalcountry, v.deleted "
                    + " FROM virtualflight AS v JOIN airport as dep ON v.departureairport = dep.iata "
                    + " JOIN airport as arr ON v.arrivalairport = arr.iata "
                    + " WHERE flightcode = ?";
            
            ps = conn.prepareStatement(sq1);
            ps.setString(1, flightCode);
            
            ResultSet resultSet = ps.executeQuery();
            
            if(resultSet.next())
                virtualFlight = read(resultSet);
            
            resultSet.close();
            ps.close();
            
       }catch(SQLException e){
            throw new RuntimeException(e);
       }
       
       return virtualFlight;
    }
    
    public List<VirtualFlight> findAllVirtualFlights() {
        PreparedStatement ps;
        ArrayList<VirtualFlight> virtualFlights = new ArrayList<VirtualFlight>();
        
        try {
            String sq1
                    = "SELECT flightcode, pricefirst, pricesecond, dep.iata AS departureiata, dep.airportname AS departureairportname, dep.city AS departurecity, dep.country AS departurecountry, "
                    + " arr.iata AS arrivaliata, arr.airportname AS arrivalairportname, arr.city AS arrivalcity, arr.country AS arrivalcountry, v.deleted "
                    + " FROM virtualflight AS v JOIN airport as dep ON v.departureairport = dep.iata "
                    + " JOIN airport as arr ON v.arrivalairport = arr.iata "
                    + " WHERE dep.deleted <> true AND arr.deleted <> true;";
            
            ps = conn.prepareStatement(sq1);
            
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                
                virtualFlights.add(read(resultSet));
                
            }
            
            resultSet.close();
            ps.close();
        
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return virtualFlights;
    }
    
}
