public class Categoria_igual_a implements Filtro{

	public Categoria_igual_a(){
	}

	public boolean filtra( Produto produto, Object argFiltro ) {

		if(produto.getCategoria().equalsIgnoreCase((String)argFiltro))
			return true;
		else
			return false;
	}
}