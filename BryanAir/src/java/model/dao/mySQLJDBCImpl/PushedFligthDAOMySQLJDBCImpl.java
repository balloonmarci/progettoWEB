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
import model.dao.PushedFlightDAO;
import model.mo.PushedFlight;
import org.joda.time.DateTime;

/**
 *
 * @author Marcello
 */
public class PushedFligthDAOMySQLJDBCImpl implements PushedFlightDAO {
    Connection conn;
    
    public PushedFligthDAOMySQLJDBCImpl(Connection conn) {
        this.conn=conn;
    }

    @Override
    public List<PushedFlight> getPushedFlights() {
        PreparedStatement ps;
      ArrayList<PushedFlight> pushedFlights = new ArrayList<PushedFlight>();

      try {
          String sq1
                  = " SELECT V.flightcode, A1.airportname AS departure, A2.airportname AS arrival, "
                  + " A2.city as arrivalcity, "
                  + " (V.pricesecond*C.multiplier) AS finalprice, "
                  + " C.departuredate, C.arrivaldate, "
                  + " (V.pricesecond - V.pricesecond*C.multiplier) AS difference "
                  + " FROM concreteflight AS C JOIN virtualflight AS V "
                  + " ON C.flightcode=V.flightcode "
                  + " JOIN airport AS A1 "
                  + " ON A1.iata = V.departureairport "
                  + " JOIN airport AS A2 "
                  + " ON A2.iata = V.arrivalairport "
                  + " WHERE C.PUSH = 1 "
                  + " AND C.deleted = 0 "
                  + " ORDER BY (V.pricesecond*C.multiplier); ";

          ps = conn.prepareStatement(sq1);

          ResultSet resultSet = ps.executeQuery();

          while(resultSet.next()){

              pushedFlights.add(read(resultSet));
          }

          resultSet.close();
          ps.close();


      }catch(SQLException e){
          throw new RuntimeException(e);
      }
      return pushedFlights;
    }  
    
    PushedFlight read(ResultSet rs){
        PushedFlight pushedFlight = new PushedFlight();
        
        try{
            pushedFlight.setFlightcode(rs.getString("flightcode"));
        }catch (SQLException sqle){
        }
        
        try{
            pushedFlight.setDepartureairport(rs.getString("departure"));
        }catch (SQLException sqle){
        }
        
        try{
            pushedFlight.setArrivalairport(rs.getString("arrival"));
        }catch (SQLException sqle){
        }
        
        try{
            pushedFlight.setArrivalcity(rs.getString("arrivalcity"));
        }catch (SQLException sqle){
        }
        
        try{
            pushedFlight.setFinalprice(rs.getFloat("finalprice"));
        }catch (SQLException sqle){
        }
        
        try{
            pushedFlight.setDeparturedate(new DateTime(rs.getTimestamp("arrivaldate")));
        }catch (SQLException sqle){
        }
        
        try{
            pushedFlight.setArrivaldate(new DateTime(rs.getTimestamp("arrivaldate")));
        }catch (SQLException sqle){
        }
        
        try{
            pushedFlight.setDifference(rs.getFloat("difference"));
        }catch (SQLException sqle){
        }
        
        return pushedFlight;
    }
    
}
