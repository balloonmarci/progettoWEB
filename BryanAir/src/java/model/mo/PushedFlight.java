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
public class PushedFlight {
    private String flightcode;
    private String departureairport;
    private String arrivalairport;
    private String arrivalcity;
    private float finalprice;
    private DateTime departuredate;
    private DateTime arrivaldate;
    private float difference;

    public String getFlightcode() {
        return flightcode;
    }

    public void setFlightcode(String flightcode) {
        this.flightcode = flightcode;
    }

    public String getDepartureairport() {
        return departureairport;
    }

    public void setDepartureairport(String departureairport) {
        this.departureairport = departureairport;
    }

    public String getArrivalairport() {
        return arrivalairport;
    }

    public void setArrivalairport(String arrivalairport) {
        this.arrivalairport = arrivalairport;
    }

    public String getArrivalcity() {
        return arrivalcity;
    }

    public void setArrivalcity(String arrivalcity) {
        this.arrivalcity = arrivalcity;
    }
    
    

    public float getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(float finalprice) {
        this.finalprice = finalprice;
    }

    public DateTime getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(DateTime departuredate) {
        this.departuredate = departuredate;
    }

    public DateTime getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(DateTime arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public float getDifference() {
        return difference;
    }

    public void setDifference(float difference) {
        this.difference = difference;
    }
    
    
}
