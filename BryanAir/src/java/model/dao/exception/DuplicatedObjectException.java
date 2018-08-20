/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.exception;

/**
 *
 * @author Filippo
 */
public class DuplicatedObjectException extends Exception {
    
    public DuplicatedObjectException(){
    }
    
    public DuplicatedObjectException(String msg){
        super(msg);
    }
}
