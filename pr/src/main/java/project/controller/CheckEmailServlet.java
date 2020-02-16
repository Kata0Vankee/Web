
package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.impl.UserDaoImpl;


public class CheckEmailServlet extends HttpServlet {

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean value = userDaoImpl.isValidUsername(username);

        if (value) {
            response.getWriter().write("<span style=\"color: red\">Username already taken.</span>");
        } else {
            response.getWriter().write("<span style=\"color: green\">Username can be used.</span>");
        }
    }    
}