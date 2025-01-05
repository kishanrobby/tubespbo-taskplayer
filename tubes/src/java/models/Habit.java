package models;

import java.sql.ResultSet;

public class Habit extends Task {
    private String habitType;
    private int points;

    public Habit() {
        this.table = "habits";
    }

    public Habit(int id, String title, String description, String habitType, int points) {
        super(id, title, description);
        this.habitType = habitType;
        this.points = points;
    }

    @Override
    public String getType() {
        return "Habit";
    }

    public String getHabitType() {
        return habitType;
    }

    public void setHabitType(String habitType) {
        this.habitType = habitType;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Method to reward points for habit completion
     * 
     * @param completionPoints - Points to add upon completion
     */
    public void rewardPoints(int completionPoints) {
        this.points += completionPoints;
    }

    @Override
    public Habit toModel(ResultSet rs) {
        try {
            return new Habit(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("type"),
                rs.getInt("points")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
