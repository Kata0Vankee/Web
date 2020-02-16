
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
import project.dao.impl.GroupDaoImpl;
import project.dao.impl.UserDaoImpl;


public class CreateGroup extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String id = request.getParameter("id");
            String groupName = request.getParameter("groupName");
            GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            if (groupDaoImpl.getGroupInfoByName(groupName).getGroupName() == "") {
                groupDaoImpl.createGroup(userDaoImpl.getInfoByID(id), groupName);
                String result = "<script>alert(\"Create group successfully!!\");</script>";
                response.getWriter().write(result);
                System.out.println(result);                
            } else {
                String result = "<script>alert(\"DON'T DO THAT!!\");</script>";
                response.getWriter().write(result);
                System.out.println(result);
            }
        }    
    }