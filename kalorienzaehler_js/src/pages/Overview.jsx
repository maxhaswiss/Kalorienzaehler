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
            </td>
            <td>
            </td>
            <td>
            </td>
            <td>
            </td>
            <td>
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
