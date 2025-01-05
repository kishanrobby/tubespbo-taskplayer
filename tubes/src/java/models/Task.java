/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mdwivaaryaerlangga
 */

import java.sql.ResultSet;

public abstract class Task extends User{
    private int id;
    private String title;
    private String description;

    protected String table;

    public Task() {}

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String getType();

    public abstract Task toModel(ResultSet rs);
    
    public boolean save() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "username", "password")) {
            String sql = id == 0
                ? "INSERT INTO " + table + " (title, description) VALUES (?, ?)"
                : "UPDATE " + table + " SET title = ?, description = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, title);
                stmt.setString(2, description);
                if (id != 0) {
                    stmt.setInt(3, id);
                }
                stmt.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

