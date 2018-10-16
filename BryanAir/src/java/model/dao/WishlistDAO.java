/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.mo.ConcreteFlight;
import model.mo.Wishlist;
import model.session.mo.LoggedUser;

/**
 *
 * @author Marcello
 */
public interface WishlistDAO {
    
    public Wishlist getWishlist(LoggedUser user);
    public void removeFromWishlist(LoggedUser user, ConcreteFlight flight);
    public void insertFlight(LoggedUser user, ConcreteFlight flight);
    
}
