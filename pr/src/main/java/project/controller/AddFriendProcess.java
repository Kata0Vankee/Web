package project.controller;


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
import project.dao.impl.UserDaoImpl;
import project.dao.impl.RelationshipDaoImpl;
import project.database.DatabaseConnection;
import project.model.Relationship;
import project.model.User;

public class AddFriendProcess extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String userId = request.getParameter("id");
            String friendInf = request.getParameter("friendInf");
            UserDaoImpl friendImpl = new UserDaoImpl();
            if (friendImpl.isValidUsername(friendInf)){
                RelationshipDaoImpl rli = new RelationshipDaoImpl();
                Relationship rl = new Relationship("1", userId, friendImpl.getInfoByUsername(friendInf).getId());
                if (rli.canCreateRelationship(rl)){
                    if (rli.isValidRelationship(rl)){
                        String result = "<script>alert(\"Befriend Already!!!\");</script>";
                        response.getWriter().write(result);
                        System.out.println(result);
                    } else {
                        String result = "<script>alert(\"Send request successfully!!!\");</script>";
                        rli.createRelationship(rl);
                        response.getWriter().write(result);
                        System.out.println(result);
                    }      
                } else {
                    String result = "<script>alert(\"Request has already sended!!!\");</script>";
                    response.getWriter().write(result);
                    System.out.println(result);
                } 
            } else {
                String write = "<script>alert(\"username does'nt exist!!!\");</script>";
                response.getWriter().write(write);
            }
    }

}
