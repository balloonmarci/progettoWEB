package model.dao.mySQLJDBCImpl;

/**
 *
 * @author Marcello
 */

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.AirportDAO;
import services.config.Configuration;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLJDBCDAOFactory extends DAOFactory {
    
    private Connection connection;

    @Override
    public void beginTransaction() {
        try {
            Class.forName(Configuration.DATABASE_DRIVER);
            this.connection = DriverManager.getConnection(Configuration.DATABASE_URL);
            this.connection.setAutoCommit(false);
        }catch(ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commitTransaction() {
        try {
            this.connection.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            this.connection.rollback();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeTransaction() {
        try {
            this.connection.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOMySQLJDBCImpl(connection);
    }
    
    @Override
    public AirportDAO getAirportDAO(){
        return new AirportDAOMySQLJDBCImpl(connection);
    }
    
}
