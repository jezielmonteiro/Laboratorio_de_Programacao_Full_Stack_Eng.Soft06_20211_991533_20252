import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import CatalogoPage from './pages/CatalogoPage'
import './index.css'

createRoot(document.getElementById('catalogo')).render(
  <StrictMode>
    <CatalogoPage />
  </StrictMode>,
)