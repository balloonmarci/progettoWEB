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
import model.dao.WishlistDAO;
import model.mo.Admin;
import model.mo.ConcreteFlight;
import model.mo.VirtualFlight;
import model.mo.Wishlist;
import model.session.mo.LoggedUser;
import org.joda.time.DateTime;

/**
 *
 * @author Marcello
 */
public class WishlistDAOMySQLJDBCImpl implements WishlistDAO {
    Connection conn;

    public WishlistDAOMySQLJDBCImpl(Connection connection) {
        this.conn = connection;
    }

    @Override
    public Wishlist getWishlist(LoggedUser user) {
        PreparedStatement ps;
        Wishlist wishlist = new Wishlist();
        ArrayList<ConcreteFlight> concreteFlights = new ArrayList<ConcreteFlight>();
        
        wishlist.setUser(user);
        
        try{
            
            String sql
              = "SELECT C.flightcode, C.departuredate, C.arrivaldate, C.multiplier, "
              + "C.push, C.adminid, C.seatfirst, C.seatsecond, C.deleted "
              + "FROM wishlist AS W "
              + "JOIN concreteflight AS C "
              + "ON W.flightcode = C.flightcode "
              + "AND W.flightdate = C.departuredate "
              + "JOIN user AS U "
              + "ON W.userid = U.id "
              + "WHERE U.deleted = 0 AND C.deleted = 0 "
              + "AND U.id = ? ;";
            
            ps = conn.prepareStatement(sql);
            ps.setLong(1, user.getUserId() );
            
            ResultSet resultSet = ps.executeQuery();
            
            if(resultSet.next()){
                concreteFlights.add(readConcreteFlight(resultSet));
                                
            }
            resultSet.close();
            ps.close();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return wishlist;
    
    }
    
    ConcreteFlight readConcreteFlight(ResultSet rs){
        ConcreteFlight concreteFlight = new ConcreteFlight();
        Admin admin = new Admin();
        concreteFlight.setAdmin(admin);
        VirtualFlight virtualFlight = new VirtualFlight();
        concreteFlight.setVirtualFlight(virtualFlight);

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
    public void removeFromWishlist(LoggedUser user, ConcreteFlight flight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertFlight(LoggedUser user, ConcreteFlight flight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

