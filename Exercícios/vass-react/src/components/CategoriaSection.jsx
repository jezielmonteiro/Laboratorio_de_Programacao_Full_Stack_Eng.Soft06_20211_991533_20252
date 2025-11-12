import ProdutoItem from './ProdutoItem'

export default function CategoriaSection({ categoria }) {
    return (
        <section>
            <h3>{categoria.nome}</h3>
            <ul>
                {categoria.produtos.map(p => (
                    <ProdutoItem key={p.id} produto={p} />
                ))}
            </ul>
        </section>
    )
}