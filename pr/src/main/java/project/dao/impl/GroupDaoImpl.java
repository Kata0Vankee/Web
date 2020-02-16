package project.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.dao.GroupDao;
import project.database.DatabaseConnection;
import project.model.Group;
import project.model.User;

public class GroupDaoImpl implements GroupDao{

    @Override
    public Group getGroupInfoById(String id) {
        Group gr = null;
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "SELECT ID, NAME, USERID FROM ROOMCHAT,ROOMPARTICIPANTS WHERE ID = ROOMID AND ID = '"+id+"';";
            ResultSet rs = state.executeQuery(sql);  
            ArrayList<User> temp = new ArrayList<User>();
            UserDaoImpl userDaoImpl = new UserDaoImpl(); 
            String groupID = "";
            String groupNameT = "";
            while (rs.next()){
                groupID = rs.getString("ID");
                groupNameT = rs.getString("NAME");
                String thisUserId = rs.getString("USERID");                              
                temp.add(userDaoImpl.getInfoByID(thisUserId));
            }          
            gr = new Group(groupID, groupNameT, temp);
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gr;
    }

    @Override
    public Group getGroupInfoByName(String groupName) {
        Group gr = null;
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            System.out.println(groupName);
            String sql = 
                    "USE PROJECT1;\n" +
                    "SELECT ID, NAME, USERID FROM ROOMCHAT,ROOMPARTICIPANTS WHERE NAME = N'"+groupName+"';";
            ResultSet rs = state.executeQuery(sql);  
            ArrayList<User> temp = new ArrayList<User>();
            UserDaoImpl userDaoImpl = new UserDaoImpl(); 
            String groupID = "";
            String groupNameT = "";
            while (rs.next()){
                groupID = rs.getString("ID");
                groupNameT = rs.getString("NAME");
                String thisUserId = rs.getString("USERID");                              
                temp.add(userDaoImpl.getInfoByID(thisUserId));
                System.out.println("user id: "+thisUserId);
            }          
            gr = new Group(groupID, groupNameT, temp);
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gr;
    }

    @Override
    public void createGroup(User user, String groupName) {
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "INSERT INTO ROOMCHAT VALUES\n" +
                    "	(N'"+groupName+"',GETDATE(),'NULL');\n" +
                    "DECLARE @ROOMID AS NUMERIC(18,0);\n" +
                    "SET @ROOMID = (SELECT ID FROM ROOMCHAT WHERE NAME = N'"+groupName+"');\n" +
                    "INSERT INTO ROOMPARTICIPANTS VALUES\n" +
                    "	(@ROOMID,'"+user.getId()+"',GETDATE());";
            state.executeUpdate(sql);           
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isIn(String groupId, String userId) {
        boolean check = false;
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE newBACKUP;\n" +
                    "SELECT * FROM ROOMCHAT,ROOMPARTICIPANTS\n" +
                    "WHERE ID = ROOMID AND ID = '"+groupId+"' AND USERID = '"+userId+"';";
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()){
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public void addMember(String groupId, String userId) {
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "INSERT INTO ROOMPARTICIPANTS VALUES\n" +
                    "	('"+groupId+"','"+userId+"',GETDATE());";
            state.executeUpdate(sql);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User findCreater(String groupId) {
        User user = null;
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "SELECT TOP(1) ID, USERID, ROOMPARTICIPANTS.TIMEVALUE FROM ROOMCHAT, ROOMPARTICIPANTS \n" +
                    "WHERE ID = ROOMID AND ID = '"+groupId+"'\n" +
                    "GROUP BY ID, USERID, ROOMPARTICIPANTS.TIMEVALUE ORDER BY ROOMPARTICIPANTS.TIMEVALUE ASC;";
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            user = userDaoImpl.getInfoByID(rs.getString("USERID"));
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public ArrayList<String> takeGroupIdListJoined(String userId) {
        ArrayList<String> list =new ArrayList<String>();
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = "USE PROJECT1;\n" +
                        "SELECT ROOMID FROM ROOMPARTICIPANTS WHERE USERID = '"+userId+"';";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                System.out.println("room id :"+rs.getString("ROOMID"));
                list.add(rs.getString("ROOMID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void saveAvatar(String groupId, String avatar) {
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            System.out.println("thay doi avatar nhom : "+groupId+" "+avatar);
            String sql = 
                    "USE PROJECT1;\n" +
                    "UPDATE ROOMCHAT SET AVATAR = '"+avatar+"'\n" +
                    "WHERE ID = '"+groupId+"';";
            state.executeUpdate(sql);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getAvatar(String groupID) {
        String avatar = "";
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "SELECT AVATAR FROM ROOMCHAT WHERE ID = '"+groupID+"';";
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            avatar += rs.getString("AVATAR");
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avatar;
    }
    
}
