package project.model;

import java.util.ArrayList;

public class Group {
    private String id;
    private String groupName;
    private ArrayList<User> memberList;
    public Group(){};
    public Group(String i, String n, ArrayList<User> m){
        id=i;
        groupName = n;
        memberList = m;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<User> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<User> memberList) {
        this.memberList = memberList;
    }
    
}
