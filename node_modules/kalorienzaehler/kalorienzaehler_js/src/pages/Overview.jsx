import { useState, useEffect } from 'react'

export default function Overview() {

  const [meals, setMeals] = useState([])

  useEffect(() => {
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
    </>
  )
}
