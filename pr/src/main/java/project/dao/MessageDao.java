package project.dao;

import java.util.ArrayList;
import project.model.Group;
import project.model.Message;

public interface MessageDao {
    public void saveMessageGroup(Message message);
    public ArrayList<Message> loadMessageGroup(Group group);
    public ArrayList<Message> loadMessageGroup(String groupId);
}
