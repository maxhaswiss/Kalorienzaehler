import { useState, useEffect } from 'react'

export default function Overview() {

  const [meals, setMeals] = useState([])

  useEffect(() => {
    fetch("http://localhost:8080/api/meals")
      .then(r => r.json())
      .then(qs => {
        setMeals(qs);
      })
      .catch(error => {
        console.error("Error fetching meals:", error);
      });
  }, [])

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
      <hr />
      <br />
    </>
  )
}
