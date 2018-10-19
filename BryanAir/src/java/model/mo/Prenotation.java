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
    private String passengertitle;
    private ConcreteFlight concreteFlight;
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
    
    public String getPassengerTitle() {
        return passengertitle;
    }

    public void setPassengerTitle(String title) {
        this.passengertitle = title;
    }
    
    public ConcreteFlight getConcreteFlight() {
        return concreteFlight;
    }

    public void setConcreteFlight(ConcreteFlight concreteFligth) {
        this.concreteFlight = concreteFligth;
    }
    
    public DateTime getPrenotationDate() {
        return prenotationdate;
    }

    public void setPrenotationDate(DateTime prenotationdate) {
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
