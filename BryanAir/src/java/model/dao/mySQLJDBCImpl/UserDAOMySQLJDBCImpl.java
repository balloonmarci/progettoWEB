package model.dao.mySQLJDBCImpl;

/**
 *
 * @author Marcello
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.mo.User;
import model.dao.UserDAO;

public class UserDAOMySQLJDBCImpl implements UserDAO{
    Connection conn;
    
    public UserDAOMySQLJDBCImpl(Connection conn){
        this.conn = conn;
    }
    
    User read(ResultSet rs){
        
        User user = new User();
        try {
            user.setUserId(rs.getLong("id"));
        } catch (SQLException sqle) {
        }
        try {
            user.setUsername(rs.getString("username"));
        } catch (SQLException sqle) {
        }
        try {
            user.setEmail(rs.getString("email"));
        } catch (SQLException sqle) {
        }
        try {
            user.setPassword(rs.getString("password"));
        } catch (SQLException sqle) {
        }
        try {
            user.setFirstname(rs.getString("firstname"));
        } catch (SQLException sqle) {
        }
        try {
            user.setLastname(rs.getString("lastname"));
        } catch (SQLException sqle) {
        }
        try {
            user.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException sqle) {
        }
        return user;
        
    }
    
    public User findByUsername(String username){
        
        PreparedStatement ps;
        User user = null;
        
        try{
            
            String sql
              = " SELECT * "
              + "   FROM user "
              + " WHERE "
              + "   username = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, username );
            
            ResultSet resultSet = ps.executeQuery();
            
            if(resultSet.next()){
                user = read(resultSet);
            }
            resultSet.close();
            ps.close();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User insert(String username, String email, String password, String firstname, String lastname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findByUserId(Long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



