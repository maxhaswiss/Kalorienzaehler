import { Routes, Route } from 'react-router-dom'
import Home from './pages/Home'
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
    </Route>
  </Routes>

  )
}

export default App
