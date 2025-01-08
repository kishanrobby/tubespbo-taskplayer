package com.taskplayer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dashboard")
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int points = 0;

    @Column(nullable = false)
    private int taskPending = 0;

    @Column(nullable = false)
    private int taskFinished = 0;

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTaskPending() {
        return taskPending;
    }

    public void setTaskPending(int taskPending) {
        this.taskPending = taskPending;
    }

    public int getTaskFinished() {
        return taskFinished;
    }

    public void setTaskFinished(int taskFinished) {
        this.taskFinished = taskFinished;
    }

    public void incrementPoints(int points) {
        this.points += points;
    }

    public void incrementTaskPending() {
        this.taskPending++;
    }

    public void incrementTaskFinished() {
        this.taskFinished++;
        if (this.taskPending > 0) {
            this.taskPending--;
        }
    }
}