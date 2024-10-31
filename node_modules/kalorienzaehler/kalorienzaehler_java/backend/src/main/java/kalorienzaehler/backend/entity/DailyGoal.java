package kalorienzaehler.backend.entity;


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

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public double getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    public void setDailyCalorieGoal(double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    
}