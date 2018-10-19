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
import java.util.Random;
import model.dao.PrenotationDAO;
import model.dao.exception.DuplicatedObjectException;
import model.mo.Airport;
import model.mo.ConcreteFlight;
import model.mo.Prenotation;
import model.mo.PrenotationView;
import model.mo.User;
import model.mo.VirtualFlight;
import model.session.mo.LoggedUser;
import org.joda.time.DateTime;

/**
 *
 * @author Marcello
 */
public class PrenotationDAOMySQLJDBCImpl implements PrenotationDAO {
    
    Connection conn;
    public PrenotationDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    
    Prenotation read(ResultSet rs){
        Prenotation prenotation = new Prenotation();
        ConcreteFlight concreteFlight = new ConcreteFlight();
        concreteFlight.setVirtualFlight(new VirtualFlight());
        prenotation.setConcreteFlight(concreteFlight);
        prenotation.setUser(new User());

        try {
            prenotation.setId(rs.getString("code"));
        } catch (SQLException sqle) {
        }

        try{
            prenotation.setClas(rs.getLong("class"));
        }catch(SQLException sqle){
        }

        try {
            prenotation.setPricetotal(rs.getFloat("pricetotal"));
        } catch (SQLException sqle) {
        }

        try{
            prenotation.setPassengerfirstname(rs.getString("passengerfirstname"));
        }catch(SQLException sqle){
        }

        try{
            prenotation.setPassengerlastname(rs.getString("passengerlastname"));
        }catch(SQLException sqle){
        }

        try{
            prenotation.setPassengerTitle(rs.getString("passengertitle"));
        }catch(SQLException sqle){
        }

        try{
            prenotation.getConcreteFlight().getVirtualFlight().setFlightCode(rs.getString("flightcode"));
        }catch(SQLException sqle){
        }
        
        try{
            prenotation.getConcreteFlight().setDate(new DateTime(rs.getTimestamp("departuredate")));
        }catch(SQLException sqle){
        }
        
        try{
            prenotation.getConcreteFlight().setArrivalDate(new DateTime(rs.getTimestamp("arrivaldate")));
        }catch(SQLException sqle){
        }

        try{
            prenotation.setPrenotationDate(new DateTime(rs.getTimestamp("date")));
        }catch(SQLException sqle){
        }
        
        try{
            prenotation.getUser().setUserId(rs.getLong("userid"));
        }catch(SQLException sqle){
        }
        
        try{
            prenotation.setDeleted(rs.getBoolean("deleted"));
        }catch(SQLException sqle){
        }

        return prenotation;
    }
    
    PrenotationView readView(ResultSet rs){
        PrenotationView prenotation = new PrenotationView();
        
        prenotation.setConcreteFlight(new ConcreteFlight());
        prenotation.getConcreteFlight().setVirtualFlight(new VirtualFlight());
        prenotation.getConcreteFlight().getVirtualFlight().setDepartureAirport(new Airport());
        prenotation.getConcreteFlight().getVirtualFlight().setArrivalAirport(new Airport());
        
        
        try {
            prenotation.setClas(rs.getLong("class"));
        } catch (SQLException sqle) {
        }
        
        try {
            prenotation.getConcreteFlight().getVirtualFlight().setFlightCode(rs.getString("flightcode"));
        } catch (SQLException sqle) {
        }
        
        try {
            prenotation.getConcreteFlight().setDate(new DateTime(rs.getTimestamp("departuredate")));
        } catch (SQLException sqle) {
        }
        
        try {
            prenotation.getConcreteFlight().setArrivalDate(new DateTime(rs.getTimestamp("arrivaldate")));
        } catch (SQLException sqle) {
        }
        
        try {
            prenotation.getConcreteFlight().getVirtualFlight().getDepartureAirport().setAirportname(rs.getString("departureairport"));
        } catch (SQLException sqle) {
        }
        
        try {
            prenotation.getConcreteFlight().getVirtualFlight().getArrivalAirport().setAirportname(rs.getString("arrivalairport"));
        } catch (SQLException sqle) {
        }
        try {
            prenotation.setPassengers(rs.getLong("passengers"));
        } catch (SQLException sqle) {
        }
        
        return prenotation;
    }
    
