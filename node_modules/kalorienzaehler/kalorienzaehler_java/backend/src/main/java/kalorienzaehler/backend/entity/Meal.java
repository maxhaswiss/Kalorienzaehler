package kalorienzaehler.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;
    private String name;
    private double quantity;
    private double calories;  

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
        
        if (product.getCalories() == 0) {
            product.calculateCalories();
        }
        
        products.add(product);
        calculateMealStats();
    }
    

    public void calculateMealStats() {
        nutritionalInfo.calculateTotalNutrients(products);
    
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

    public List<Product> getProducts() {
        return products;
    }

    public NutritionalInfo getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setNutritionalInfo(NutritionalInfo nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }
}
