package models;

import java.sql.ResultSet;

public class Dashboard extends Model<Dashboard> {

//    private String taskStats;
    private int userId;
    private int points;
    private int taskPending;
    private int taskFinished;

    public Dashboard(){
        this.table = "user";
        this.primaryKey = "id";
    };

    public Dashboard(int userId, int points, int taskPending, int taskFinished) {
        this.table = "user";
        this.primaryKey = "id";
//        this.userId = userId;
        this.points = points;
        this.taskPending = taskPending;
        this.taskFinished = taskFinished;
    }

    @Override
    Dashboard toModel(ResultSet rs) {
        try {
            return new Dashboard (
                rs.getInt("userId"),
                rs.getInt("points"),
                rs.getInt("taskPending"),
                rs.getInt("taskPending")
                );
        } catch (Exception e) {
            setMessage(e.getMessage());
        }
        return null;
    }

    public void setUserId(int ID){
        this.userId = ID;
    }
    public void setPoints(int points){
        this.points = points;
    }
//    public void setTaskStats(String taskStats){
//        this.taskStats = taskStats;
//    }
    public void setTaskPending(int taskPending) {
        this.taskPending = taskPending;
    }
    public void setTaskFinished(int taskFinished) {
        this.taskFinished = taskFinished;
    }
    
    public int getUserId(){
        return this.userId;
    }
    public int getPoints(){
        return this.points;
    }
//    public int getTaskStats(){
//        return this.taskStats;
//    }
    public int getTaskPending() {
        return taskPending;
    }
    public int getTaskFinished() {
        return taskFinished;
    }

    public void incrementTaskPending(int taskPending){
        taskPending += 1;
    }
    public void decrementTaskPending(int taskPending){
        taskPending -= 1;
        if (taskPending < 0){
            taskPending = 0;
        }
    }
    public void incrementTaskFinish(int taskFinished){
        taskFinished += 1;
    }
}