    Prenotation readDetails(ResultSet rs){
        Prenotation prenotation = new Prenotation();
        
        prenotation.setConcreteFlight(new ConcreteFlight());
        prenotation.getConcreteFlight().setVirtualFlight(new VirtualFlight());
        prenotation.getConcreteFlight().getVirtualFlight().setDepartureAirport(new Airport());
        prenotation.getConcreteFlight().getVirtualFlight().setArrivalAirport(new Airport());
        prenotation.setUser(new User());
        
        

        try {
            prenotation.setId(rs.getString("code"));
        } catch (SQLException sqle) {
        }

        try{
            prenotation.setClas(rs.getLong("class"));
        }catch(SQLException sqle){
        }

        try {
            prenotation.setPricetotal(rs.getFloat("pricetotal"));
        } catch (SQLException sqle) {
        }

        try{
            prenotation.setPassengerfirstname(rs.getString("passengerfirstname"));
        }catch(SQLException sqle){
        }

        try{
            prenotation.setPassengerlastname(rs.getString("passengerlastname"));
        }catch(SQLException sqle){
        }
        
        
        try{
            prenotation.setPassengerTitle(rs.getString("passengertitle"));
        }catch(SQLException sqle){
        }
        

        try{
            prenotation.getConcreteFlight().getVirtualFlight().setFlightCode(rs.getString("flightcode"));
        }catch(SQLException sqle){
        }
        
        try{
            prenotation.getConcreteFlight().setDate(new DateTime(rs.getTimestamp("departuredate")));
        }catch(SQLException sqle){
        }
        
        try{
            prenotation.getConcreteFlight().setArrivalDate(new DateTime(rs.getTimestamp("arrivaldate")));
        }catch(SQLException sqle){
        }

        try{
            prenotation.setPrenotationDate(new DateTime(rs.getTimestamp("date")));
        }catch(SQLException sqle){
        }
        
        try{
            prenotation.getUser().setUserId(rs.getLong("userid"));
        }catch(SQLException sqle){
        }
        
        try{
            prenotation.setDeleted(rs.getBoolean("deleted"));
        }catch(SQLException sqle){
        }
        
        try {
            prenotation.getConcreteFlight().getVirtualFlight().getDepartureAirport().setAirportname(rs.getString("departureairport"));
        } catch (SQLException sqle) {
        }
        
        try {
            prenotation.getConcreteFlight().getVirtualFlight().getArrivalAirport().setAirportname(rs.getString("arrivalairport"));
        } catch (SQLException sqle) {
        }
        
        try {
            prenotation.getConcreteFlight().getVirtualFlight().getArrivalAirport().setCity(rs.getString("arrivalcity"));
        } catch (SQLException sqle) {
        }

        return prenotation;
    }

