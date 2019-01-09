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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.dao.ConversationDAO;
import model.dao.exception.DuplicatedObjectException;
import model.mo.Admin;
import model.mo.Conversation;
import model.mo.User;
import model.session.mo.LoggedAdmin;
import model.session.mo.LoggedUser;

/**
 *
 * @author Marcello
 */
public class ConversationDAOMySQLJDBCImpl implements ConversationDAO {
    
    Connection conn;
    
    public ConversationDAOMySQLJDBCImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public long start (LoggedUser user, String title) throws DuplicatedObjectException{
        PreparedStatement ps;
        ResultSet resultSet;
        String sq1;
        Random rand = new Random();
        Long l;
        //RAND()*2147483647
        try{
            do{
                sq1 = " SELECT idconv "
                    + " FROM conversation "
                    + " WHERE idconv = ? ;";                    
                
                l = new Long(rand.nextInt(2147483647));
                
                ps = conn.prepareStatement(sq1);
                ps.setLong(1, l);
                
                resultSet = ps.executeQuery();
            }while(resultSet.next());
            
            try{
                sq1 = "INSERT INTO conversation (idconv, title, iduser, start, deleted) "
                    + "VALUES (?, ?, ?, CURRENT_TIMESTAMP, 0);";
                //273610922
                ps=conn.prepareStatement(sq1);
                ps.setLong(1, l);
                //ps.setLong(1, 666);
                ps.setString(2, title);
                ps.setLong(3, user.getUserId());

                ps.executeUpdate();
            }
            catch(SQLIntegrityConstraintViolationException e){
                throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un utente già esistente.");
            }
        }catch(SQLException e){
            throw new RuntimeException();
        }
        
        return l;
    }

    @Override
    public void setAdmin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void end(Long convid) {
        PreparedStatement ps;
        
        try{
            String sq1
                    ="UPDATE conversation SET end=NOW() "
                    +"WHERE idconv = ? ";
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, convid);
            ps.executeUpdate();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Conversation read(ResultSet rs){
        Conversation conversation = new Conversation();
        
        try {
            conversation.setIdconv(rs.getInt("idconv"));
        }catch (SQLException sqle){
        }
        
        try {
            conversation.setTitle(rs.getString("title"));
        }catch (SQLException sqle){
        }
        
        try {
            conversation.setUser(readUser(rs));
        }catch (SQLException sqle){
        }
        
        try {
            conversation.setAdmin(readAdmin(rs));
        }catch (SQLException sqle){
        }
        
        try {
            
            conversation.setStartdate(conversation.initDate(rs.getTimestamp("start").getTime()));
        }catch(SQLException sqle){
        }
        
        try {
            conversation.setEnddate(conversation.initDate(rs.getTimestamp("end").getTime()));
        }
        catch(SQLException sqle){
        }
        catch(NullPointerException e){
        }
        
        try {
            conversation.setDeleted(rs.getBoolean("deleted"));
        }catch(SQLException sqle){
        }
        return conversation;
    }

    @Override
    public List<Conversation> findNewConversations() {
        PreparedStatement ps;
        ArrayList<Conversation> conversations = new ArrayList<Conversation>();
        
        try{
            String sq1
                    ="SELECT C.idconv, C.title, C.start, C.end, C.deleted, U.id AS iduser, U.username, A.id AS idadmin, A.firstname AS adminfirstname "
                    +"FROM conversation AS C "
                    +"JOIN user AS U ON C.iduser=U.id "
                    +"LEFT JOIN admin AS A ON C.idadmin=A.id "
                    +"WHERE A.id IS NULL "
                    +"AND end IS NULL AND C.deleted = '0' ;";
            
            ps = conn.prepareStatement(sq1);
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                conversations.add(read(resultSet));
            }
            
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return conversations;
    }
    
    @Override
    public List<Conversation> findUserConversations(LoggedUser user) {
        PreparedStatement ps;
        ArrayList<Conversation> conversations = new ArrayList<Conversation>();
        
        try{
            String sq1
                    ="SELECT C.idconv, C.title, C.start, C.end, C.deleted, U.id AS iduser, U.username, A.id AS idadmin, A.firstname AS adminfirstname "
                    +"FROM conversation AS C "
                    +"JOIN user AS U ON C.iduser=U.id "
                    +"LEFT JOIN admin AS A ON C.idadmin=A.id "
                    +"WHERE C.iduser = ? "
                    +"AND end IS NULL AND C.deleted = '0' "
                    +"ORDER BY C.start;";
            
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, user.getUserId());
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                conversations.add(read(resultSet));
            }
            
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return conversations;
    }
    
    private User readUser(ResultSet rs) throws SQLException{
       User user = new User();
       
       user.setUserId(rs.getLong("iduser"));
       user.setUsername(rs.getString("username"));
       
       return user;
    }

    private Admin readAdmin(ResultSet rs) throws SQLException{
        Admin admin = new Admin();
        
        admin.setId(rs.getLong("idadmin"));
        admin.setFirstname(rs.getString("adminfirstname"));
        
        return admin;
    }
    
    @Override
    public Conversation findById(long id){
        PreparedStatement ps;
        Conversation conversation = new Conversation();
        
        try{
            String sq1
                    ="SELECT C.idconv, C.title, C.start, C.end, C.deleted, U.id AS iduser, U.username, A.id AS idadmin, A.firstname AS adminfirstname "
                    +"FROM conversation AS C "
                    +"JOIN user AS U ON C.iduser=U.id "
                    +"LEFT JOIN admin AS A ON C.idadmin=A.id "
                    +"WHERE C.idconv = ? AND C.deleted = '0' ";
            
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            conversation = read(resultSet);
            
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return conversation;
    }
    
    @Override
    public List<Conversation> findAdminConversations(LoggedAdmin admin) {
        PreparedStatement ps;
        ArrayList<Conversation> conversations = new ArrayList<Conversation>();
        
        try{
            String sq1
                    ="SELECT C.idconv, C.title, C.start, C.end, C.deleted, U.id AS iduser, "
                    + "U.username, A.id AS idadmin, A.firstname AS adminfirstname "
                    + "FROM conversation AS C "
                    + "JOIN user AS U ON C.iduser=U.id "
                    + "LEFT JOIN admin AS A ON C.idadmin=A.id "
                    + "WHERE C.idadmin = ? AND C.deleted = '0' "
                    + "AND end IS NULL "
                    + "ORDER BY C.start;";
            
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, admin.getId());
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                conversations.add(read(resultSet));
            }
            
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return conversations;
    }
    
    @Override
    public void adminJoinConversation(Long id, LoggedAdmin admin){
        PreparedStatement ps;
        
        try{
            String sq1
                    ="UPDATE conversation SET idadmin = ? "
                    +"WHERE idconv = ? ";
            ps = conn.prepareStatement(sq1);
            ps.setLong(1, admin.getId());
            ps.setLong(2, id);
            ps.executeUpdate();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
