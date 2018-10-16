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
    
    
    public String getHtmlDate(){
        String date, year, month, day;
        
        year=Integer.toString(this.getDeparturedate().getYear());
        
        if(this.getDeparturedate().getMonthOfYear()>=10){
            month=Integer.toString(this.getDeparturedate().getMonthOfYear());
        } else {
            month="0"+Integer.toString(this.getDeparturedate().getMonthOfYear());
        }
        
        if(this.getDeparturedate().getDayOfMonth()>=10){
            day=Integer.toString(this.getDeparturedate().getDayOfMonth());
        } else {
            day="0"+Integer.toString(this.getDeparturedate().getDayOfMonth());
        }
        date = year + "-" + month + "-" + day;        
        
        return date;
    }
    
    public String getHtmlDateTime(){
        String date, year, month, day, hour, minute, second;
        
        year=Integer.toString(this.getDeparturedate().getYear());
        
        if(this.getDeparturedate().getMonthOfYear()>=10){
            month=Integer.toString(this.getDeparturedate().getMonthOfYear());
        } else {
            month="0"+Integer.toString(this.getDeparturedate().getMonthOfYear());
        }
        
        if(this.getDeparturedate().getDayOfMonth()>=10){
            day=Integer.toString(this.getDeparturedate().getDayOfMonth());
        } else {
            day="0"+Integer.toString(this.getDeparturedate().getDayOfMonth());
        }
        
        if(this.getDeparturedate().getHourOfDay()>=10){
            hour=Integer.toString(this.getDeparturedate().getHourOfDay());
        } else {
            hour="0"+Integer.toString(this.getDeparturedate().getHourOfDay());
        }
        
        if(this.getDeparturedate().getMinuteOfHour()>=10){
            minute=Integer.toString(this.getDeparturedate().getMinuteOfHour());
        } else {
            minute="0"+Integer.toString(this.getDeparturedate().getMinuteOfHour());
        }
        
        if(this.getDeparturedate().getSecondOfMinute()>=10){
            second=Integer.toString(this.getDeparturedate().getSecondOfMinute());
        } else {
            second="0"+Integer.toString(this.getDeparturedate().getSecondOfMinute());
        }
        date = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;        
        
        return date;
        }
    
    
}
