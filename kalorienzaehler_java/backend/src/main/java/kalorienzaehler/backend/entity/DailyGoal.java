package kalorienzaehler.backend.entity;

import jakarta.persistence.*;

/**
 * Repräsentiert das tägliche Kalorienziel einer Person.
 * 
 * Diese Klasse dient zur Verwaltung des Kalorienziels und der bereits konsumierten Kalorien für einen Tag.
 * Sie ermöglicht die Aktualisierung der konsumierten Kalorien und die Anpassung des täglichen Ziels.
 */
@Entity
public class DailyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId; // Eindeutige Kennung für dieses Kalorienziel.

    private double dailyCalorieGoal; // Das definierte Kalorienziel für den Tag.
    private double totalCalories; // Die Summe der bereits konsumierten Kalorien am aktuellen Tag.

    /**
     * Standard-Konstruktor, notwendig für die Verwendung in Frameworks wie JPA.
     */
    public DailyGoal() {
    }

    /**
     * Konstruktor zur Erstellung eines täglichen Kalorienziels.
     * 
     * Das Ziel wird auf den angegebenen Wert gesetzt, während die konsumierten Kalorien initial auf 0 gesetzt werden.
     * 
     * @param dailyCalorieGoal Das Kalorienziel für den Tag (z. B. 2000 kcal).
     */
    public DailyGoal(double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
        this.totalCalories = 0;
    }

    /**
     * Setzt ein neues tägliches Kalorienziel.
     * 
     * Beispiel: Aktualisieren des Ziels von 2000 kcal auf 2500 kcal.
     * 
     * @param newGoal Das neue Kalorienziel.
     */
    public void setDailyGoal(double newGoal) {
        this.dailyCalorieGoal = newGoal;
    }

    /**
     * Aktualisiert die konsumierten Kalorien, indem die angegebene Menge hinzugefügt wird.
     * 
     * Beispiel: Hinzufügen von 500 kcal zu den bereits konsumierten Kalorien.
     * 
     * @param calories Die Anzahl der hinzuzufügenden Kalorien.
     */
    public void updateTotalCalories(double calories) {
        this.totalCalories += calories;
    }

    // Getter und Setter

    /**
     * Gibt die eindeutige Kennung für dieses Kalorienziel zurück.
     */
    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    /**
     * Gibt das aktuelle tägliche Kalorienziel zurück.
     */
    public double getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    public void setDailyCalorieGoal(double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    /**
     * Gibt die Summe der bisher konsumierten Kalorien zurück.
     */
    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
}
