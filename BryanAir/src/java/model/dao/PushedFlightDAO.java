/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.mo.PushedFlight;

/**
 *
 * @author Marcello
 */
public interface PushedFlightDAO {
    public List<PushedFlight> getPushedFlights();       
}
