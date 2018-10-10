package model.dao.mySQLJDBCImpl;

/**
 *
 * @author Marcello
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.mo.VirtualFlight;
import model.dao.VirtualFlightDAO;
import model.dao.exception.DuplicatedObjectException;

import model.mo.Airport;


public class VirtualFlightDAOMySQLJDBCImpl implements VirtualFlightDAO{
    Connection conn;

    public VirtualFlightDAOMySQLJDBCImpl(Connection conn){
        this.conn = conn;
    }

    VirtualFlight read(ResultSet rs){
        VirtualFlight virtualFlight = new VirtualFlight();
        virtualFlight.setDepartureAirport(new Airport());
        virtualFlight.setArrivalAirport(new Airport());

        try {
            virtualFlight.setFlightCode(rs.getString("flightcode"));
        } catch (SQLException sqle) {
        }

        try {
            virtualFlight.setPriceFirst(rs.getFloat("pricefirst"));
        } catch (SQLException sqle) {
        }

        try{
            virtualFlight.setPriceSecond(rs.getFloat("pricesecond"));
        }catch(SQLException sqle){
        }

        try{
            virtualFlight.getDepartureAirport().setIata(rs.getString("departureairport"));
        }catch(SQLException sqle){
        }

        try{
            virtualFlight.getArrivalAirport().setIata(rs.getString("arrivalairport"));
        }catch(SQLException sqle){
        }

        try{
            virtualFlight.setDeleted(rs.getBoolean("deleted"));
        }catch(SQLException sqle){
        }

        return virtualFlight;
    }

    @Override
    public VirtualFlight insert(String flightCode, Float priceFirst, Float priceSecond, Airport departureAirport, Airport arrivalAirport)
            throws DuplicatedObjectException {

              PreparedStatement ps;
              ResultSet resultSet;
              VirtualFlight virtualFlight = new VirtualFlight();
              virtualFlight.setFlightCode(flightCode);
              virtualFlight.setPriceFirst(priceFirst);
              virtualFlight.setPriceSecond(priceSecond);
              virtualFlight.setDepartureAirport(departureAirport);
              virtualFlight.setArrivalAirport(arrivalAirport);

              try{
                  String sql;
                  sql = "SELECT * "
                      + "FROM virtualflight "
                      + "WHERE departureairport = ? AND "
                      + "arrivalairport = ? AND "
                      + "deleted = '0' ";

                  ps = conn.prepareStatement(sql);
                  ps.setString(1, virtualFlight.getDepartureAirport().getIata());
                  ps.setString(2, virtualFlight.getArrivalAirport().getIata());

                  resultSet = ps.executeQuery();

                  if(resultSet.next())
                      throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un volo già esistente.");

                  try {
                      sql
                      = " INSERT INTO virtualflight "
                      + "   ( flightcode,"
                      + "     pricefirst,"
                      + "     pricesecond,"
                      + "     departureairport,"
                      + "     arrivalairport,"
                      + "     deleted "
                      + "   ) "
                      + " VALUES (?,?,?,?,?,'0')";

                      ps = conn.prepareStatement(sql);

                      ps.setString(1, virtualFlight.getFlightCode());
                      ps.setFloat(2, virtualFlight.getPriceFirst());
                      ps.setFloat(3, virtualFlight.getPriceSecond());
                      ps.setString(4, virtualFlight.getDepartureAirport().getIata());
                      ps.setString(5, virtualFlight.getArrivalAirport().getIata());

                      ps.executeUpdate();
                  }
                  catch(SQLIntegrityConstraintViolationException e){
                      throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un volo già esistente.");
                  }
              }
              catch(SQLException e)
              {
                  throw new RuntimeException(e);
              }

              return virtualFlight;
    }

    @Override
    public void update(VirtualFlight virtualFlight) throws DuplicatedObjectException {
        PreparedStatement ps;
//        ResultSet resultSet;

        try {

            String sql;
//            sql = "SELECT * "
//                + "FROM virtualflight "
//                + "WHERE departureairport = ? AND "
//                + "arrivalairport = ? AND "
//                + "deleted = '0'";
//
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, virtualFlight.getDepartureAirport().getIata());
//            ps.setString(2, virtualFlight.getArrivalAirport().getIata());
//
//            resultSet = ps.executeQuery();
//
//            if(resultSet.next())
//                throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un volo già esistente.");


            sql
            = "UPDATE virtualflight "
            + "SET "
            + "pricefirst = ?, "
            + "pricesecond = ?, "
            + "departureairport = ?, "
            + "arrivalairport = ? "
            + "WHERE "
            + "flightcode = ?";

            ps = conn.prepareStatement(sql);

            ps.setFloat(1, virtualFlight.getPriceFirst());
            ps.setFloat(2, (virtualFlight.getPriceSecond()));
            ps.setString(3, virtualFlight.getDepartureAirport().getIata());
            ps.setString(4, virtualFlight.getArrivalAirport().getIata());
            ps.setString(5, virtualFlight.getFlightCode());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(VirtualFlight virtualFlight) {
        PreparedStatement ps;

        try {

            String sql
                    = "DELETE "
                    + "FROM virtualflight "
                    + "WHERE "
                    + "flightcode = ? ";

            ps = conn.prepareStatement(sql);

            ps.setString(1, virtualFlight.getFlightCode());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
      public VirtualFlight findByFlightCode(String flightCode) {
         PreparedStatement ps;
         VirtualFlight virtualFlight = new VirtualFlight();

         try {
              String sq1
                      = "SELECT * "
                      + " FROM virtualflight "
                      + " WHERE flightcode = ?";

              ps = conn.prepareStatement(sq1);
              ps.setString(1, flightCode);

              ResultSet resultSet = ps.executeQuery();

              if(resultSet.next())
                  virtualFlight = read(resultSet);

              resultSet.close();
              ps.close();

         }catch(SQLException e){
              throw new RuntimeException(e);
         }

         return virtualFlight;
      }

      @Override
      public List<VirtualFlight> findAllVirtualFlights() {
          PreparedStatement ps;
          ArrayList<VirtualFlight> virtualFlights = new ArrayList<VirtualFlight>();

          try {
              String sq1
                      = "SELECT * "
                      + " FROM virtualflight "
                      + " WHERE deleted = '0' ";

              ps = conn.prepareStatement(sq1);

              ResultSet resultSet = ps.executeQuery();

              while(resultSet.next()){

                  virtualFlights.add(read(resultSet));

              }

              resultSet.close();
              ps.close();


          }catch(SQLException e){
              throw new RuntimeException(e);
          }

          return virtualFlights;
      }

      @Override
      public List<VirtualFlight> findSelectedVirtualFlights(String flightCode, Airport depAirport, Airport arrAirport, String orderBy, String direction) {
          PreparedStatement ps;
          ArrayList<VirtualFlight> virtualFlights = new ArrayList<VirtualFlight>();

          try {
              String sq1
                      = String.format(
                      "SELECT vf.* "
                      + "FROM virtualflight AS vf JOIN airport AS depair ON vf.departureairport = depair.iata "
                      + "JOIN airport AS arrair ON vf.arrivalairport = arrair.iata "
                      + "WHERE "
                      + "vf.flightcode LIKE ? AND "
                      + "depair.airportname LIKE ? AND "
                      + "arrair.airportname LIKE ? AND "
                      + "depair.country LIKE ? AND "
                      + "arrair.country LIKE ? AND "
                      + "vf.deleted = '0' "
                      + "ORDER BY vf.%s %s ", orderBy, (direction == null)? "ASC":direction);

              ps = conn.prepareStatement(sq1);

              ps.setString(1, flightCode + "%");
              ps.setString(2, depAirport.getAirportname() + "%");
              ps.setString(3, arrAirport.getAirportname() + "%");
              ps.setString(4, depAirport.getCountry() + "%");
              ps.setString(5, arrAirport.getCountry() + "%");
              ResultSet resultSet = ps.executeQuery();

              while(resultSet.next()){
                  virtualFlights.add(read(resultSet));
              }

              resultSet.close();
              ps.close();


          }catch(SQLException e){
              throw new RuntimeException(e);
          }

          return virtualFlights;
      }

  }

