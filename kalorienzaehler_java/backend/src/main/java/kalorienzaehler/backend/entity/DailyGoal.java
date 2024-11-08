package main.java.kalorienzaehler.backend.entity;


import jakarta.persistence.*;

@Entity
public class DailyGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;
    private double dailyCalorieGoal;
    private double totalCalories;

    public DailyGoal() {
    }

    public DailyGoal(double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
        this.totalCalories = 0;
    }

    public void setDailyGoal(double newGoal) {
        this.dailyCalorieGoal = newGoal;
    }

    public void updateTotalCalories(double calories) {
        this.totalCalories += calories;
    }

    // Getter and Setter methods
    // ...
}