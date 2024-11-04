import { useState } from 'react'

export default function AddMeal() {
  const [entries, setEntries] = useState({
    mealName: '',
    quantity: 1,
    products: [],
    productName: '',
    productQuantity: 0,
    productCalories: 0,
  });

  const store = (e) => {
    const { name, value } = e.target;
    setEntries({
      ...entries,
      [name]: value,
    });
  };

  const addProduct = () => {
    const product = {
      name: entries.productName,
      quantity: parseFloat(entries.productQuantity),
      calories: parseFloat(entries.productCalories),
    };
    setEntries({
      ...entries,
      products: [...entries.products, product],
      productName: '',
      productQuantity: 0,
      productCalories: 0,
    });
  };

  const submit = (e) => {
    e.preventDefault();

    if (entries.mealName === '' || entries.products.length === 0) {
      alert("Input is missing or invalid!");
      return;
    }

    const submitData = {
      name: entries.mealName,
      quantity: parseFloat(entries.quantity),
      products: entries.products,
    };

    fetch("http://localhost:8080/api/meals", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(submitData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error creating meal");
        }
        return response.json();
      })
      .then((data) => {
        console.log("Meal created successfully:", data);
        setEntries({
          mealName: '',
          quantity: 1,
          products: [],
          productName: '',
          productQuantity: 0,
          productCalories: 0,
        });
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return (
    <>
      <h1>Mahlzeit hinzufügen</h1>
      <hr />
      <form onSubmit={submit}>
        <div>
          <fieldset>
            <legend>Mahlzeit Name:</legend>
            <input
              type="text"
              name="mealName"
              minLength="2"
              maxLength="60"
              value={entries.mealName}
              onChange={store}
              required
            />
          </fieldset>
        </div>

        <div>
          <fieldset>
            <legend>Mahlzeit Menge (Faktor):</legend>
            <input
              className="number-input"
              type="number"
              name="quantity"
              min="1"
              max="10"
              value={entries.quantity}
              onChange={store}
              required
            />
          </fieldset>
        </div>

        <div>
          <fieldset>
            <legend>Produktname:</legend>
            <input
              type="text"
              name="productName"
              minLength="2"
              maxLength="60"
              value={entries.productName}
              onChange={store}
              required
            />
          </fieldset>
        </div>

        <div>
          <fieldset>
            <legend>Produkt Menge (g):</legend>
            <input
              className="number-input"
              type="number"
              name="productQuantity"
              min="1"
              max="1000"
              value={entries.productQuantity}
              onChange={store}
              required
            />
          </fieldset>
        </div>

        <div>
          <fieldset>
            <legend>Kalorien pro 100g:</legend>
            <input
              className="number-input"
              type="number"
              name="productCalories"
              min="1"
              max="10000"
              value={entries.productCalories}
              onChange={store}
              required
            />
          </fieldset>
        </div>

        <button type="button" onClick={addProduct}>
          Produkt hinzufügen
        </button>

        <div>
          <h3>Produkte in der Mahlzeit:</h3>
          <ul>
            {entries.products.map((product, index) => (
              <li key={index}>
                {product.name}, {product.quantity}g, {product.calories}kcal
              </li>
            ))}
          </ul>
        </div>

        <hr />
        <button className="input-button" type="submit">
          Speichern
        </button>
        <button
          className="input-button"
          type="reset"
          onClick={() =>
            setEntries({
              mealName: '',
              quantity: 1,
              products: [],
              productName: '',
              productQuantity: 0,
              productCalories: 0,
            })
          }
        >
          Zurücksetzen
        </button>
      </form>
    </>
  );
}
