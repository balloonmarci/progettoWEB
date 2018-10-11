/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mo;

/**
 *
 * @author Marcello
 */
public class Prenotation {
    private String id;
    private long clas;
    private float pricetotal;
    private String passengerfirst;
    private String passengerlast;
    private ConcreteFlight concreteFligth;
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

    public String getPassengerfirst() {
        return passengerfirst;
    }

    public void setPassengerfirst(String passengerfirst) {
        this.passengerfirst = passengerfirst;
    }

    public String getPassengerlast() {
        return passengerlast;
    }

    public void setPassengerlast(String passengerlast) {
        this.passengerlast = passengerlast;
    }

    public ConcreteFlight getConcreteFligth() {
        return concreteFligth;
    }

    public void setConcreteFligth(ConcreteFlight concreteFligth) {
        this.concreteFligth = concreteFligth;
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
