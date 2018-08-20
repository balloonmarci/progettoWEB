package model.dao;

/**
 *
 * @author Marcello
 */

import model.mo.User;
import model.dao.exception.DuplicatedObjectException;
public interface UserDAO {
    
    public User insert(
        String username,
        String email,
        String password,
        String firstname,
        String lastname) throws DuplicatedObjectException;
    
    public void update(User user) throws DuplicatedObjectException;
    public void delete(User user);
    public User findByUserId(Long userId);
    public User findByUsername(String username);
            
}
