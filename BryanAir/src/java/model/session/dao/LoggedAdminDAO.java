/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.session.dao;
import model.session.mo.LoggedAdmin;

/**
 *
 * @author Filippo
 */
public interface LoggedAdminDAO{
    
    public LoggedAdmin create(
            String firstname,
            String lastname);
    
    public void update(LoggedAdmin loggedAdmin);
    public void destroy();
    public LoggedAdmin find();
}
