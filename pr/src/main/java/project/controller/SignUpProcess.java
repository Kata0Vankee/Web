package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import project.database.DatabaseConnection;
import project.model.User;

public class SignUpProcess extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            User user = new User("1",username,password,firstname,lastname,email);
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            userDaoImpl.insertUser(user);
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
    }
}
