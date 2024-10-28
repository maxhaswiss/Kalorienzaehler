import { Link } from 'react-router-dom'

export default function GlobalNavigation() {
  return (
    <>
    <div className='navigation'>
      <ul>
        <li><Link to="/">Home</Link></li>
        
      </ul>
      <hr className='line' />
    </div>
    </>
  )
}
