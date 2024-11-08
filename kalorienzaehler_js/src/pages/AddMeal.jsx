import { useState } from 'react'

export default function AddMeal() {


  const [entries, setEntries] = useState({
    meal: '',
    foodName: '',
    amount: '',
    calories: '',
    nutrition: '',
  });

  const store = (e) => {
    setEntries({...entries,
      [e.target.name]: e.target.value
    })
  }

  const submit = (e) => {
    if (entries.meal==="" || entries.foodName==="" || entries.amount==="" || entries.calories==="" || entries.nutrition==="") {
      alert("Input is missing or invalid!")
    } else {
      const submitData = {
        content: {
          meal: entries.meal,
          foodName: entries.foodName,
          amount: entries.amount,
          calories: entries.calories,
          nutrition: entries.nutrition
        }
      }
  
      e.preventDefault()
      fetch("http://localhost:8080/meals", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(submitData)
      }).then(() => {
        e.target.reset()
      })
    }
  }

  
  return (
    <>
    <h1>Mahlzeit hinzufügen</h1>
    <hr />
      <form onSubmit={ submit }>
        <div>
          <fieldset className='first-field'>
            <legend>Mahlzeit: </legend>
            <input type="text" name="meal" minLength="2" maxLength="60" onChange={store} required />
          </fieldset>
        </div>
        
        <div>
          <fieldset>
            <legend>Lebensmittelname: </legend>
            <input type="text" name="foodName" minLength="2" maxLength="60" onChange={store} required />
          </fieldset>
        </div>
  
        <div>
          <fieldset>
            <legend>Menge: </legend>
            <input className='number-input' type="number" name="amount" min="1" max="200" onChange={store} required />
          </fieldset>
        </div>

        <div>
          <fieldset>
            <legend>Kalorien pro 100g: </legend>
            <input className='number-input' type="number" name="calories" min="1" max="10000" onChange={store} required />
          </fieldset>
        </div>

        <div>
          <fieldset className='last-field'>
            <legend>Nährwertinformationen: </legend>
            <div className='nutrition-box'>
              <label htmlFor="fett">Fett: </label>
              <input className='nutrition-input' type="number" id="fett" name="nutrition" value={"Fett"} onChange={store} required />
              <br />
              <label htmlFor="kohlenhydrate">Kohlenhydrate: </label>
              <input className='nutrition-input' type="number" id="kohlenhydrate" name="nutrition" value={"Kohlenhydrate"} onChange={store} required />
              <br />
              <label htmlFor="proteine">Proteine: </label>
              <input className='nutrition-input' type="number" id="proteine"name="nutrition" value={"Proteine"} onChange={store} required />
            </div>
            
          </fieldset>
        </div>

        <hr />
        <button className='input-button' type="submit">Speichern</button>
        <button className='input-button' type="reset">Zurücksetzen</button>
      </form>
    </>
  )
}

