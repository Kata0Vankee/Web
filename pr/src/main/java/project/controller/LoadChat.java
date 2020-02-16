package project.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.database.DatabaseConnection;

public class LoadChat extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            JsonArray chatsentence = new JsonArray();
            String userid = request.getParameter("userid");
            String friend = request.getParameter("friend");
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String sql = 
                    "use PROJECT1;\n" +
                    "DECLARE @CHATID AS NUMERIC(18,0);\n" +
                    "DECLARE @FRIENDID AS NUMERIC(18,0);\n" +
                    "SET @FRIENDID = (SELECT ID FROM USERINF WHERE USERNAME = '"+friend+"');\n" +
                    "SET @CHATID = (SELECT ID FROM CONVERSATIONS \n" +
                    "WHERE (USERID1 = '"+userid+"' AND USERID2 = @FRIENDID)\n" +
                    "	OR (USERID1 = @FRIENDID AND USERID2 = '"+userid+"'));\n" +
                    "IF @CHATID IS NOT NULL\n" +
                    "	SELECT * FROM CHATSENTENCE WHERE ID = @CHATID\n" +
                    "	ORDER BY TIMEVALUE DESC;";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                String senderid = rs.getString("SENDERID");
                String content = rs.getString("BODY");
                String time = rs.getString("TIMEVALUE");
                JsonObject obj = new JsonObject();
                obj.addProperty("senderid", senderid);
                obj.addProperty("content", content);
                obj.addProperty("time", time);
                chatsentence.add(obj);
            }
            System.out.println(chatsentence.toString());
            response.getWriter().print(chatsentence.toString());
        } catch (SQLException ex) {
            Logger.getLogger(LoadChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
