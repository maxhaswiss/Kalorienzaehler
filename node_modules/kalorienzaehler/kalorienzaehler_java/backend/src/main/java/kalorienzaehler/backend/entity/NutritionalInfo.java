package main.java.kalorienzaehler.backend.entity;

import jakarta.persistence.*;

@Entity
public class NutritionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutritionalInfoId;
    private double fat;
    private double carbohydrates;
    private double proteins;

    public NutritionalInfo() {
    }

    public void calculateTotalNutrients(List<Product> products) {
        this.fat = products.stream().mapToDouble(Product::getFat).sum();
        this.carbohydrates = products.stream().mapToDouble(Product::getCarbohydrates).sum();
        this.proteins = products.stream().mapToDouble(Product::getProteins).sum();
    }

    // Getter and Setter methods
    // ...
}