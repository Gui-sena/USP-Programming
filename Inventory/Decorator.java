import java.io.PrintWriter;
import java.io.IOException;

abstract class Decorator implements Produto{

	public Produto p;

	public Decorator( Produto p ){

		this.p = p;
	}

	public String formataParaImpressao(){

		return p.formataParaImpressao();
	}
}