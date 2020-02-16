package project.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.JsonArray;
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
import project.dao.UserDao;
import project.dao.impl.RelationshipDaoImpl;
import project.dao.impl.UserDaoImpl;
import project.database.DatabaseConnection;
import project.model.Relationship;
import project.model.User;

/**
 *
 * @author qrona
 */
public class CheckFriendRequest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        RelationshipDaoImpl rli = new RelationshipDaoImpl();
        JsonArray requestList = new JsonArray();
        if (!rli.haveRequest(id)){
            requestList.add("no");
            String write = "<script>alert(\"You have no request\");</script>";
            requestList.add(write);
        } else {
            requestList.add("yes");
            ArrayList<String> requestListTemp = rli.getRelationshipRequestList(id);
            while (!requestListTemp.isEmpty()){
                String requestUserId = requestListTemp.remove(0);
                UserDaoImpl userDaoImpl = new UserDaoImpl();                
                requestList.add(userDaoImpl.getInfoByID(requestUserId).getUsername());
            }            
        }
        response.getWriter().write(requestList.toString());
        System.out.println(requestList.toString());
        /*try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            JsonArray requestList = new JsonArray();
            String id = request.getParameter("id");
            String sql = "USE TPROJECT;\n" +
                        "SELECT username FROM USERINF\n" +
                        "WHERE id IN (SELECT friendid FROM FRIENDREQUEST WHERE id = '"+id+"');";
            ResultSet rs = state.executeQuery(sql);
            boolean value = rs.next();
            if (!value) {
                System.out.println("k co friend" + value);
                requestList.add("no");
                String write = "<script>alert(\"You have no request\");</script>";
                requestList.add(write);
            }
            else {
                requestList.add("yes");
                while (value) {
                    String friend = rs.getString("username");
                    System.out.println("ten ban :" + friend);
                    requestList.add(friend);
                    value = rs.next();
                }                                               
            }
            System.out.println(requestList.toString());
            response.getWriter().write(requestList.toString());
        } catch (SQLException ex) {
            Logger.getLogger(CheckFriendRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckFriendRequest.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
