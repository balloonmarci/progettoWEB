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
import java.util.List;
import java.util.Random;
import model.dao.PrenotationDAO;
import model.dao.exception.DuplicatedObjectException;
import model.mo.ConcreteFlight;
import model.mo.Prenotation;
import model.mo.User;
import model.mo.VirtualFlight;
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
            prenotation.setSesso(rs.getString("passengergender"));
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
                
                l = new Long(i.nextInt(9999999));
                
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
                      + "     passengergender,"
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
                        ps.setString(6, prenotation.getSesso());
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
    public List<Prenotation> findUserPrenotations(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
