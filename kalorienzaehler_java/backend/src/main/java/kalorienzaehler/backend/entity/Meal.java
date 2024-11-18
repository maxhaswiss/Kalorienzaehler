package kalorienzaehler.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Meal-Klasse repräsentiert eine Mahlzeit mit ihren Produkten, Nährwerten und Gesamtkalorien.
 * 
 * Funktionen:
 * - Verwaltung der Produkte, die Teil der Mahlzeit sind.
 * - Berechnung der Gesamtkalorien und Nährwerte basierend auf den enthaltenen Produkten.
 */
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId; // Eindeutige Kennung für die Mahlzeit.

    private String name; // Name der Mahlzeit, z. B. "Frühstück".
    private double quantity; // Mengenfaktor für die Mahlzeit (z. B. 2x Frühstück).
    private double calories; // Gesamtkalorien der Mahlzeit.

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>(); // Liste der Produkte in der Mahlzeit.

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private NutritionalInfo nutritionalInfo; // Nährwertinformationen der Mahlzeit.

    /**
     * Standard-Konstruktor.
     */
    public Meal() {
    }

    /**
     * Konstruktor zur Erstellung einer Mahlzeit mit Name und Menge.
     * 
     * @param name     Name der Mahlzeit.
     * @param quantity Mengenfaktor der Mahlzeit.
     */
    public Meal(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
        this.nutritionalInfo = new NutritionalInfo();
    }

    /**
     * Fügt ein Produkt zur Mahlzeit hinzu und aktualisiert die Mahlzeitstatistiken.
     * 
     * Wenn die Kalorien des Produkts nicht berechnet wurden, wird die Berechnung automatisch durchgeführt.
     * 
     * @param product Das hinzuzufügende Produkt.
     */
    public void addProduct(Product product) {
        if (product.getCalories() == 0) {
            product.calculateCalories(); // Berechnet die Kalorien, falls sie noch nicht gesetzt sind.
        }

        products.add(product); // Produkt zur Liste hinzufügen.
        calculateMealStats(); // Statistiken der Mahlzeit aktualisieren.
    }

    /**
     * Berechnet die Gesamtkalorien und Nährwerte der Mahlzeit.
     * 
     * - Die Nährwerte werden basierend auf allen Produkten in der Mahlzeit berechnet.
     * - Die Gesamtkalorien werden unter Berücksichtigung der Menge berechnet.
     */
    public void calculateMealStats() {
        nutritionalInfo.calculateTotalNutrients(products); // Berechnet die Nährwerte.

        System.out.println("Produkte: " + products);
        System.out.println("Berechnung der Kalorien für jedes Produkt:");

        this.calories = products.stream()
                .mapToDouble(product -> {
                    double calories = product.getCalories() * (product.getQuantity() / 100);
                    System.out.println("Produkt: " + product.getName() + ", Kalorien: " + calories);
                    return calories;
                })
                .sum() * this.quantity;

        System.out.println("Gesamte Kalorien: " + this.calories);
    }

    // Getter und Setter

    /**
     * Gibt die eindeutige Kennung der Mahlzeit zurück.
     */
    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    /**
     * Gibt den Namen der Mahlzeit zurück.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt den Mengenfaktor der Mahlzeit zurück.
     */
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Gibt die Gesamtkalorien der Mahlzeit zurück.
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Gibt die Liste der Produkte in der Mahlzeit zurück.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Gibt die Nährwertinformationen der Mahlzeit zurück.
     */
    public NutritionalInfo getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setNutritionalInfo(NutritionalInfo nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }
}
