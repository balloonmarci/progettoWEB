/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;
import java.util.List;
import model.dao.exception.DuplicatedObjectException;
import model.mo.Conversation;
import org.joda.time.DateTime;
import model.session.mo.LoggedUser;
import model.mo.Admin;
import model.mo.User;
import model.session.mo.LoggedAdmin;

/**
 *
 * @author Marcello
 */
public interface ConversationDAO {
    
    public long start (LoggedUser user, String title) throws DuplicatedObjectException;
    public void setAdmin(Admin admin);
    public void end();
    public void delete();
    public List<Conversation> findNewConversations();
    public List<Conversation> findUserConversations(LoggedUser user);
    public Conversation findById(long id);
    public List<Conversation> findAdminConversations(LoggedAdmin admin);
    
}
