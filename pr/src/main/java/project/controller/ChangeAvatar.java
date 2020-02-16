package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.impl.UserDaoImpl;

public class ChangeAvatar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String avatar = request.getParameter("avatar");
        System.out.println(avatar);
        String id = request.getParameter("id");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        userDaoImpl.saveAvatar(id, avatar);
    }
}
