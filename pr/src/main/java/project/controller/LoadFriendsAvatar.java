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
import project.dao.impl.UserDaoImpl;

public class LoadFriendsAvatar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonArray listFriendsSend = new JsonArray();
        String id = request.getParameter("id");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        
        ArrayList<String> listFriends = userDaoImpl.getAllUsersList();
        while (!listFriends.isEmpty()){
            String friendId = listFriends.remove(0);
            JsonObject obj = new JsonObject();
            System.out.println(friendId);
            System.out.println(userDaoImpl.getAvatar(friendId));
            obj.addProperty("username",userDaoImpl.getInfoByID(friendId).getUsername());
            obj.addProperty("avatar", userDaoImpl.getAvatar(friendId));
            listFriendsSend.add(obj);
        }
        System.out.println(listFriendsSend.toString());
        response.getWriter().write(listFriendsSend.toString());
    }
}
