package kalorienzaehler.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CaloricIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intakeId;
    private LocalDate date;
    private double totalCalories;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Meal> meals = new ArrayList<>();

    public CaloricIntake() {
        this.date = LocalDate.now();
    }

    // Methode zum Hinzufügen einer Mahlzeit und Aktualisieren der Kalorien
    public void addMeal(Meal meal) {
        meals.add(meal);
        totalCalories += meal.getProducts().stream()
                             .mapToDouble(Product::calculateCalories)
                             .sum();
    }

    public double calculateTotalCalories() {
        return meals.stream()
                    .flatMap(meal -> meal.getProducts().stream())
                    .mapToDouble(Product::calculateCalories)
                    .sum();
    }

    public void resetDailyIntake() {
        meals.clear();
        totalCalories = 0;
    }

    public Long getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(Long intakeId) {
        this.intakeId = intakeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
