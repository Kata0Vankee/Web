package project.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.dao.MessageDao;
import project.database.DatabaseConnection;
import project.model.Group;
import project.model.Message;

public class MessageDaoImpl implements MessageDao{

    @Override
    public void saveMessageGroup(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Message> loadMessageGroup(Group group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Message> loadMessageGroup(String groupId) {
        ArrayList<Message> listMessage = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "SELECT * FROM ROOMMESSAGE WHERE ROOMID = '"+groupId+"'\n" +
                    "ORDER BY TIMEVALUE DESC;";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                Message messageTemp = new Message();
                messageTemp.setFrom(rs.getString("SENDERID"));
                messageTemp.setTo(rs.getString("ROOMID"));
                messageTemp.setContent(rs.getString("BODY"));
                messageTemp.setTime(rs.getString("TIMEVALUE"));
                listMessage.add(messageTemp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMessage;
    }
    
}
