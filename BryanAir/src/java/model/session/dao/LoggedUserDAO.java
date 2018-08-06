package model.session.dao;

/**
 *
 * @author Marcello
 */

import model.session.mo.LoggedUser;

public interface LoggedUserDAO {
    
    public LoggedUser create(
            Long userId,
            String username,
            String firstname,
            String lastname);
    
    public void update(LoggedUser loggedUser);
    public void destroy();
    public LoggedUser find();
    
}
