package kalorienzaehler.backend.entity;

import jakarta.persistence.*;

/**
 * Repräsentiert ein Produkt, das in einer Mahlzeit verwendet werden kann.
 * 
 * Diese Klasse beschreibt die wesentlichen Eigenschaften eines Produkts, 
 * wie dessen Name, Nährwertangaben und Menge. Zusätzlich berechnet sie 
 * automatisch die Kalorien basierend auf Fett, Kohlenhydraten und Proteinen.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; // Eindeutige Kennung für das Produkt.

    private String name; // Name des Produkts, z. B. "Apfel".
    private double calories; // Kaloriengehalt des Produkts (pro definierter Menge).
    private double quantity; // Menge des Produkts in einer spezifischen Einheit (z. B. Gramm).
    private double fat; // Fettgehalt in Gramm.
    private double carbohydrates; // Kohlenhydratgehalt in Gramm.
    private double proteins; // Proteingehalt in Gramm.

    /**
     * Standard-Konstruktor, notwendig für die Verwendung in Frameworks wie JPA.
     */
    public Product() {
    }

    /**
     * Konstruktor zur Erstellung eines Produkts mit allen relevanten Eigenschaften.
     * 
     * @param name          Name des Produkts.
     * @param calories      (Optional) Kaloriengehalt. Wird automatisch berechnet, falls nicht gesetzt.
     * @param quantity      Menge des Produkts.
     * @param fat           Fettgehalt in Gramm.
     * @param carbohydrates Kohlenhydratgehalt in Gramm.
     * @param proteins      Proteingehalt in Gramm.
     */
    public Product(String name, double calories, double quantity, double fat, double carbohydrates, double proteins) {
        this.name = name;
        this.calories = calories;
        this.quantity = quantity;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        calculateCalories(); // Berechnet die Kalorien basierend auf Nährwerten.
    }

    /**
     * Berechnet die Kalorien basierend auf den Nährwerten.
     * 
     * Formel:
     * - Proteine: 4 Kalorien pro Gramm
     * - Kohlenhydrate: 4 Kalorien pro Gramm
     * - Fett: 9 Kalorien pro Gramm
     * 
     * @return Die berechneten Kalorien.
     */
    public double calculateCalories() {
        this.calories = (this.proteins * 4) + (this.carbohydrates * 4) + (this.fat * 9);
        return this.calories;
    }

    // Getter und Setter mit automatischer Neuberechnung der Kalorien, falls nötig.

    /**
     * Gibt die eindeutige Kennung des Produkts zurück.
     */
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * Gibt den Namen des Produkts zurück (z. B. "Apfel").
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt den Kaloriengehalt des Produkts zurück.
     */
    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Gibt die Menge des Produkts zurück (z. B. in Gramm).
     */
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Gibt den Fettgehalt des Produkts zurück (in Gramm).
     */
    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
        calculateCalories(); // Aktualisiert die Kalorien nach Änderung.
    }

    /**
     * Gibt den Kohlenhydratgehalt des Produkts zurück (in Gramm).
     */
    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
        calculateCalories(); // Aktualisiert die Kalorien nach Änderung.
    }

    /**
     * Gibt den Proteingehalt des Produkts zurück (in Gramm).
     */
    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
        calculateCalories(); // Aktualisiert die Kalorien nach Änderung.
    }
}
