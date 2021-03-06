/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mo;

import org.joda.time.DateTime;

/**
 *
 * @author Filippo
 */
public class ConcreteFlight {
    private DateTime date;
    private DateTime arrivaldate;
    private float multiplier;
    private boolean push;
    private VirtualFlight vFlight;
    private Admin admin;
    private int seatFirst;
    private int seatSecond;
    private boolean deleted;
    
    public DateTime getDate(){
        return date;
    }
    
    public DateTime getArrivalDate() {
        return arrivaldate;
    }

    public float getMultiplier(){
        return multiplier;
    }
    
    public boolean getPush(){
        return push;
    }
    
    public VirtualFlight getVirtualFlight(){
        return vFlight;
    }
    
    public int getSeatFirst(){
        return seatFirst;
    }
    
    public int getSeatSecond(){
        return seatSecond;
    }
    
    public Admin getAdmin() {
        return admin;
    }
    
    public boolean isDeleted(){
        return deleted;
    }
    
    public void setDate(DateTime date) {
        this.date = date;
    }
    
    public void setArrivalDate(DateTime arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public void setVirtualFlight(VirtualFlight vFlight) {
        this.vFlight = vFlight;
    }

    public void setSeatFirst(int seatFirst) {
        this.seatFirst = seatFirst;
    }

    public void setSeatSecond(int seatSecond) {
        this.seatSecond = seatSecond;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
