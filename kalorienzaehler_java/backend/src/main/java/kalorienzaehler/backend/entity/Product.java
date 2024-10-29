package main.java.kalorienzaehler.backend.entity;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private double calories;    // Hinzugefügt
    private double quantity;    // Hinzugefügt
    private double fat;         // Hinzugefügt
    private double carbohydrates; // Hinzugefügt
    private double proteins;    // Hinzugefügt

    public Product() {
    }

    public Product(String name, double calories, double quantity, double fat, double carbohydrates, double proteins) {
        this.name = name;
        this.calories = calories;
        this.quantity = quantity;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
    }

    // Getter und Setter für alle Felder
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() { // Hinzugefügt
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getQuantity() { // Hinzugefügt
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getFat() { // Hinzugefügt
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrates() { // Hinzugefügt
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProteins() { // Hinzugefügt
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }
}
