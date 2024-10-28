# Dokumentation `Kalorienzaehler`

<hr>

## Ausgangslage:
Übergewicht ist das grösste Gesundheitsproblem unserer westlichen Gesellschaft. Der einzige Weg wie man wissenschaftlich bewiesen dagegen vorgehen kann ist mit einem Kaloriendefizit. Und der beste Weg um dieses Defizit wirklich zu erfüllen ist mit ehrlichem Kalorientracking. Und da setzen wir an indem wir ein Tool für genau das bieten.

## Vision:
Unsere Vision ist es einen posiviten Beitrag zur Gesundheit in der Schweiz und der Welt zu liefern indem wir es Usern so einfach wie möglich machen ihre Kalorien zu tracken.

## Zielen
- Benutzerfreundlichkeit und Zugänglichkeit:
    - [ ] Die Web-App soll ein einfaches, intuitives Interface bieten, das ohne lange Einarbeitung genutzt werden     kann.
    -  [ ] Die Oberfläche muss klar strukturiert und für alle Bildschirmgrössen (Responsive Design) optimiert sein.
    - [ ]Mehrsprachige Unterstützung, um eine breite Nutzerbasis anzusprechen.
- Effektives Kalorientracking:
    1. Eine umfassende Lebensmitteldatenbank bereitstellen, die den Nutzern erlaubt, schnell und einfach Kalorien zu suchen und hinzuzufügen.
    2. Individuelle Namenseinsetzung und Hinzufügung von Kalorien.

- Datenschutz und Sicherheit:
    -  Alle personenbezogenen Daten, einschliesslich Gesundheitsdaten, müssen verschlüsselt/sicher gespeichert werden.
## Rollen in unserem Projekt

### Product Owner 
Max Hasselwander <br>
**Aufgaben**
Ziele, Produkt Vision/ Idee, Dokumentation, Anforderungen
### Scrum Master 
Simon Tenger / Jan Gsell <br>
**Aufgaben**
Scrum Meetings leiten, Dokumentation, Sicherstellung der Arbeit, beseitigen von Hindernissen
### Lead Entwickler
Mykhaylo Zhovkevych <br>
**Aufgaben** 
Haupsächliche verantwortung der Entwicklung

<hr>

## User Stories und Aktzeptanzkriterien 

### 1. **User Story**:

_Als Nutzer möchte ich meine täglichen Mahlzeiten eingeben, damit ich die aufgenommenen Kalorien verfolgen kann._

**Akzeptanzkriterien**:

- Der Nutzer kann eine neue Mahlzeit hinzufügen.
- Jede Mahlzeit enthält mindestens den Namen des Lebensmittels und die Menge. 
- Die Kalorienzahl wird automatisch basierend auf der Menge berechnet.

### 2. **User Story**:

_Als Nutzer möchte ich Nährwerte (wie Fett, Kohlenhydrate, Proteine) zu den Mahlzeiten hinzufügen, damit ich meine Ernährung besser überwachen kann._

**Akzeptanzkriterien**:

- Der Nutzer kann Nährwertinformationen (Fett, Kohlenhydrate, Proteine) für jede Mahlzeit hinzufügen.
- Die Nährwerte werden in der Zusammenfassung der Mahlzeit angezeigt.
- Die App aktualisiert die täglichen Gesamtwerte für Fett, Kohlenhydrate und Proteine.

### 3. **User Story**:

_Als Nutzer möchte ich meine Ernährungsziele (z. B. tägliche Kalorienmenge) festlegen, um meinen Fortschritt im Auge zu behalten._

**Akzeptanzkriterien**:

- Der Nutzer kann ein tägliches Kalorienziel festlegen.
- Die App zeigt an, wie viele Kalorien der Nutzer im Verhältnis zu seinem Ziel aufgenommen hat.
- Eine visuelle Anzeige (z. B. Fortschrittsbalken) gibt den aktuellen Stand im Verhältnis zum Ziel wieder.

### 4. **User Story**:

_Als Nutzer möchte ich meine tägliche Kalorienzufuhr in einer Übersicht sehen, um meinen Fortschritt nachverfolgen zu können._

**Akzeptanzkriterien**:

- Der Nutzer kann eine tägliche Übersicht seiner Kalorienzufuhr einsehen.
- Die Übersicht zeigt eine Aufschlüsselung der Kalorien pro Mahlzeit(Frühstück, Mittagessen, Abendessen und Snacks).
- Der tägliche Gesamtkalorienwert wird klar und deutlich angezeigt.
- Die Übersicht wird täglich zurückgesetzt und speichert historische Daten.

### Reihenfolge (Priorisierung):

1. Mahlzeiteneingabe (User Story 1)
2. Nährwertangaben hinzufügen (User Story 2)
3. Ernährungsziele festlegen (User Story 3)
4. Tagesübersicht der Kalorienzufuhr (User Story 4)

Dieser Product Backlog gibt einen Überblick über die wichtigsten Funktionen der App und priorisiert die Entwicklungsschritte.

<hr>

### 5. Backlog
- die alle Points von **1. User Story**
- die alle Points von **2. User Story**
- die alle Points von **3. User Story**
- die alle Points von **4. User Story**

## optional
- Die Mahlzeit wird korrekt im Tagesprotokoll gespeichert.


## Story Points Tabelle 
| User Story                                                                                      | Story Points |User Story|
|-------------------------------------------------------------------------------------------------|--------------|--------|
| Als Nutzer möchte ich meine täglichen Mahlzeiten eingeben, damit ich die aufgenommenen Kalorien verfolgen kann                   | 5            |    1    |
| Als Nutzer möchte ich Nährwerte (wie Fett, Kohlenhydrate, Proteine) zu den Mahlzeiten hinzufügen, damit ich meine Ernährung besser überwachen kann.                | 13            |    2    |
| Als Nutzer möchte ich meine Ernährungsziele (z. B. tägliche Kalorienmenge) festlegen, um meinen Fortschritt im Auge zu behalten.         | 8            |    3    |
| Als Nutzer möchte ich meine tägliche Kalorienzufuhr in einer Übersicht sehen, um meinen Fortschritt nachverfolgen zu können        |      13       |    4    |


