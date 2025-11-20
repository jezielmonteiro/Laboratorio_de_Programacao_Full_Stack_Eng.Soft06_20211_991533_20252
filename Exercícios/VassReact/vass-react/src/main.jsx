import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import CategoriasPage from './pages/CategoriasPage'
import './index.css'

createRoot(document.getElementById('categorias')).render(
  <StrictMode>
    <CategoriasPage />
  </StrictMode>,
)