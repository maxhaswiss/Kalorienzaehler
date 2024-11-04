import { useState, useEffect } from 'react';

export default function Tracker() {
  const [meals, setMeals] = useState([]);
  const [totals, setTotals] = useState({
    totalCalories: 0,
    totalFat: 0,
    totalCarbohydrates: 0,
    totalProteins: 0,
  });
  const dailyCalorieGoal = 2500;

  // Check if a new day has started and reset if needed
  const checkAndResetForNewDay = () => {
    const lastUpdatedDate = localStorage.getItem("lastUpdatedDate");
    const today = new Date().toISOString().split("T")[0];

    if (lastUpdatedDate !== today) {
      // Reset totals and delete all meals for a new day
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
      localStorage.removeItem("meals"); // Clear meals from local storage for the new day
    } else {
      // Load totals and meals from localStorage
      const storedTotals = JSON.parse(localStorage.getItem("totals"));
      const storedMeals = JSON.parse(localStorage.getItem("meals"));
      if (storedTotals) {
        setTotals(storedTotals);
      }
      if (storedMeals) {
        setMeals(storedMeals);
      }
    }
  };

  // Fetch meals from the API
  const fetchMeals = () => {
    fetch("http://localhost:8080/api/meals")
      .then((r) => r.json())
      .then((qs) => {
        setMeals(qs);
        calculateTotals(qs);
        localStorage.setItem("meals", JSON.stringify(qs)); // Store meals in local storage for persistence
      })
      .catch((error) => {
        console.error("Error fetching meals:", error);
      });
  };

  // Delete all meals from the server
  const deleteAllMeals = () => {
    fetch("http://localhost:8080/api/meals", {
      method: "DELETE",
    })
      .then((response) => {
        if (response.ok) {
          console.log("All meals deleted for the new day");
        } else {
          console.error("Failed to delete meals");
        }
      })
      .catch((error) => {
        console.error("Error deleting meals:", error);
      });
  };

  // Simulate a new day by setting an old date in localStorage
  const simulateNewDay = () => {
    localStorage.setItem("lastUpdatedDate", "2000-01-01"); // Set an old date to simulate a new day
    checkAndResetForNewDay(); // Apply the reset logic
  };

  useEffect(() => {
    checkAndResetForNewDay();
    fetchMeals(); // Load meals from the backend
  }, []);

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
        <div className='meal-table'>
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

      <style jsx>{`
        .progress-bar-container {
          width: 100%;
          background-color: #e0e0e0;
          border-radius: 5px;
          overflow: hidden;
          height: 25px;
          margin-bottom: 10px;
        }
        .progress-bar {
          height: 100%;
          background-color: #4caf50;
          text-align: center;
          color: white;
          line-height: 25px;
          border-radius: 5px 0 0 5px;
        }
      `}</style>
    </>
  );
}
