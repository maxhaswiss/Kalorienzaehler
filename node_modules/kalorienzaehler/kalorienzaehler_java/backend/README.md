# Technische Dokumentation für das Backend

## Struktur und UML Klassendiagramm

### Allgemeine Struktur der Anwendung

Die Anwendung ist in folgende Schichten unterteilt:

- **Entitäten (Entities)** – Diese Klassen repräsentieren die zentralen Datenmodelle der Anwendung. Sie definieren die Struktur der Daten.
- **Repositorys** – Diese Klassen sind für den Zugriff auf die Datenbank verantwortlich. Sie bieten CRUD-Operationen (Create, Read, Update, Delete) für die jeweiligen Entitäten und ermöglichen es, Daten einfach und effizient abzurufen und zu speichern.
- **Services** – Die Service-Schicht enthält die Geschäftslogik der Anwendung. Sie interagiert mit den Repositorys, um komplexe Abläufe oder Regeln umzusetzen. Services stellen die Funktionen bereit, die die Controller-Schicht benötigt.
- **Controller (optional)** – Die Controller-Schicht (falls implementiert) stellt eine API-Schnittstelle für die Benutzer oder andere Systeme bereit, die die Anwendung nutzen. Sie verarbeitet Anfragen, ruft die entsprechenden Services auf und gibt die Ergebnisse zurück.

---

### Entitäten und deren Aufgaben

#### 1. Meal (Mahlzeit)

- **Aufgabe**: Die `Meal`-Entität repräsentiert eine Mahlzeit, die aus mehreren Produkten bestehen kann. Sie speichert Informationen über die Gesamtmenge, den Namen und die Kalorien der Mahlzeit sowie die zugehörigen Produkte und Nährwerte.
- **Methoden**:
  - `addProduct(product: Product)`: Fügt ein Produkt zur Mahlzeit hinzu.
  - `calculateMealStats()`: Berechnet die Nährwerte basierend auf den enthaltenen Produkten.
  - `calculateCalories()`: Gibt die Gesamtkalorien der Mahlzeit zurück.

---

#### 2. Product (Produkt)

- **Aufgabe**: Die `Product`-Entität repräsentiert ein einzelnes Produkt (z. B. ein Nahrungsmittel oder eine Zutat) und speichert Informationen wie Kalorien, Menge, Fett, Kohlenhydrate und Proteine.

---

#### 3. NutritionalInfo (Nährwerte)

- **Aufgabe**: Die `NutritionalInfo`-Entität speichert spezifische Nährwerte wie Fett, Kohlenhydrate und Proteine. Sie kann an eine Mahlzeit gebunden sein und stellt Methoden bereit, um Nährwerte zu berechnen und zu aktualisieren.
- **Methoden**:
  - `calculateTotalNutrients()`: Berechnet die Gesamtmenge an Nährstoffen für eine Mahlzeit.
  - `updateNutritionalValues(newFat, newCarbs, newProteins)`: Aktualisiert die Nährwerte.

---

#### 4. CaloricIntake (Kalorienaufnahme)

- **Aufgabe**: Die `CaloricIntake`-Entität repräsentiert die tägliche Kalorienaufnahme eines Benutzers und speichert alle Mahlzeiten eines Tages sowie die Gesamtmenge der aufgenommenen Kalorien.
- **Methoden**:
  - `addMeal(meal: Meal)`: Fügt eine Mahlzeit zur Kalorienaufnahme hinzu.
  - `calculateTotalCalories()`: Berechnet die Gesamtkalorien für den Tag.
  - `resetDailyIntake()`: Setzt die Tagesaufnahme zurück.

---

#### 5. DailyGoal (Tagesziel)

- **Aufgabe**: Die `DailyGoal`-Entität speichert das tägliche Kalorienziel eines Benutzers und ermöglicht es, das aktuelle Kalorienziel zu setzen und die aufgenommenen Kalorien zu aktualisieren.
- **Methoden**:
  - `setDailyGoal(newGoal: double)`: Setzt ein neues Kalorienziel.
  - `updateTotalCalories(calories: double)`: Aktualisiert die aufgenommenen Kalorien.

