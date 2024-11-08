import { useState, useEffect } from 'react'

export default function Overview() {

  const [meals, setMeals] = useState([])

  useEffect(() => {
<<<<<<< HEAD
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
=======
    fetch("http://localhost:8080/meals")
   .then(r => r.json())
   .then(qs => {
      setMeals(qs)
    })
  }, [])


  return (
    <>
    <h1>Übersicht Mahlzeiten</h1>
    <hr />
    <div className='meal-table'>
      <table>
        <thead>
          <tr>
            <th>Mahlzeit</th>
            <th>Lebensmittelname</th>
            <th>Menge</th>
            <th>Kalorien</th>
            <th>Nährwertinformationen</th>
          </tr>
        </thead>
        <tbody>
          { meals.map(m => <tr key={ m.id }>
            <td>
              (m.content.meal)
            </td>
            <td>
              (m.content.foodName)
            </td>
            <td>
              (m.content.amount)
            </td>
            <td>
              <span>pro 100g</span>
              {"Kalorien insgesamt: " + (m.content.amount * m.content.calories).toFixed(1)}
              <span>pro 100g</span>AMOUNT*CALORIES
            </td>
            <td>
              (m.content.nutrition)
              <td>Fett: </td>
              <td>Kohlenhydrate: </td>
              <td>Proteine: </td>
            </td>
          </tr>)}
        </tbody>
      </table>
    </div>
    <hr />
    <br />
>>>>>>> parent of 3ce2b08 (Merge remote-tracking branch 'origin/main')
    </>
  )
}
