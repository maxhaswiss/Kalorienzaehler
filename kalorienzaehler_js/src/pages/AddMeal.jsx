import { useState } from 'react';

export default function AddMeal() {
  const [entries, setEntries] = useState({
    mealName: '',
    quantity: 1,
    products: [],
    productName: '',
    productQuantity: '',
    productCalories: '',
    productFat: '',
    productCarbohydrates: '',
    productProteins: '',
  });

  const store = (e) => {
    const { name, value } = e.target;
    setEntries({
      ...entries,
      [name]: value,
    });
  };

  const addProduct = () => {
    if (entries.productName === '' || entries.productQuantity <= 0 || entries.productCalories <= 0) {
      alert("Produktname, Menge, und Kalorien sind erforderlich, um ein Produkt hinzuzufügen!");
      return;
    }

    const product = {
      name: entries.productName,
      quantity: parseFloat(entries.productQuantity) || 0,
      calories: parseFloat(entries.productCalories) || 0,
      fat: parseFloat(entries.productFat) || 0,
      carbohydrates: parseFloat(entries.productCarbohydrates) || 0,
      proteins: parseFloat(entries.productProteins) || 0,
    };

    setEntries({
      ...entries,
      products: [...entries.products, product],
      productName: '',
      productQuantity: '',
      productCalories: '',
      productFat: '',
      productCarbohydrates: '',
      productProteins: '',
    });
  };

  const submit = (e) => {
    e.preventDefault();

    if (entries.products.length === 0) {
      alert("Bitte fügen Sie mindestens ein Produkt zur Mahlzeit hinzu!");
      return;
    }

    const submitData = {
      name: entries.mealName,
      quantity: parseFloat(entries.quantity) || 1,
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
          throw new Error("Fehler beim Erstellen der Mahlzeit");
        }
        return response.json();
      })
      .then((data) => {
        console.log("Mahlzeit erfolgreich erstellt:", data);
        setEntries({
          mealName: '',
          quantity: 1,
          products: [],
          productName: '',
          productQuantity: '',
          productCalories: '',
          productFat: '',
          productCarbohydrates: '',
          productProteins: '',
        });
      })
      .catch((error) => {
        console.error("Fehler:", error);
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
              min="0"
              max="1000"
              value={entries.productQuantity}
              onChange={store}
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
              min="0"
              max="10000"
              value={entries.productCalories}
              onChange={store}
            />
          </fieldset>
        </div>

        <div>
          <fieldset>
            <legend>Fett pro 100g (g):</legend>
            <input
              className="number-input"
              type="number"
              name="productFat"
              min="0"
              max="100"
              value={entries.productFat}
              onChange={store}
            />
          </fieldset>
        </div>

        <div>
          <fieldset>
            <legend>Kohlenhydrate pro 100g (g):</legend>
            <input
              className="number-input"
              type="number"
              name="productCarbohydrates"
              min="0"
              max="100"
              value={entries.productCarbohydrates}
              onChange={store}
            />
          </fieldset>
        </div>

        <div>
          <fieldset>
            <legend>Proteine pro 100g (g):</legend>
            <input
              className="number-input"
              type="number"
              name="productProteins"
              min="0"
              max="100"
              value={entries.productProteins}
              onChange={store}
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
                {product.name}, {product.quantity}g, {product.calories} kcal,
                Fett: {product.fat}g, Kohlenhydrate: {product.carbohydrates}g, Proteine: {product.proteins}g
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
              productQuantity: '',
              productCalories: '',
              productFat: '',
              productCarbohydrates: '',
              productProteins: '',
            })
          }
        >
          Zurücksetzen
        </button>
      </form>
    </>
  );
}
