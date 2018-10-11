/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.mo.ConcreteFlight;
import model.mo.Prenotation;
import model.mo.User;

/**
 *
 * @author Marcello
 */
public interface PrenotationDAO {
    public void newPrenotation(long clas, 
            String passengerfirst, 
            String passengerlast, 
            ConcreteFlight concreteFlight, 
            User user);
    
    public void updateName(String passengerfirst, String passengerlast);
    public List<Prenotation> findUserPrenotations(User user);
    
    
}
