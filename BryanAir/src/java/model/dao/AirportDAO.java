package model.dao;

/**
 *
 * @author Marcello
 */
import java.util.List;
import model.mo.Airport;
public interface AirportDAO {
    public Airport insert(
        String iata,
        String airportname,
        String city,
        String country);
    
    public void update(Airport airport);
    public void delete(Airport airport);
    public Airport findByIata(String iata);
    public Airport[] findByCity(String city);
    public List<Airport> findAllAirport();    
}
