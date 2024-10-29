package kalorienzaehler.backend.entity;

import java.lang.annotation.Inherited;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.processing.Generated;

import jakarta.persistence.*;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;
    private String name;
    private double quantity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private NutritionalInfo nutritionalInfo;

    public Meal() {
    }

    public Meal(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
        this.nutritionalInfo = new NutritionalInfo();
    }

    public void addProduct(Product product) {
        products.add(product);
        calculateMealStats();  // Update nutritional info whenever a product is added
    }

    public void calculateMealStats() {
        nutritionalInfo.calculateTotalNutrients(products);
    }

    public double calculateCalories() {
        return products.stream()
                .mapToDouble(product -> product.getCalories() * product.getQuantity())
                .sum();
    }

    // Getter und Setter
    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public NutritionalInfo getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setNutritionalInfo(NutritionalInfo nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }
}
