package project.dao;

import java.util.ArrayList;
import project.model.User;

public interface UserDao {
    public User getInfoByID(String id);
    public User getInfoByUsername(String username);
    public ArrayList<String> getFriendsList(String userId);
    public ArrayList<String> getAllUsersList();
    public boolean isValidUsername(String username);
    public void insertUser(User user);
    public boolean isValidUSer(User user);
    public String getAvatar(String userId);
    public void saveAvatar(String userId, String avatar);
}
