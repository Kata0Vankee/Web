package project.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.dao.UserDao;
import project.database.DatabaseConnection;
import project.model.User;

public class UserDaoImpl implements UserDao{

    @Override
    public User getInfoByID(String idIn) {
        User user = null;
        try {
            Connection conn = new DatabaseConnection().initializeDatabase();
            Statement state = conn.createStatement();
            String sql = "USE PROJECT1; SELECT * FROM dbo.USERINF WHERE ID ='"+idIn+"';";
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            String id = rs.getString("ID");
            String username = rs.getString("USERNAME");
            String password = rs.getString("password");
            String firstname = rs.getString("FIRSTNAME");
            String lastname = rs.getString("LASTNAME");
            String email = rs.getString("EMAIL");
            user = new User(id,username, password, firstname, lastname, email);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public void insertUser(User user) {
        try {
            Connection conn = new DatabaseConnection().initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1; INSERT INTO dbo.USERINF(USERNAME,PASSWORD,FIRSTNAME,LASTNAME,EMAIL) VALUES"
                    + "('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFirstname()+"','"+user.getLastname()+"','"+user.getEmail()+"');";
            state.executeUpdate(sql);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isValidUsername(String username) {
        boolean check = false;
        try {
            Connection conn = new DatabaseConnection().initializeDatabase();
            Statement state = conn.createStatement();
            String sql = "USE PROJECT1;"
                    + "SELECT * FROM USERINF WHERE USERNAME = '"+username+"';";
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                check = true;
            }
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public boolean isValidUSer(User user) {
        boolean check = false;
        try {
            Connection conn = new DatabaseConnection().initializeDatabase();
            Statement state = conn.createStatement();
            String sql = "USE PROJECT1;"
                    + "SELECT * FROM USERINF WHERE USERNAME = '"+user.getUsername()+"' OR ID = '"+user.getId()+";'";
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                check = true;
            }
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public User getInfoByUsername(String usernameIn) {
        User user = null;
        try {
            Connection conn = new DatabaseConnection().initializeDatabase();
            Statement state = conn.createStatement();
            String sql = "USE PROJECT1; SELECT * FROM dbo.USERINF WHERE USERNAME ='"+usernameIn+"';";
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            String id = rs.getString("ID");
            String username = rs.getString("USERNAME");
            String password = rs.getString("password");
            String firstname = rs.getString("FIRSTNAME");
            String lastname = rs.getString("LASTNAME");
            String email = rs.getString("EMAIL");
            user = new User(id,username, password, firstname, lastname, email);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    
    @Override
    public String getAvatar(String userId) {
        String img = "";
        try {
            Connection conn = new DatabaseConnection().initializeDatabase();
            Statement state = conn.createStatement();
            String sql = "USE PROJECT1;"
                    + "SELECT AVATAR FROM USERINF WHERE ID = '"+userId+"';";
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            img += rs.getString("AVATAR").toString();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }

    @Override
    public void saveAvatar(String userId, String avatar) {
        try {
            Connection conn = new DatabaseConnection().initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "UPDATE USERINF \n" +
                    "SET AVATAR = '"+avatar+"' WHERE ID = '"+userId+"';";
            System.out.println(avatar);
            System.out.println(userId);
            state.executeUpdate(sql);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<String> getFriendsList(String userId) {
        ArrayList<String> listFriends = new ArrayList<>();
        try {
            Connection conn = new DatabaseConnection().initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "SELECT FRIENDID FROM RELATIONSHIP WHERE ID = '"+userId+"';";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                listFriends.add(rs.getString("FRIENDID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFriends;
    }

    @Override
    public ArrayList<String> getAllUsersList() {
        ArrayList<String> listUsers = new ArrayList<>();
        try {
            Connection conn = new DatabaseConnection().initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "SELECT ID FROM USERINF;";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                listUsers.add(rs.getString("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers;
    }
    
   
    
}
