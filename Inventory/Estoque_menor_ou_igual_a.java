public class Estoque_menor_ou_igual_a implements Filtro{

	public Estoque_menor_ou_igual_a(){
	}

	public boolean filtra( Produto produto, Object argFiltro ) {

		if(produto.getQtdEstoque() <= (Integer) argFiltro)
			return true;
		else
			return false;
	}
}