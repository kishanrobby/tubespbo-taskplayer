/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;

public class Daily extends Task {
    private String frequency;

    public Daily() {
        this.table = "dailies";
    }

    public Daily(int id, String title, String description, String frequency) {
        super(id, title, description);
        this.frequency = frequency;
    }

    @Override
    public String getType() {
        return "Daily";
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public Daily toModel(ResultSet rs) {
        try {
            return new Daily(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("frequency")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

