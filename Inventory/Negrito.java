public class Negrito extends Decorator{

	public Negrito( Produto p ){

		super( p );
	}

	public String formataParaImpressao(){

		String src = super.formataParaImpressao();
		src ="<span style=\"font-weight:bold\">" + "#" + src;
		return src;
	}

	public void setQtdEstoque(int qtdEstoque){}
	public void setPreco(double preco){}
	
	public int getId(){ return p.getId(); }
	public String getDescricao(){ return p.getDescricao(); }
	public String getCategoria(){ return p.getCategoria(); }
	public int getQtdEstoque(){ return p.getQtdEstoque(); }
	public double getPreco(){ return p.getPreco(); }

}
