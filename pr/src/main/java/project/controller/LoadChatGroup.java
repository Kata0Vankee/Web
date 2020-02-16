package project.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.impl.GroupDaoImpl;
import project.dao.impl.MessageDaoImpl;
import project.dao.impl.UserDaoImpl;
import project.model.Message;

public class LoadChatGroup extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String groupName  = request.getParameter("groupName");
        System.out.println("Group load : "+groupName);
        GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String groupId = groupDaoImpl.getGroupInfoByName(groupName).getId();
        /*String sql = 
                "USE PROJECT1;\n" +
                "SELECT * FROM ROOMMESSAGE WHERE ROOMID = '"+groupId+"'\n" +
                "ORDER BY TIMEVALUE DESC;";*/
        MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
        ArrayList<Message> listMessageTemp =  messageDaoImpl.loadMessageGroup(groupId);
        JsonArray listMessage = new JsonArray();
        while (!listMessageTemp.isEmpty()) {
            Message temp = listMessageTemp.remove(0);
            JsonObject obj = new JsonObject();
            obj.addProperty("senderName", userDaoImpl.getInfoByID(temp.getFrom()).getUsername());
            obj.addProperty("content", temp.getContent());
            obj.addProperty("time", temp.getTime());
            obj.addProperty("to", groupDaoImpl.getGroupInfoById(temp.getTo()).getGroupName());
            listMessage.add(obj);
        }
        System.out.println(listMessage.toString());
        response.getWriter().write(listMessage.toString());
    }
}
