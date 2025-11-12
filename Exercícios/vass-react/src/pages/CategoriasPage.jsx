import { useMemo, useState } from 'react'
import { categorias as base} from '../data/categorias'
import CategoriaSection from '../components/CategoriaSection'

export default function CategoriasPage() {
    const [ordem, setOrdem] = useState('asc')

    const categoriasOrdenadas = useMemo(() => {
        const copia = base.map(cat => ({
            ...cat,
            produtos: [...cat.produtos]
        }))

        for (const cat of copia) {
            cat.produtos.sort((a,b) =>
                ordem === 'asc' ? a.preco - b.preco : b.preco - a.preco
            )
        }
        return copia
    }, [ordem])

    return (
        <main>
            <header style={{display: 'flex', gap: 12, alignItems: 'center'}}>
                <h2>Categorias</h2>
                <label>
                    Ordernar por preço:
                    <select value={ordem} onChange={e => setOrdem(e.target.value)}>
                        <option value="asc">Menor para maior</option>
                        <option value="desc">Maior para menor</option>
                    </select>
                </label>
            </header>

            {categoriasOrdenadas.map(cat => (
                <CategoriaSection key={cat.id} categoria={cat} />
            ))}
        </main>
    )
}