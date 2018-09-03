package model.mo;

/**
 *
 * @author Marcello
 */

import model.mo.Airport;

public class VirtualFlight {
    private String flightCode;
    private Float priceFirst;
    private Float priceSecond;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private boolean deleted;

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Float getPriceFirst() {
        return priceFirst;
    }

    public void setPriceFirst(float priceFirst) {
        this.priceFirst = priceFirst;
    }

    public Float getPriceSecond() {
        return priceSecond;
    }

    public void setPriceSecond(float priceSecond) {
        this.priceSecond = priceSecond;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    
    
    
    
}
