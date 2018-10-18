/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Timestamp;
import java.util.List;
import model.mo.PushedFlight;
import model.session.mo.LoggedUser;
import org.joda.time.DateTime;

/**
 *
 * @author Marcello
 */
public interface PushedFlightDAO {
    public List<PushedFlight> getPushedFlights();     
    public List<PushedFlight> getWishlist(LoggedUser loggeduser);
    public void deleteFromWishlist(LoggedUser user, String flightcode, DateTime date);
    public void addToWishlist(LoggedUser user, String flightcode, DateTime date);
}
