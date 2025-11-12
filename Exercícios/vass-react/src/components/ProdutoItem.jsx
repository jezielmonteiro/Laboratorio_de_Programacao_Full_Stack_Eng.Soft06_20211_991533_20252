export default function ProdutoItem({ produto }) {
    const barato = produto.preco < 100
    
    return (
        <li style={{ color: barato ? 'green' : 'inherit'}}>
            {produto.nome} - R$ {produto.preco.toFixed(2)}
        </li>
    )
}