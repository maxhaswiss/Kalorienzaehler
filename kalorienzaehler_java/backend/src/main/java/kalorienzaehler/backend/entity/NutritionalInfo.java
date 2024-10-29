package kalorienzaehler.backend.entity;

import jakarta.persistence.*;
import java.util.List;


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

    public Long getNutritionalInfoId() {
        return nutritionalInfoId;
    }

    public void setNutritionalInfoId(Long nutritionalInfoId) {
        this.nutritionalInfoId = nutritionalInfoId;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    
    
}