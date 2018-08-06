package model.dao;

/**
 *
 * @author Marcello
 */

import model.mo.User;
public interface UserDAO {
    
    public User insert(
        String username,
        String email,
        String password,
        String firstname,
        String lastname);
    
    public void update(User user);
    public void delete(User user);
    public User findByUserId(Long userId);
    public User findByUsername(String username);
            
}
