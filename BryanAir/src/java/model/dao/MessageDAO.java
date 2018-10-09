/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.mo.Conversation;
import model.mo.Message;

/**
 *
 * @author Marcello
 */
public interface MessageDAO {
    //0 per l'user, 1 per admin
    public void send(long conv, String text, String sender);
    public List<Message> findMessagesFromConversation(Conversation conversation);
    
}
