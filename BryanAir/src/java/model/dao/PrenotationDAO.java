/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;
import model.dao.exception.DuplicatedObjectException;
import model.mo.ConcreteFlight;
import model.mo.Prenotation;
import model.mo.PrenotationView;
import model.mo.User;
import model.session.mo.LoggedUser;
import org.joda.time.DateTime;

/**
 *
 * @author Marcello
 */
public interface PrenotationDAO{
    public void newPrenotation(List<Prenotation>prenotations) throws DuplicatedObjectException;
    public void updateName(String passengerfirst, String passengerlast);
    public List<PrenotationView> findUserPrenotations(LoggedUser user);
    public List<PrenotationView> findUserPrenotationsCheckIn(LoggedUser user);
    public List<Prenotation> findPrenotationDetail(LoggedUser user, String flightcode, DateTime departuredate, DateTime arrivaldate);
    
}
