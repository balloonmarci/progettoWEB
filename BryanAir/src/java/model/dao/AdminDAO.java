/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.mo.Admin;

/**
 *
 * @author Filippo
 */
public interface AdminDAO {
    public Admin insert(String firstname, String lastname);
    public void update(Admin admin);
    public void delete(Admin admin);
    public Admin findAdminById(Long id);
    public Admin findAdminByIdAndName(String firstname, String lastname, Long id);


    

}
