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
import model.mo.Admin;
import model.dao.AdminDAO;
import model.dao.exception.DuplicatedObjectException;

import java.util.Random;

/**
 *
 * @author Filippo
 */
public class AdminDAOMySQLJDBCImpl implements AdminDAO{
    Connection conn;
    
    public AdminDAOMySQLJDBCImpl(Connection conn){
        this.conn = conn;
    }
    
    Admin read(ResultSet rs){
        Admin admin = new Admin();
       
        try {
            admin.setId(rs.getLong("id"));
        } catch (SQLException sqle) {
        }
        try {
            admin.setFirstname(rs.getString("firstname"));
        } catch (SQLException sqle) {
        }
        try {
            admin.setLastname(rs.getString("lastname"));
        } catch (SQLException sqle) {
        }
        try {
            admin.setPassword(rs.getString("password"));
        } catch (SQLException sqle) {
        }
        try {
            admin.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException sqle) {
        }
        return admin;
    }
    
    @Override
    public Admin insert(String firstname, String lastname){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(Admin admin){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(Admin admin){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Admin findAdminByIdAndName(String firstname, String lastname, Long id) {
        PreparedStatement ps;
        Admin admin = null;

        try {

            String sql
                    = "SELECT * "
                    + "FROM admin "
                    + "WHERE "
                    + "id = ? AND "
                    + "firstname = ? AND "
                    + "lastname = ? AND "
                    + "deleted = '0' ";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
           
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                admin = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
}

