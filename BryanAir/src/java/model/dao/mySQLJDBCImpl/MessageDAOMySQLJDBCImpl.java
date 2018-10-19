/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.MessageDAO;
import model.mo.Conversation;
import model.mo.Message;

/**
 *
 * @author Marcello
 */
public class MessageDAOMySQLJDBCImpl implements MessageDAO{
    Connection conn;
    
    public MessageDAOMySQLJDBCImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public void send(long conv, String text, String sender) {
        PreparedStatement ps;
        Message message = new Message();
        message.setConvid(conv);
        message.setText(text);
        message.setSender(sender);
        
        try{
            String sq1;
            
            sq1 = " INSERT INTO message(idconv, time, text, sender, deleted)  "
                + " VALUES(?, NOW(), ?, ?, 0);";
            
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, message.getConvid());
            ps.setString(2, message.getText());
            ps.setString(3, message.getSender());
            
            ps.executeUpdate();
            
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
    
    Message read(ResultSet rs){
        Message message = new Message();
        try {
            message.setConvid(rs.getInt("idconv"));
        }catch (SQLException sqle){
        }
        try {
            message.setSendtime(message.initDate(rs.getTimestamp("time").getTime()));
        }catch (SQLException sqle){
        }
        try {
            message.setText(rs.getString("text"));
        }catch (SQLException sqle){
        }
        try {
            message.setSender(rs.getString("sender"));
        }catch (SQLException sqle){
        }
        try {
            message.setDeleted(rs.getBoolean("deleted"));
        }catch (SQLException sqle){
        }
        return message;
    }
    
    
    @Override
    public List<Message> findMessagesFromConversation(Conversation conversation) {
        PreparedStatement ps;
        ArrayList<Message> messages = new ArrayList<Message>();
        
        try{
            String sq1
                    =" SELECT * FROM message "
                    +" WHERE idconv = ? AND deleted = '0' "
                    +" ORDER BY time; ";
            
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, conversation.getIdconv());
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                messages.add(read(resultSet));
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return messages;
    } 
    
    
}
