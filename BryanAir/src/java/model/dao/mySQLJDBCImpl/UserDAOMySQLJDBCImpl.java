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
import model.mo.User;
import model.dao.UserDAO;
import model.dao.exception.DuplicatedObjectException;

import java.util.Random;

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
    public User insert(String username, String email, String password, String firstname, String lastname) 
            throws DuplicatedObjectException {
        
        PreparedStatement ps;
        Random i = new Random();
        
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        
        
        
        try{
            String sql;
            Long l;
            boolean exist;
            
            do{
                sql = " SELECT username "
                    + " FROM user "
                    + " WHERE "
                    + " deleted ='0' AND "
                    + " id = ?";
                
                l = new Long(i.nextInt(99999));
                
                ps = conn.prepareStatement(sql);
                ps.setLong(1, l);
                
                
                ResultSet resultSet = ps.executeQuery();
                exist = resultSet.next();
                
            }while(exist);
            user.setUserId(l);
            
            try {
                sql
                = " INSERT INTO user "
                + "   ( id,"
                + "     username,"
                + "     email,"
                + "     password,"
                + "     firstname,"   
                + "     lastname,"      
                + "     deleted "
                + "   ) "
                + " VALUES (?,?,?,?,?,?,'0')";
            
                ps = conn.prepareStatement(sql);
            
                ps.setLong(  1, user.getUserId());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setString(5, user.getFirstname());
                ps.setString(6, user.getLastname());
            
                ps.executeUpdate();
            }
            catch(SQLIntegrityConstraintViolationException e){
                throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un utente già esistente.");
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        
        return user;
    }

    @Override
    public void update(User user) throws DuplicatedObjectException{
        PreparedStatement ps;
        
        try {
            try{
                String sql 
                = " UPDATE user "
                + " SET "
                + " username = ?,"
                + "   firstname = ?, "
                + "   lastname = ?, "
                + "   email = ? "              
                + " WHERE "
                + "   id = ? ";

                ps = conn.prepareStatement(sql);
                int i = 1;
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getFirstname());
                ps.setString(3, user.getLastname());
                ps.setString(4, user.getEmail());
                ps.setLong(5, user.getUserId());
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
    public void delete(User user) {
        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE user "
                    + " SET deleted='1' "
                    + " WHERE "
                    + " id=?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, user.getUserId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByUserId(Long userId) {
        PreparedStatement ps;
        User user = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM user "
                    + " WHERE "
                    + "   id = ? AND"
                    + " deleted = '0' ";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, userId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                user = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}