    @Override
    public void newPrenotation(List<Prenotation>prenotations) throws DuplicatedObjectException {
        PreparedStatement ps;
        Random i = new Random();
            
          try{
            String sql;
            Long l;
            boolean exist;
            int k = 0;
            do{
                sql = " SELECT * "
                    + " FROM prenotation "
                    + " WHERE "
                    + " deleted ='0' AND "
                    + " code = ?";
                
                l = new Long(i.nextInt(Integer.MAX_VALUE));
                
                ps = conn.prepareStatement(sql);
                ps.setLong(1, l);
                
                
                ResultSet resultSet = ps.executeQuery();
                exist = resultSet.next();
                
                if(!exist){
                    prenotations.get(k).setId("BAP"+l);
                    k++;
                }
                
            }while(k < prenotations.size());
            
              try {
                      sql
                      = " INSERT INTO prenotation "
                      + "   ( code,"
                      + "     class,"
                      + "     pricetotal,"
                      + "     passengerfirstname,"
                      + "     passengerlastname,"
                      + "     passengertitle,"
                      + "     flightcode,"
                      + "     departuredate,"
                      + "     arrivaldate,"
                      + "     date,"
                      + "     userid,"
                      + "     deleted "
                      + "   ) "
                      + " VALUES (?,?,?,?,?,?,?,?,?,?,?,'0')";

                      ps = conn.prepareStatement(sql);
                      
                      for (Prenotation prenotation : prenotations) {
                        ps.setString(1, prenotation.getId());
                        ps.setLong(2, prenotation.getClas());
                        ps.setFloat(3, prenotation.getPricetotal());
                        ps.setString(4, prenotation.getPassengerfirstname());
                        ps.setString(5, prenotation.getPassengerlastname());
                        ps.setString(6, prenotation.getPassengerTitle());
                        ps.setString(7, prenotation.getConcreteFlight().getVirtualFlight().getFlightCode());
                        ps.setTimestamp(8, new Timestamp(prenotation.getConcreteFlight().getDate().getMillis()));
                        ps.setTimestamp(9, new Timestamp(prenotation.getConcreteFlight().getArrivalDate().getMillis()));
                        ps.setTimestamp(10, new Timestamp(prenotation.getPrenotationDate().getMillis()));
                        ps.setLong(11, prenotation.getUser().getUserId());

                        ps.addBatch();
                      }
                      
                      ps.executeBatch();

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
    }

    @Override
    public void updateName(String passengerfirst, String passengerlast) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PrenotationView> findUserPrenotations(LoggedUser user) {
        PreparedStatement ps;
        List<PrenotationView> prenotations = new ArrayList<PrenotationView>();
        
        try{
            String sq1
                    = " SELECT P.class AS class, P.flightcode AS flightcode, P.departuredate AS departuredate, P.arrivaldate AS arrivaldate, "
                    + " A1.airportname AS departureairport, A2.airportname AS arrivalairport, "
                    + " COUNT(code) AS passengers "
                    + " FROM prenotation AS P "
                    + " JOIN virtualflight AS V ON P.flightcode = V.flightcode "
                    + " JOIN airport AS A1 ON V.departureairport = A1.iata "
                    + " JOIN airport AS A2 ON V.arrivalairport = A2.iata "
                    + " WHERE userid = ? AND NOW() < P.departuredate AND P.deleted = '0' "
                    + " GROUP BY P.flightcode, P.departuredate, P.arrivaldate;";
                     
            
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, user.getUserId());
            ResultSet resultSet = ps.executeQuery();
            
            
            while(resultSet.next()){
              prenotations.add(readView(resultSet));
            }
            
            resultSet.close();
            ps.close();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return prenotations;
    }
    
    @Override
    public List<PrenotationView> findUserPrenotationsCheckIn(LoggedUser user) {
        PreparedStatement ps;
        List<PrenotationView> prenotations = new ArrayList<PrenotationView>();
        
        try{
            String sq1
                    = " SELECT P.class AS class, P.flightcode AS flightcode, P.departuredate AS departuredate, P.arrivaldate AS arrivaldate, "
                    + " A1.airportname AS departureairport, A2.airportname AS arrivalairport, "
                    + " COUNT(code) AS passengers "
                    + " FROM prenotation AS P "
                    + " JOIN virtualflight AS V ON P.flightcode = V.flightcode "
                    + " JOIN airport AS A1 ON V.departureairport = A1.iata "
                    + " JOIN airport AS A2 ON V.arrivalairport = A2.iata "
                    + " WHERE userid = ? AND (NOW() BETWEEN DATE_SUB(P.departuredate, INTERVAL 1 WEEK) AND P.departuredate) "
                    + " AND NOW() < P.departuredate AND P.deleted = '0' "
                    + " AND P.code NOT IN (SELECT prencode FROM checkin) "
                    + " GROUP BY P.flightcode, P.departuredate, P.arrivaldate; ";
                     
            
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, user.getUserId());
            ResultSet resultSet = ps.executeQuery();
            
            
            while(resultSet.next()){
              prenotations.add(readView(resultSet));
            }
            
            resultSet.close();
            ps.close();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return prenotations;
    }
    
    @Override
    public List<Prenotation> findPrenotationDetail(LoggedUser user, String flightcode, DateTime departuredate, DateTime arrivaldate) {
        PreparedStatement ps;
        List<Prenotation> prenotations = new ArrayList<Prenotation>();
        
        try{
            String sq1
                    = " SELECT P.code, P.class, P.pricetotal,  "
                    + " P.passengerfirstname, P.passengerlastname, "
                    + " P.passengertitle, P.flightcode, P.departuredate, P.arrivaldate,  "
                    + " P.date, A1.airportname AS departureairport, A2.airportname AS arrivalairport, A2.city AS arrivalcity "
                    + " FROM prenotation AS P "
                    + " JOIN virtualflight AS V ON P.flightcode=V.flightcode "
                    + " JOIN airport AS A1 ON V.departureairport=A1.iata "
                    + " JOIN airport AS A2 ON V.arrivalairport=A2.iata "
                    + " WHERE userid = ? AND P.flightcode = ? "
                    + " AND departuredate = ? AND arrivaldate = ?;";
            
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, user.getUserId());
            ps.setString(2, flightcode);
            ps.setTimestamp(3, new Timestamp(departuredate.getMillis()));
            ps.setTimestamp(4, new Timestamp (arrivaldate.getMillis()));
            ResultSet resultSet = ps.executeQuery();
            
            
            while(resultSet.next()){
              prenotations.add(readDetails(resultSet));
            }
            
            resultSet.close();
            ps.close();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return prenotations;
    }
    
    
    
}
