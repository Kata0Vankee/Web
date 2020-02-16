package project.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import project.model.UserBean;
import project.database.DatabaseConnection;

public class LoadFriends extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter printWriter = response.getWriter();
            JSONArray friendsList = new JSONArray();
            UserBean user = new UserBean();
            ArrayList<String> listFriend = new ArrayList<String>();
            String id = request.getParameter("id");
            System.out.println(id);
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            
            String sql = "USE PROJECT1; \n" +
                            "SELECT USERNAME FROM USERINF\n" +
                            "WHERE ID IN\n" +
                            "(SELECT FRIENDID FROM USERINF, RELATIONSHIP\n" +
                            "WHERE USERINF.ID = RELATIONSHIP.ID AND RELATIONSHIP.ID = '"+id+"');";
            
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                String friend = rs.getString("USERNAME");
                listFriend.add(friend);
                friendsList.put(friend);             
            }
            user.setUserFriends(listFriend); 
            printWriter.write(friendsList.toString());
    }   catch (SQLException ex) {
            Logger.getLogger(LoadFriends.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
