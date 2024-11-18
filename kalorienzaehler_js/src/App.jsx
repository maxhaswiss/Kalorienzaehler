import { Routes, Route } from 'react-router-dom'
import Home from './pages/Home'
import Tracker from './pages/Tracker'
import Overview from './pages/Overview'
import AddMeal from './pages/AddMeal'
import Layout from './components/Layout'
import PageNotFound from './pages/404page'
import './App.css'

function App() {

  return (

  <Routes>
    <Route path="/" element={<Layout />}>
      <Route index element={<Home />} />
      <Route path="*" element={<PageNotFound />} />
      <Route path="home" element={<Home />} />
      <Route path="tracker" element={<Tracker />} />
      <Route path="overview" element={<Overview />} />
      <Route path="addMeal" element={<AddMeal />} />
    </Route>
  </Routes>

  )
}

export default App
