package model.dao.mySQLJDBCImpl;

/**
 *
 * @author Marcello
 */

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.AirportDAO;
import model.dao.VirtualFlightDAO;
import model.dao.ConcreteFlightDAO;
import model.dao.AdminDAO;
import model.dao.ConversationDAO;
import services.config.Configuration;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import model.dao.CheckInDAO;
import model.dao.MessageDAO;
import model.dao.PrenotationDAO;
import model.dao.PushedFlightDAO;

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
    
    @Override
    public VirtualFlightDAO getVirtualFlightDAO(){
        return new VirtualFlightDAOMySQLJDBCImpl(connection);
    }
    
    @Override
    public ConcreteFlightDAO getConcreteFlightDAO(){
        return new ConcreteFlightDAOMySQLJDBCImpl(connection);
    }
    
    @Override
    public AdminDAO getAdminDAO(){
        return new AdminDAOMySQLJDBCImpl(connection);
    }
    
    @Override
    public ConversationDAO getConversationDAO(){
        return new ConversationDAOMySQLJDBCImpl(connection);
    }
    
    @Override
    public MessageDAO getMessageDAO(){
        return new MessageDAOMySQLJDBCImpl(connection);
    }

    @Override
    public PrenotationDAO getPrenotationDAO() {
        return new PrenotationDAOMySQLJDBCImpl(connection);
    }

    @Override
    public PushedFlightDAO getPushedFlightDAO() {
        return new PushedFligthDAOMySQLJDBCImpl(connection);
    }
    
    @Override
    public CheckInDAO getCheckInDAO() {
        return new CheckInDAOMySQLJDBCImpl(connection);
    }
    
}
