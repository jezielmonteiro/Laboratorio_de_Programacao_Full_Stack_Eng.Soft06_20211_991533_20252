import { useEffect, useState } from 'react'
import { listarCategorias } from '../services/api'
import FormNovoProduto from '../components/FormNovoProduto'

export default function CatalogoPage() {
    const [dados, setDados] = useState([])
    const [loading, setLoading] = useState(true)
    const [erro, setErro] = useState(null)

    useEffect(() => {
        let ativo = true
        listarCategorias()
            .then(json => ativo && setDados(json))
            .catch(e => ativo && setErro(e.message))
            .finally(() => ativo && setLoading(false))
        return () => { ativo = false}
    }, [])

    if (loading) return <p>Carregando...</p>
    if (erro) return <p style={{color:'crimson'}}>Erro: {erro}</p>

    return (
        <main>
            <h2>Categorias</h2>
            <section style={{marginTop: 16}}>
                <h3>Novo Produto</h3>
                <FormNovoProduto onCriado={() => {
                    alert('Produto criado!')
                }}/>
            </section>
            <pre>{JSON.stringify(dados, null, 2)}</pre>
        </main>
    )
}

useEffect(() => {
    const ac = new AbortController()
    setLoading(true); setErro(null)

    listarCategorias(ac.signal)
        .then(json => setDados(json))
        .catch(e => {
            if (e.name !== 'AbortError') setErro(e.message)
        })
        .finally(() => setLoading(false))
        return () => ac.abort()
    }, [])