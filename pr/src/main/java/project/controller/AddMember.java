package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.impl.GroupDaoImpl;
import project.dao.impl.UserDaoImpl;

public class AddMember extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String senderId = request.getParameter("senderId");
        String memberId = userDaoImpl.getInfoByUsername(request.getParameter("memberInfo")).getId();
        String groupId = groupDaoImpl.getGroupInfoByName(request.getParameter("groupName")).getId();
        if (groupDaoImpl.isIn(groupId, memberId)){
            String result = "<script>alert(\"This user have already been in this group!!!\");</script>";
            response.getWriter().write(result);
            System.out.println(result);
        } else {
            String result = "<script>alert(\"Add successfully!!!\");</script>";
            groupDaoImpl.addMember(groupId, memberId);
            response.getWriter().write(result);
            System.out.println(result);
        }
    }
}
