import { useState } from 'react';

/**
 * Die AddMeal-Komponente ermöglicht es Benutzern, eine neue Mahlzeit zu erstellen, 
 * indem sie Produkte hinzufügen und die Gesamtkalorien berechnen.
 */
export default function AddMeal() {
  // Verwaltet den Zustand aller Eingaben und der Liste der Produkte.
  const [entries, setEntries] = useState({
    mealName: '', // Name der Mahlzeit.
    quantity: 1, // Mengenfaktor der Mahlzeit (z. B. 2x Frühstück).
    products: [], // Liste der Produkte, die Teil der Mahlzeit sind.
    productName: '', // Temporärer Name eines Produkts.
    productQuantity: '', // Temporäre Menge eines Produkts (in Gramm).
    productCalories: '', // Temporäre Kalorienangabe pro 100g für ein Produkt.
    productFat: '', // Temporärer Fettgehalt pro 100g eines Produkts.
    productCarbohydrates: '', // Temporärer Kohlenhydratgehalt pro 100g eines Produkts.
    productProteins: '', // Temporärer Proteingehalt pro 100g eines Produkts.
  });

  // Speichert die Änderungen der Eingaben im Zustand.
  const store = (e) => {
    const { name, value } = e.target;
    setEntries({
      ...entries,
      [name]: value,
    });
  };

  /**
   * Fügt ein Produkt zur Liste hinzu und setzt die Eingabefelder zurück.
   * Überprüft, ob wichtige Felder (Name, Menge, Kalorien) ausgefüllt sind.
   */
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

  /**
   * Sendet die Mahlzeit an den Server.
   * Überprüft, ob mindestens ein Produkt hinzugefügt wurde, bevor die Daten gesendet werden.
   */
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
        {/* Eingabe für den Namen der Mahlzeit */}
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

        {/* Eingabe für die Mengenangabe der Mahlzeit */}
        <fieldset>
          <legend>Mahlzeit Menge (Faktor):</legend>
          <input
            type="number"
            name="quantity"
            min="1"
            max="10"
            value={entries.quantity}
            onChange={store}
          />
        </fieldset>

        {/* Eingaben für Produkte */}
        <fieldset>
          <legend>Produktname:</legend>
          <input
            type="text"
            name="productName"
            value={entries.productName}
            onChange={store}
          />
        </fieldset>
        <fieldset>
          <legend>Produkt Menge (g):</legend>
          <input
            type="number"
            name="productQuantity"
            min="0"
            max="1000"
            value={entries.productQuantity}
            onChange={store}
          />
        </fieldset>
        <fieldset>
          <legend>Kalorien pro 100g:</legend>
          <input
            type="number"
            name="productCalories"
            min="0"
            max="10000"
            value={entries.productCalories}
            onChange={store}
          />
        </fieldset>
        <fieldset>
          <legend>Fett, Kohlenhydrate, Proteine (pro 100g):</legend>
          <input
            type="number"
            name="productFat"
            placeholder="Fett"
            value={entries.productFat}
            onChange={store}
          />
          <input
            type="number"
            name="productCarbohydrates"
            placeholder="Kohlenhydrate"
            value={entries.productCarbohydrates}
            onChange={store}
          />
          <input
            type="number"
            name="productProteins"
            placeholder="Proteine"
            value={entries.productProteins}
            onChange={store}
          />
        </fieldset>

        <button type="button" onClick={addProduct}>
          Produkt hinzufügen
        </button>

        {/* Anzeige der hinzugefügten Produkte */}
        <h3>Produkte in der Mahlzeit:</h3>
        <ul>
          {entries.products.map((product, index) => (
            <li key={index}>
              {product.name}, {product.quantity}g, {product.calories} kcal,
              Fett: {product.fat}g, Kohlenhydrate: {product.carbohydrates}g, Proteine: {product.proteins}g
            </li>
          ))}
        </ul>

        {/* Buttons zum Speichern oder Zurücksetzen */}
        <button type="submit">Speichern</button>
        <button type="reset" onClick={() => setEntries({
          mealName: '',
          quantity: 1,
          products: [],
          productName: '',
          productQuantity: '',
          productCalories: '',
          productFat: '',
          productCarbohydrates: '',
          productProteins: '',
        })}>
          Zurücksetzen
        </button>
      </form>
    </>
  );
}
