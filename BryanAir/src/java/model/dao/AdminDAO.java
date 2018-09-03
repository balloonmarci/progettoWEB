/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;
import model.mo.Admin;
import model.dao.exception.DuplicatedObjectException;

/**
 *
 * @author Filippo
 */
public interface AdminDAO {
    public Admin insert(
        String firstname,
        String lastname) throws DuplicatedObjectException;
    
    public void update(Admin admin) throws DuplicatedObjectException;
    public void delete(Admin admin);
    public Admin findByAdminFirstnameAndLastname(String firstname, String lastname);
}
