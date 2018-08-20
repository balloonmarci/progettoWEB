/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.config;

/**
 *
 * @author Marcello
 */

import java.util.logging.Level;
import model.dao.DAOFactory;
import model.session.dao.SessionDAOFactory;

public class Configuration {
    
    //Logger
    public static final String GLOBAL_LOGGER_NAME="BryanAir";  
    public static final String GLOBAL_LOGGER_FILE="C:\\Log\\BrianAir_log.%g.txt";
    public static final Level GLOBAL_LOGGER_LEVEL=Level.ALL;
    
    
    //Session
    public static final String SESSION_IMPL=SessionDAOFactory.COOKIEIMPL;
    
    
    //DB
    public static final String DAO_IMPL=DAOFactory.MYSQLJDBCIMPL;
    public static final String DATABASE_DRIVER="com.mysql.jdbc.Driver";
    //public static final String DATABASE_URL="jdbc:mysql://localhost/brianairdb?user=root&password=";
    public static final String DATABASE_URL="jdbc:mysql://localhost/bryanairdb?user=root&password=&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
}
