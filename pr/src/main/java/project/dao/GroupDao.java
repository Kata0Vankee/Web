package project.dao;

import java.util.ArrayList;
import project.model.Group;
import project.model.User;

public interface GroupDao {
    public Group getGroupInfoById(String id);
    public Group getGroupInfoByName(String groupName);
    public void createGroup(User user, String groupName);
    public boolean isIn(String groupId, String userId);
    public void addMember(String groupId, String userId);
    public User findCreater(String groupId);
    public ArrayList<String> takeGroupIdListJoined(String userId);
    public void saveAvatar(String groupId, String avatar);
    public String getAvatar(String groupID);
}
