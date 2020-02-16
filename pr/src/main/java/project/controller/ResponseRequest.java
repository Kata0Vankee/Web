package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.relation.Relation;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.impl.RelationshipDaoImpl;
import project.dao.impl.UserDaoImpl;
import project.database.DatabaseConnection;
import project.model.Relationship;

public class ResponseRequest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String decide = request.getParameter("bool");
        String userId = request.getParameter("id");
        String friendName = request.getParameter("friend");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        RelationshipDaoImpl relationshipDaoImpl = new RelationshipDaoImpl();
        System.out.println(decide+"  "+userId+"  "+friendName);
        if (decide.equals("false")) {
            relationshipDaoImpl.deleteARequest(new Relationship("1", userId, userDaoImpl.getInfoByUsername(friendName).getId()));
        } else {
            relationshipDaoImpl.submitRelationship(new Relationship("1", userId, userDaoImpl.getInfoByUsername(friendName).getId()));
        }
        /*try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String bool = request.getParameter("bool");
            String friend = request.getParameter("friend");
            String userid = request.getParameter("id");
            if (bool.equals("true")) {
                String sql=
                        "USE TPROJECT;\n" +
                        "DECLARE @FRIENDID AS NUMERIC(18,0);\n" +
                        "SET @FRIENDID = (SELECT id FROM USERINF WHERE username = '"+friend+"');\n" +
                        "SELECT @FRIENDID\n" +
                        "IF NOT EXISTS (SELECT * FROM RELATIONSHIP WHERE id = '"+userid+"' AND friendid = @FRIENDID)\n" +
                        "	INSERT INTO RELATIONSHIP VALUES\n" +
                        "		('"+userid+"',@FRIENDID,GETDATE()),\n" +
                        "		(@FRIENDID,'"+userid+"',GETDATE());\n" +
                        "DELETE FROM FRIENDREQUEST WHERE id = '"+userid+"' \n" +
                        "AND friendid = @FRIENDID;";
                state.executeUpdate(sql);
            }
            else {
                String sql=
                        "USE TPROJECT;\n" +
                        "DECLARE @FRIENDID AS NUMERIC(18,0);\n" +
                        "SET @FRIENDID = (SELECT id FROM USERINF WHERE username = '"+friend+"');\n" +
                        "DELETE FROM FRIENDREQUEST WHERE id = '"+userid+"' \n" +
                        "AND friendid = @FRIENDID;";
                state.executeUpdate(sql);
            }
            state.close(); conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ResponseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ResponseRequest.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
