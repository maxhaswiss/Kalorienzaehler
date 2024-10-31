package kalorienzaehler.backend.entity;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private double calories;    
    private double quantity;    
    private double fat;         
    private double carbohydrates; 
    private double proteins;    

    public Product() {
    }

    public Product(String name, double calories, double quantity, double fat, double carbohydrates, double proteins) {
        this.name = name;
        this.calories = calories;
        this.quantity = quantity;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        calculateCalories(); 
    }

    public double calculateCalories() {
        this.calories = (this.proteins * 4) + (this.carbohydrates * 4) + (this.fat * 9);
        return this.calories;
    }
    

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

    public double getCalories() { 
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getQuantity() { 
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getFat() { 
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
        calculateCalories();
    }

    public double getCarbohydrates() { 
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
        calculateCalories();
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
        calculateCalories();
    }
}
