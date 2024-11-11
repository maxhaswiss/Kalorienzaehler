package kalorienzaehler.backend.service;

import kalorienzaehler.backend.entity.Meal;
import kalorienzaehler.backend.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    public Meal updateMeal(Long id, Meal meal) {
        meal.setMealId(id);
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    public void deleteAllMeals() {
        mealRepository.deleteAll();
    }    
}
