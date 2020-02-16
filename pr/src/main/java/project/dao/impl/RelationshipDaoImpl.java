package project.dao.impl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.dao.RelationshipDao;
import project.dao.impl.RelationshipDaoImpl;
import project.database.DatabaseConnection;
import project.model.Relationship;

public class RelationshipDaoImpl implements RelationshipDao{

    @Override
    public boolean canCreateRelationship(Relationship rl) {
        boolean check = false;
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "DECLARE @USER2 AS NUMERIC(18,0);\n" +
                    "SET @USER2 = (SELECT ID FROM USERINF WHERE USERNAME = '"+rl.getUsernameOrId2()+"');\n" +
                    "IF NOT EXISTS (SELECT * FROM REQUEST WHERE SENDERID = '"+rl.getUsernameOrId2()+"' AND RECEIVERID = @USER2) \n" +
                    "	BEGIN\n" +
                    "		SELECT * FROM USERINF;\n" +
                    "	END";
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) check = true;
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    @Override
    public void submitRelationship(Relationship rl) {
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "INSERT INTO RELATIONSHIP VALUES\n" +
                    "	('"+rl.getUsernameOrId1()+"','"+rl.getUsernameOrId2()+"',GETDATE()),\n" +
                    "	('"+rl.getUsernameOrId2()+"','"+rl.getUsernameOrId1()+"',GETDATE());";
            state.executeUpdate(sql);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createRelationship(Relationship rl) {
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "INSERT INTO REQUEST VALUES\n" +
                    "('"+rl.getUsernameOrId1()+"','"+rl.getUsernameOrId2()+"');";
            state.executeUpdate(sql);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean haveRequest(String id) {
        boolean check = false;
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "SELECT * FROM REQUEST WHERE RECEIVERID = '"+id+"';";
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public ArrayList<String> getRelationshipRequestList(String id) {
        ArrayList<String> requestList = new ArrayList<String>();
        try {           
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "SELECT SENDERID FROM REQUEST WHERE RECEIVERID = '"+id+"';";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                requestList.add(rs.getString("SENDERID"));             
            }
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requestList;
    }

    @Override
    public boolean isValidRelationship(Relationship rl) {
        boolean check = false;
        try {            
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = "USE PROJECT1;\n" +
                    "SELECT * FROM RELATIONSHIP WHERE ID = '"+rl.getUsernameOrId1()+"' AND FRIENDID = '"+rl.getUsernameOrId2()+"';";
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                return check = true;
            }
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public void deleteARequest(Relationship rl) {
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "USE PROJECT1;\n" +
                    "DELETE FROM REQUEST WHERE SENDERID = '"+rl.getUsernameOrId2()+"' AND RECEIVERID = '"+rl.getUsernameOrId1()+"';";
            state.executeUpdate(sql);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelationshipDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
