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
    
    public static DAOFactory getDAOFactory(String whichFactory){
        if(whichFactory.equals(MYSQLJDBCIMPL)){
            return new MySQLJDBCDAOFactory();
        } else {
            return null;
        }
    }
    
}
