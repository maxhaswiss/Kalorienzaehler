package kalorienzaehler.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert die tägliche Kalorienaufnahme.
 * 
 * Diese Klasse dient zur Verwaltung der Kalorien, die durch verschiedene Mahlzeiten an einem bestimmten Tag konsumiert werden.
 * Sie speichert die Liste der Mahlzeiten, die konsumierten Kalorien und das Datum der Aufnahme.
 */
@Entity
public class CaloricIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intakeId; // Eindeutige Kennung für diese Kalorienaufnahme.

    private LocalDate date; // Das Datum, an dem die Kalorienaufnahme stattgefunden hat.

    private double totalCalories; // Gesamtkalorien, die an diesem Tag konsumiert wurden.

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Meal> meals = new ArrayList<>(); // Liste der konsumierten Mahlzeiten.

    /**
     * Standard-Konstruktor.
     * 
     * Initialisiert das Datum der Kalorienaufnahme mit dem aktuellen Datum.
     */
    public CaloricIntake() {
        this.date = LocalDate.now();
    }

    /**
     * Fügt eine neue Mahlzeit zur Kalorienaufnahme hinzu und aktualisiert die Gesamtkalorien.
     * 
     * Beispiel:
     * - Neue Mahlzeit enthält ein Produkt mit 500 Kalorien.
     * - Die Gesamtkalorien werden automatisch um 500 erhöht.
     * 
     * @param meal Die hinzuzufügende Mahlzeit.
     */
    public void addMeal(Meal meal) {
        meals.add(meal);
        totalCalories += meal.getProducts().stream()
                             .mapToDouble(Product::calculateCalories)
                             .sum();
    }

    /**
     * Berechnet die Gesamtkalorien basierend auf allen Mahlzeiten.
     * 
     * Diese Methode summiert die Kalorien aller Produkte in allen Mahlzeiten.
     * 
     * @return Die berechneten Gesamtkalorien.
     */
    public double calculateTotalCalories() {
        return meals.stream()
                    .flatMap(meal -> meal.getProducts().stream())
                    .mapToDouble(Product::calculateCalories)
                    .sum();
    }

    /**
     * Setzt die Kalorienaufnahme für den Tag zurück.
     * 
     * Entfernt alle Mahlzeiten und setzt die Gesamtkalorien auf 0.
     */
    public void resetDailyIntake() {
        meals.clear();
        totalCalories = 0;
    }

    // Getter und Setter

    /**
     * Gibt die eindeutige Kennung dieser Kalorienaufnahme zurück.
     */
    public Long getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(Long intakeId) {
        this.intakeId = intakeId;
    }

    /**
     * Gibt das Datum der Kalorienaufnahme zurück.
     */
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gibt die Gesamtkalorien zurück, die an diesem Tag konsumiert wurden.
     */
    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    /**
     * Gibt die Liste der konsumierten Mahlzeiten zurück.
     */
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
