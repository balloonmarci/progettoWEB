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
import java.util.Random;
import model.dao.CheckInDAO;

/**
 *
 * @author Marcello
 */
public class CheckInDAOMySQLJDBCImpl implements CheckInDAO {
    Connection conn;

    public CheckInDAOMySQLJDBCImpl(Connection connection) {
        this.conn=connection;
    }

    @Override
    public void insertCheckIns(String doctype, String doccode, Long prencode) {
        PreparedStatement ps;
        Random i = new Random();
        long id = 0;
        
        try{
            String sq1;
            Long l;
            boolean exist;
            int k = 0;
            do{
                sq1
                        = " SELECT * "
                        + " FROM checkin "
                        + " WHERE deleted = 0 AND "
                        + " id = ?;";
                l = new Long(i.nextInt(9999999));
                
                ps = conn.prepareStatement(sq1);
                ps.setLong(1, l);
                
                ResultSet resultSet = ps.executeQuery();
                exist = resultSet.next();
                
                if(!exist){
                    id=l;
                }
            }while(exist);
            
            sq1
                    = "INSERT INTO checkin "
                    + " (id, doctype, doccode, prencode, deleted ) "
                    + " VALUES ( ?, ?, ?, ?, '0');";
            
            ps = conn.prepareStatement(sq1);
            
            ps.setLong(1, id );
            ps.setString(2, doctype);
            ps.setString(3, doccode);
            ps.setLong(4, prencode);
            
            ps.executeUpdate();
            ps.close();
            
            
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
    }
    
}
