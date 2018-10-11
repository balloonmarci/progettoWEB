/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.util.List;
import model.dao.PrenotationDAO;
import model.mo.ConcreteFlight;
import model.mo.Prenotation;
import model.mo.User;

/**
 *
 * @author Marcello
 */
public class PrenotationDAOMySQLJDBCImpl implements PrenotationDAO {
    
    Connection conn;
    public PrenotationDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void newPrenotation(long clas, String passengerfirst, String passengerlast, ConcreteFlight concreteFlight, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
