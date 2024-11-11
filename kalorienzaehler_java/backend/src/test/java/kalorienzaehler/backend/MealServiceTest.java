package kalorienzaehler.backend;

import kalorienzaehler.backend.entity.Meal;
import kalorienzaehler.backend.entity.Product;
import kalorienzaehler.backend.service.MealService;
import kalorienzaehler.backend.repository.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    private Meal meal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Erstelle ein Produkt
        Product product = new Product("Banana", 0, 200, 0.3, 27, 1.2); 

        // Erstelle eine Mahlzeit
        meal = new Meal("Fruit Salad", 1);  
        // Produkt hinzuf�gen
        meal.addProduct(product);  
    }

    @Test
    void testCreateMealWithCaloriesCalculation() {
        // Vor der Speicherung der Mahlzeit die Kalorien berechnen
        meal.calculateMealStats();

        // �berpr�fe, ob die Kalorien korrekt berechnet wurden
        double expectedCalories = (100 * 231.0 / 100) ;
        
        // Teste, ob die berechneten Kalorien mit dem erwarteten Wert �bereinstimmen
        assertEquals(expectedCalories, meal.getCalories(), 0.1, "Die berechneten Kalorien stimmen nicht �berein.");
    }

    @Test
    void testCreateMeal() {
        // Mock f�r die Speicherung im Repository
        when(mealRepository.save(any(Meal.class))).thenReturn(meal);

        // Methode aufrufen, um die Mahlzeit zu speichern
        Meal createdMeal = mealService.createMeal(meal);

        // �berpr�fe, ob die gespeicherte Mahlzeit korrekt zur�ckgegeben wurde
        assertNotNull(createdMeal);
        assertEquals(meal.getName(), createdMeal.getName());
        assertEquals(meal.getQuantity(), createdMeal.getQuantity());
        assertEquals(meal.getCalories(), createdMeal.getCalories());
    }
}
