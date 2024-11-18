package kalorienzaehler.backend.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Diese Klasse repräsentiert die Nährwertinformationen für eine Gruppe von Produkten.
 * 
 * Sie speichert den Gesamtgehalt an Fett, Kohlenhydraten und Proteinen, der sich aus mehreren Produkten ergibt.
 * Diese Informationen können genutzt werden, um die Nährwertzusammensetzung von Mahlzeiten oder anderen Sammlungen von Produkten zu berechnen.
 */
@Entity
public class NutritionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutritionalInfoId; // Eindeutige Kennung für diese Nährwertinformation.

    private double fat; // Gesamter Fettgehalt in Gramm.
    private double carbohydrates; // Gesamter Kohlenhydratgehalt in Gramm.
    private double proteins; // Gesamter Proteingehalt in Gramm.

    /**
     * Standard-Konstruktor, notwendig für die Verwendung in Frameworks wie JPA.
     */
    public NutritionalInfo() {
    }

    /**
     * Berechnet die Gesamtnährwerte basierend auf einer Liste von Produkten.
     * 
     * Diese Methode summiert die Fett-, Kohlenhydrat- und Proteinwerte aller Produkte in der Liste.
     * 
     * Beispiel:
     * - Produkt 1: 10g Fett, 20g Kohlenhydrate, 5g Proteine
     * - Produkt 2: 5g Fett, 15g Kohlenhydrate, 10g Proteine
     * - Ergebnis: 15g Fett, 35g Kohlenhydrate, 15g Proteine
     * 
     * @param products Eine Liste von Produkten, aus denen die Gesamtnährwerte berechnet werden sollen.
     */
    public void calculateTotalNutrients(List<Product> products) {
        this.fat = products.stream().mapToDouble(Product::getFat).sum();
        this.carbohydrates = products.stream().mapToDouble(Product::getCarbohydrates).sum();
        this.proteins = products.stream().mapToDouble(Product::getProteins).sum();
    }

    // Getter und Setter

    /**
     * Gibt die eindeutige Kennung für diese Nährwertinformation zurück.
     */
    public Long getNutritionalInfoId() {
        return nutritionalInfoId;
    }

    public void setNutritionalInfoId(Long nutritionalInfoId) {
        this.nutritionalInfoId = nutritionalInfoId;
    }

    /**
     * Gibt den Gesamtfettgehalt zurück (in Gramm).
     */
    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Gibt den Gesamtkohlenhydratgehalt zurück (in Gramm).
     */
    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    /**
     * Gibt den Gesamtproteingehalt zurück (in Gramm).
     */
    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }
}
