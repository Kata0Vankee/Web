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

public class LoadGroupsAvatar extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        JsonArray listGroupsAvatar = new JsonArray();
        GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
        ArrayList<String> listGroups = groupDaoImpl.takeGroupIdListJoined(id);
        while (!listGroups.isEmpty()){
            String groupId = listGroups.remove(0);
            JsonObject obj = new JsonObject();
            obj.addProperty("groupName",groupDaoImpl.getGroupInfoById(groupId).getGroupName());
            obj.addProperty("avatar", groupDaoImpl.getAvatar(groupId));
            System.out.println(obj.get("groupName") +" "+ obj.get("avatar") );
            listGroupsAvatar.add(obj);
        }
        System.out.println(listGroupsAvatar.toString());
        response.getWriter().write(listGroupsAvatar.toString());
    }
}
