/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mo;

import org.joda.time.DateTime;

/**
 *
 * @author Marcello
 */
public class Prenotation {
    private String id;
    private long clas;
    private float pricetotal;
    private String passengerfirstname;
    private String passengerlastname;
    private ConcreteFlight concreteFligth;
    private DateTime prenotationdate;
    private User user;
    private boolean deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getClas() {
        return clas;
    }

    public void setClas(long clas) {
        this.clas = clas;
    }

    public float getPricetotal() {
        return pricetotal;
    }

    public void setPricetotal(float pricetotal) {
        this.pricetotal = pricetotal;
    }
    
    public String getPassengerfirstname() {
        return passengerfirstname;
    }

    public void setPassengerfirstname(String passengerfirstname) {
        this.passengerfirstname = passengerfirstname;
    }

    public String getPassengerlastname() {
        return passengerlastname;
    }

    public void setPassengerlastname(String passengerlastname) {
        this.passengerlastname = passengerlastname;
    }
    
    public ConcreteFlight getConcreteFligth() {
        return concreteFligth;
    }

    public void setConcreteFligth(ConcreteFlight concreteFligth) {
        this.concreteFligth = concreteFligth;
    }
    
    public DateTime getPrenotationdate() {
        return prenotationdate;
    }

    public void setPrenotationdate(DateTime prenotationdate) {
        this.prenotationdate = prenotationdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    
    
    
    
    
}
