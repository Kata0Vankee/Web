package project.model;

import java.util.ArrayList;

public class UserBean {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private final ArrayList<String> userFriends = new ArrayList<String>();
    private final ArrayList<String> chatSentence = new ArrayList<String>();
    
    public void setUserFriends(ArrayList<String> userFriends){
        for (String friend : userFriends) {
            this.userFriends.add(friend);
        }
    }
    public ArrayList<String> getUserFriends() {
        return userFriends;
    }
    
    public void setChatSentence(ArrayList<String> chatSentence){
        for (String chat : chatSentence) {
            this.chatSentence.add(chat);
        }
    }
    public ArrayList<String> getChatSentence() {
        return chatSentence;
    }
    
    
}
