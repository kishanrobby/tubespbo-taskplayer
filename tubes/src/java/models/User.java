package models;

import java.sql.ResultSet;

public class User extends Model<User> {
    private int id;
    private String username;
    private String email;
    private String password;

    public User() {
        this.table = "user";
        this.primaryKey = "id";
    }
    
    public User(int id, String username, String email, String password) {
        this.table = "user";
        this.primaryKey = "id";
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    User toModel(ResultSet rs) {
        try {
            return new User (
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
            );
        } catch (Exception e) {
            setMessage(e.getMessage());
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
