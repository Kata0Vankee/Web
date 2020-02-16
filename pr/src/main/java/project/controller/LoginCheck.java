package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import project.database.DatabaseConnection;

public class LoginCheck extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("dwdwdw");
        try {
            Connection conn = DatabaseConnection.initializeDatabase();
            Statement state = conn.createStatement();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] encode = md.digest();
            String encodePassword = new BigInteger(1, encode).toString(16);
            System.out.println(encodePassword);
            String sql = "USE PROJECT1; SELECT * FROM dbo.USERINF WHERE USERNAME ='"+username+"' and PASSWORD ='"+encodePassword+"';";
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs.getString("USERNAME")+"  "+rs.getString("ID"));
                request.setAttribute("username", rs.getString("USERNAME"));
                request.setAttribute("firstname", rs.getString("FIRSTNAME"));
                request.setAttribute("lastname", rs.getString("LASTNAME"));
                request.setAttribute("id", rs.getString("ID"));
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/homePage.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
