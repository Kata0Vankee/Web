package project.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.impl.GroupDaoImpl;
import project.dao.impl.UserDaoImpl;
import project.model.Group;
import project.model.User;

public class LoadGroup extends HttpServlet {   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        JsonArray groupInfo = new JsonArray();
        ArrayList<String> listTemp = groupDaoImpl.takeGroupIdListJoined(id);
        while (!listTemp.isEmpty()) {
            String thisGroupId = listTemp.remove(0);
            Group thisGroup = groupDaoImpl.getGroupInfoById(thisGroupId);
            JsonObject obj = new JsonObject();
            obj.addProperty("groupId", thisGroupId);
            obj.addProperty("groupName", thisGroup.getGroupName());
            String listUsername = "";
            ArrayList<User> listUserInThisGroup = thisGroup.getMemberList();
            while (!listUserInThisGroup.isEmpty()) {
                listUsername += listUserInThisGroup.remove(0).getUsername()+" ";
            }
            obj.addProperty("groupListUsername", listUsername);
            groupInfo.add(obj);
        }
        System.out.println(groupInfo.toString());
        response.getWriter().write(groupInfo.toString());
    }
}
