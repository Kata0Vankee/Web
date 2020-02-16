package project.model;

public class User {
    private String username;
    private String password;
    private String email;
    private String id;
    private String firstname;
    private String lastname;
    public User(){};
    public User(String i, String u, String p, String f, String l, String e){
        id = i;
        username = u;
        password = p;
        firstname = f;
        lastname = l;
        email = e;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
}
