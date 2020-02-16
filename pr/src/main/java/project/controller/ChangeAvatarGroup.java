package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.impl.GroupDaoImpl;

public class ChangeAvatarGroup extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String avatar = request.getParameter("avatar");
        String groupName = request.getParameter("groupName");
        GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
        groupDaoImpl.saveAvatar(groupDaoImpl.getGroupInfoByName(groupName).getId(), avatar);
    }
}
