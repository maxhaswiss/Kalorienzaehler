import { useState, useEffect } from 'react';

/**
 * Die Overview-Komponente zeigt eine Übersicht aller Mahlzeiten und ihrer zugehörigen Produkte.
 * 
 * Die Daten werden aus einer API geladen und in einer tabellarischen Ansicht dargestellt.
 */
export default function Overview() {
  const [meals, setMeals] = useState([]); // Zustand für die gespeicherten Mahlzeiten.

  /**
   * Lädt die Mahlzeiten-Daten aus der API, sobald die Komponente gerendert wird.
   */
  useEffect(() => {
    fetch("http://localhost:8080/api/meals") // Abruf der Mahlzeiten-Daten von der API.
      .then((r) => r.json()) // Konvertiert die Antwort in JSON.
      .then((qs) => {
        setMeals(qs); // Speichert die Mahlzeiten im Zustand.
      })
      .catch((error) => {
        console.error("Error fetching meals:", error); // Gibt Fehler in der Konsole aus.
      });
  }, []); // Der leere Array stellt sicher, dass die Anfrage nur einmal beim Laden der Komponente ausgeführt wird.

  /**
   * Darstellung der Komponente.
   * Zeigt die Mahlzeiten-Daten in einer Tabelle mit Spalten für Name, Produkte, Mengen und Nährwerte.
   */
  return (
    <>
      <h1>Übersicht Mahlzeiten</h1>
      <hr />
      <div className='meal-table'>
        <table>
          <thead>
            <tr>
              <th>Mahlzeit Name</th>
              <th>Produktname</th>
              <th>Menge (g)</th>
              <th>Kalorien insgesamt</th>
              <th>Fett (g)</th>
              <th>Kohlenhydrate (g)</th>
              <th>Proteine (g)</th>
            </tr>
          </thead>
          <tbody>
            {meals.map((meal) => (
              <tr key={meal.mealId}>
                <td>{meal.name}</td>
                {/* Produkte der Mahlzeit */}
                <td>
                  {meal.products.map((product, index) => (
                    <div key={index}>{product.name}</div>
                  ))}
                </td>
                {/* Mengen der Produkte */}
                <td>
                  {meal.products.map((product, index) => (
                    <div key={index}>{product.quantity}</div>
                  ))}
                </td>
                {/* Kalorien berechnet anhand der Menge */}
                <td>
                  {meal.products.map((product, index) => (
                    <div key={index}>
                      {(product.calories * (product.quantity / 100)).toFixed(1)} kcal
                    </div>
                  ))}
                </td>
                {/* Fettgehalt */}
                <td>
                  {meal.products.map((product, index) => (
                    <div key={index}>{product.fat.toFixed(1)}</div>
                  ))}
                </td>
                {/* Kohlenhydrate */}
                <td>
                  {meal.products.map((product, index) => (
                    <div key={index}>{product.carbohydrates.toFixed(1)}</div>
                  ))}
                </td>
                {/* Proteine */}
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
      <hr />
      <br />
    </>
  );
}
