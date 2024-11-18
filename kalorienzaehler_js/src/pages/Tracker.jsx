import { useState, useEffect } from 'react';

/**
 * Die Tracker-Komponente überwacht die täglichen Kalorien, Fette, Kohlenhydrate und Proteine,
 * basierend auf den Mahlzeiten eines Benutzers. Sie bietet die Möglichkeit, tägliche Werte zu speichern,
 * einen neuen Tag zu starten und die aktuellen Fortschritte zu verfolgen.
 */
export default function Tracker() {
  const [meals, setMeals] = useState([]); // Zustand für die Mahlzeiten
  const [totals, setTotals] = useState({
    totalCalories: 0,
    totalFat: 0,
    totalCarbohydrates: 0,
    totalProteins: 0,
  }); // Zustand für die Gesamtnährwerte
  const [dailyCalorieGoal, setDailyCalorieGoal] = useState(2500); // Standard-Kalorienziel

  /**
   * Prüft, ob ein neuer Tag begonnen hat, und setzt die Tageswerte bei Bedarf zurück.
   */
  const checkAndResetForNewDay = () => {
    const lastUpdatedDate = localStorage.getItem("lastUpdatedDate");
    const today = new Date().toISOString().split("T")[0];

    if (lastUpdatedDate !== today) {
      const userCalorieGoal = parseInt(
        prompt("Bitte geben Sie Ihr tägliches Kalorienziel ein:", "2500"),
        10
      );

      if (isNaN(userCalorieGoal) || userCalorieGoal <= 0) {
        alert("Ungültiges Kalorienziel. Standardwert von 2500 wird verwendet.");
        setDailyCalorieGoal(2500);
        localStorage.setItem("dailyCalorieGoal", 2500);
      } else {
        setDailyCalorieGoal(userCalorieGoal);
        localStorage.setItem("dailyCalorieGoal", userCalorieGoal);
      }

      // Setzt die Werte für einen neuen Tag zurück
      deleteAllMeals();
      setMeals([]);
      setTotals({
        totalCalories: 0,
        totalFat: 0,
        totalCarbohydrates: 0,
        totalProteins: 0,
      });
      localStorage.setItem("lastUpdatedDate", today);
      localStorage.setItem(
        "totals",
        JSON.stringify({
          totalCalories: 0,
          totalFat: 0,
          totalCarbohydrates: 0,
          totalProteins: 0,
        })
      );
      localStorage.removeItem("meals");
    } else {
      // Lädt gespeicherte Daten aus dem LocalStorage
      const storedTotals = JSON.parse(localStorage.getItem("totals"));
      const storedMeals = JSON.parse(localStorage.getItem("meals"));
      const storedCalorieGoal = parseInt(localStorage.getItem("dailyCalorieGoal"), 10);

      if (storedTotals) setTotals(storedTotals);
      if (storedMeals) setMeals(storedMeals);
      if (storedCalorieGoal) setDailyCalorieGoal(storedCalorieGoal);
    }
  };

  /**
   * Ruft die Mahlzeiten vom Backend-Server ab und berechnet die Gesamtnährwerte.
   */
  const fetchMeals = () => {
    fetch("http://localhost:8080/api/meals")
      .then((r) => r.json())
      .then((qs) => {
        setMeals(qs);
        calculateTotals(qs);
        localStorage.setItem("meals", JSON.stringify(qs));
      })
      .catch((error) => {
        console.error("Error fetching meals:", error);
      });
  };

  /**
   * Löscht alle Mahlzeiten auf dem Server, um den Tag zurückzusetzen.
   */
  const deleteAllMeals = () => {
    fetch("http://localhost:8080/api/meals", { method: "DELETE" })
      .then((response) => {
        if (response.ok) console.log("All meals deleted for the new day");
        else console.error("Failed to delete meals");
      })
      .catch((error) => {
        console.error("Error deleting meals:", error);
      });
  };

  /**
   * Simuliert einen neuen Tag, indem ein altes Datum gespeichert wird.
   */
  const simulateNewDay = () => {
    localStorage.setItem("lastUpdatedDate", "2000-01-01");
    checkAndResetForNewDay();
  };

  useEffect(() => {
    checkAndResetForNewDay(); // Überprüft den Tag bei Start
    fetchMeals(); // Lädt die Mahlzeiten
  }, []);

  /**
   * Berechnet die Gesamtkalorien, Fette, Kohlenhydrate und Proteine basierend auf den Mahlzeiten.
   */
  const calculateTotals = (meals) => {
    let totalCalories = 0;
    let totalFat = 0;
    let totalCarbohydrates = 0;
    let totalProteins = 0;

    meals.forEach((meal) => {
      meal.products.forEach((product) => {
        totalCalories += product.calories * (product.quantity / 100);
        totalFat += product.fat * (product.quantity / 100);
        totalCarbohydrates += product.carbohydrates * (product.quantity / 100);
        totalProteins += product.proteins * (product.quantity / 100);
      });
    });

    const newTotals = {
      totalCalories: totalCalories.toFixed(1),
      totalFat: totalFat.toFixed(1),
      totalCarbohydrates: totalCarbohydrates.toFixed(1),
      totalProteins: totalProteins.toFixed(1),
    };

    setTotals(newTotals);
    localStorage.setItem("totals", JSON.stringify(newTotals));
  };

  const progressPercentage = ((totals.totalCalories / dailyCalorieGoal) * 100).toFixed(1);

  /**
   * Rendert die Benutzeroberfläche, einschließlich Fortschrittsanzeige und Mahlzeitdetails.
   */
  return (
    <>
      <h1>Willkommen zum Kalorientracker / Fortschritt</h1>
      <button onClick={simulateNewDay}>Neuen Tag Simulieren (Testen)</button>
      <div>
        <hr />
        <h2>Tägliche Zusammenfassung</h2>
        <div className="progress-bar-container">
          <div className="progress-bar" style={{ width: `${progressPercentage}%` }}>
            {progressPercentage}% der täglichen Kalorienaufnahme
          </div>
        </div>
        <p>Kalorien insgesamt: {totals.totalCalories} kcal / {dailyCalorieGoal} kcal</p>
        <p>Fett insgesamt: {totals.totalFat} g</p>
        <p>Kohlenhydrate insgesamt: {totals.totalCarbohydrates} g</p>
        <p>Proteine insgesamt: {totals.totalProteins} g</p>
        <hr />
        <h2>Details der Mahlzeiten</h2>
        <div className="meal-table">
          <table>
            <thead>
              <tr>
                <th>Mahlzeit Name</th>
                <th>Produktname</th>
                <th>Menge (g)</th>
                <th>Kalorien</th>
                <th>Fett (g)</th>
                <th>Kohlenhydrate (g)</th>
                <th>Proteine (g)</th>
              </tr>
            </thead>
            <tbody>
              {meals.map((meal) => (
                <tr key={meal.mealId}>
                  <td>{meal.name}</td>
                  <td>
                    {meal.products.map((product, index) => (
                      <div key={index}>{product.name}</div>
                    ))}
                  </td>
                  <td>
                    {meal.products.map((product, index) => (
                      <div key={index}>{product.quantity}</div>
                    ))}
                  </td>
                  <td>
                    {meal.products.map((product, index) => (
                      <div key={index}>
                        {(product.calories * (product.quantity / 100)).toFixed(1)} kcal
                      </div>
                    ))}
                  </td>
                  <td>
                    {meal.products.map((product, index) => (
                      <div key={index}>{product.fat.toFixed(1)}</div>
                    ))}
                  </td>
                  <td>
                    {meal.products.map((product, index) => (
                      <div key={index}>{product.carbohydrates.toFixed(1)}</div>
                    ))}
                  </td>
                  <td>
                    {meal.products.map((product, index) => (
                      <div key={index}>{product.proteins.toFixed(1)}</div>
                    ))}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}
