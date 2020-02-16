package project.model;

public class Relationship {
    private String id;
    private String usernameOrId1;
    private String usernameOrId2;
    public Relationship(){};
    public Relationship(String i, String id1, String id2) {
        id = i;
        usernameOrId1 = id1;
        usernameOrId2 = id2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameOrId1() {
        return usernameOrId1;
    }

    public void setUsernameOrId1(String usernameOrId1) {
        this.usernameOrId1 = usernameOrId1;
    }

    public String getUsernameOrId2() {
        return usernameOrId2;
    }

    public void setUsernameOrId2(String usernameOrId2) {
        this.usernameOrId2 = usernameOrId2;
    }

    
}
