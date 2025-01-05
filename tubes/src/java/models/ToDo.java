/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;

public class ToDo extends Task {
    private String dueDate;

    public ToDo() {
        this.table = "todos";
    }

    public ToDo(int id, String title, String description, String dueDate) {
        super(id, title, description);
        this.dueDate = dueDate;
    }

    @Override
    public String getType() {
        return "ToDo";
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public ToDo toModel(ResultSet rs) {
        try {
            return new ToDo(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("due_date")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
