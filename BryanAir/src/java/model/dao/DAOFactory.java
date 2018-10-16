package model.dao;

/**
 *
 * @author Marcello
 */

import model.dao.mySQLJDBCImpl.MySQLJDBCDAOFactory;

public abstract class DAOFactory {
    
    //Lista di DAO supportati
    public static final String MYSQLJDBCIMPL = "MySQLJDBCImpl";
    
    public abstract void beginTransaction();
    public abstract void commitTransaction();
    public abstract void rollbackTransaction();
    public abstract void closeTransaction();
    
    public abstract UserDAO getUserDAO();
    public abstract AirportDAO getAirportDAO();
    public abstract VirtualFlightDAO getVirtualFlightDAO();
    public abstract ConcreteFlightDAO getConcreteFlightDAO();
    public abstract PrenotationDAO getPrenotationDAO();
    public abstract AdminDAO getAdminDAO();
    public abstract ConversationDAO getConversationDAO();
    public abstract MessageDAO getMessageDAO();
    public abstract PushedFlightDAO getPushedFlightDAO();
    
    public static DAOFactory getDAOFactory(String whichFactory){
        if(whichFactory.equals(MYSQLJDBCIMPL)){
            return new MySQLJDBCDAOFactory();
        } else {
            return null;
        }
    }
    
}
