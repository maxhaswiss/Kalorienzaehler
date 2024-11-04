import { Link } from 'react-router-dom'

export default function GlobalNavigation() {
  return (
    <>
    <div className='navigation'>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/tracker">Kalorientracker</Link></li>
        <li><Link to="/overview">Mahlzeiten Übersicht</Link></li>
        <li><Link to="/addMeal">Mahlzeit hinzufügen</Link></li>
      </ul>
      <hr className='line' />
    </div>
    </>
  )
}
