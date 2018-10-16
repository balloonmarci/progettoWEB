/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mo;

import java.util.List;
import model.session.mo.LoggedUser;

/**
 *
 * @author Marcello
 */
public class Wishlist {
    private LoggedUser user;
    private List<ConcreteFlight> concreteFlights;

    public LoggedUser getUser() {
        return user;
    }

    public void setUser(LoggedUser user) {
        this.user = user;
    }

    public List<ConcreteFlight> getConcreteFlights() {
        return concreteFlights;
    }

    public void setConcreteFlights(List<ConcreteFlight> concreteFlights) {
        this.concreteFlights = concreteFlights;
    }
    
    
    
}
