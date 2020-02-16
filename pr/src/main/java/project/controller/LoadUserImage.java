/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.impl.UserDaoImpl;

/**
 *
 * @author qrona
 */
public class LoadUserImage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String avatar = userDaoImpl.getAvatar(id);
        response.getWriter().write(avatar.toString());
        System.out.println(avatar);
    }
}
