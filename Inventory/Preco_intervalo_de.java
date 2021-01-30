import java.util.*;

public class Preco_intervalo_de implements Filtro{

	public Preco_intervalo_de(){
	}

	public boolean filtra( Produto produto, Object argFiltro ) {

		List intervalo;
		intervalo = (List) argFiltro;
		if(produto.getPreco() <= (double) intervalo.get(1) && produto.getPreco() >= (double) intervalo.get(0))
			return true;
		else
			return false;
	}
}