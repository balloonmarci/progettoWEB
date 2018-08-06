/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.logservice;

/**
 *
 * @author Marcello
 */

import java.io.IOException;
import java.util.logging.*;

import services.config.Configuration;

public class LogService {
    
    private static Logger applicationLogger;
    
    private LogService() {
    }
    
    public static Logger getApplicationLogger() {
        
        SimpleFormatter formatterTxt;
        Handler fileHandler;
        
        try {
            if(applicationLogger == null) {
                
                applicationLogger = Logger.getLogger(Configuration.GLOBAL_LOGGER_NAME);
                fileHandler = new FileHandler(Configuration.GLOBAL_LOGGER_FILE, true);
                formatterTxt = new SimpleFormatter();
                fileHandler.setFormatter(formatterTxt);
                applicationLogger.addHandler(fileHandler);
                applicationLogger.setLevel(Configuration.GLOBAL_LOGGER_LEVEL);
                applicationLogger.setUseParentHandlers(false);
                applicationLogger.log(Level.CONFIG, "Logger: {0} created.", applicationLogger.getName());
                
            }
        } catch (IOException e){
            applicationLogger.log(Level.SEVERE, "Error occured in Logger creation", e);
        }
        return applicationLogger;
    }
    
}
