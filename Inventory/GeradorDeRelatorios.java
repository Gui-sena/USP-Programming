import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

public class GeradorDeRelatorios {

	// operador bit a bit "ou" pode ser usado para combinar mais de  
	// um estilo de formatacao simultaneamente (veja exemplo no main)
	public static final int FORMATO_PADRAO  = 0b0000;
	public static final int FORMATO_NEGRITO = 0b0001;
	public static final int FORMATO_ITALICO = 0b0010;

	private static Insertion_sort insertion_sort = new Insertion_sort();
	private static Quick_sort quick_sort = new Quick_sort();
	private static Preco_Cresc preco_cresc = new Preco_Cresc();
	private static Preco_Decresc preco_decresc = new Preco_Decresc();
	private static Desc_Cresc desc_cresc = new Desc_Cresc();
	private static Desc_Decresc desc_decresc = new Desc_Decresc();
	private static Estoque_Cresc estoque_cresc = new Estoque_Cresc();
	private static Estoque_Decresc estoque_decresc = new Estoque_Decresc();
	private static Todos todos = new Todos();
	private static Categoria_igual_a categoria_igual_a = new Categoria_igual_a();
	private static Estoque_menor_ou_igual_a estoque_menor_ou_igual_a = new Estoque_menor_ou_igual_a();
	private static Desc_subString desc_subString = new Desc_subString();
	private static Preco_intervalo_de preco_intervalo_de = new Preco_intervalo_de();
	private Produto [] produtos;
	private Alg_Ord algoritmo;
	private Crit_Ord criterio;
	private Filtro filtro;
	private Object argFiltro;


	public GeradorDeRelatorios(Produto [] produtos, Alg_Ord algoritmo, Crit_Ord criterio, Filtro filtro, Object argFiltro){

		this.produtos = new Produto[produtos.length];
		
		for(int i = 0; i < produtos.length; i++){
		
			this.produtos[i] = produtos[i];
		}

		this.algoritmo = algoritmo;
		this.criterio = criterio;
		this.filtro = filtro;
		this.argFiltro = argFiltro;
	}

	
	public Produto[] getProdutos(){
		return produtos;
	}

	public void setProdutos(Produto[] produtos){
		this.produtos = produtos;
	}


	public String[] separando( String produto ){

		int tam = produto.length();
		int count = 0;
		char mds;
		char tah = '#';
		for (int i = 0; i < tam; i++){
			mds = produto.charAt(i);
			if (mds == tah){
				count++;
			}
		}
		String[] src = new String[count];
		src = produto.split("#");
		return src;
	}
	
	

	public void geraRelatorio(String arquivoSaida) throws IOException {

		algoritmo.ordena(0, produtos.length - 1, produtos, criterio);

		PrintWriter out = new PrintWriter(arquivoSaida);

		out.println("<!DOCTYPE html><html>");
		out.println("<head><title>Relatorio de produtos</title></head>");
		out.println("<body>");
		out.println("Relatorio de Produtos:");
		out.println("<ul>");

		int count = 0;
		String[] src;
		
		for(int i = 0; i < produtos.length; i++){

			Produto p = produtos[i];
			if (filtro.filtra(p, argFiltro)){

				out.print("<li>");
				src = separando(p.formataParaImpressao());
				for (int j = 0; j < src.length; j++)	
					out.print(src[j]);
				out.println("</li>");
				count++;
			}
		}

		out.println("</ul>");
		out.println(count + " produtos listados, de um total de " + produtos.length + ".");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	public static Produto [] carregaProdutos(){

		return new Produto [] { 

			new Italico(new Negrito(new ProdutoPadrao( 1, "O Hobbit", "Livros", 2, 34.90))),
			new ProdutoPadrao( 2, "Notebook Core i7", "Informatica", 5, 1999.90),
			new Italico(new Colorido( "green" , new ProdutoPadrao( 3, "Resident Evil 4", "Games", 7, 79.90))),
			new ProdutoPadrao( 4, "iPhone", "Telefonia", 8, 4999.90),
			new Negrito(new ProdutoPadrao( 5, "Calculo I", "Livros", 20, 55.00)),
			new ProdutoPadrao( 6, "Power Glove", "Games", 3, 499.90),
			new ProdutoPadrao( 7, "Microsoft HoloLens", "Informatica", 1, 19900.00),
			new ProdutoPadrao( 8, "OpenGL Programming Guide", "Livros", 4, 89.90),
			new ProdutoPadrao( 9, "Vectrex", "Games", 1, 799.90),
			new ProdutoPadrao(10, "Carregador iPhone", "Telefonia", 15, 499.90),
			new ProdutoPadrao(11, "Introduction to Algorithms", "Livros", 7, 315.00),
			new ProdutoPadrao(12, "Daytona USA (Arcade)", "Games", 1, 12000.00),
			new ProdutoPadrao(13, "Neuromancer", "Livros", 5, 45.00),
			new ProdutoPadrao(14, "Nokia 3100", "Telefonia", 4, 249.99),
			new Italico( new Negrito(new ProdutoPadrao(15, "Oculus Rift", "Games", 1, 3600.00))),
			new ProdutoPadrao(16, "Trackball Logitech", "Informatica", 1, 250.00),
			new ProdutoPadrao(17, "After Burner II (Arcade)", "Games", 2, 8900.0),
			new ProdutoPadrao(18, "Assembly for Dummies", "Livros", 30, 129.90),
			new ProdutoPadrao(19, "iPhone (usado)", "Telefonia", 3, 3999.90),
			new ProdutoPadrao(20, "Game Programming Patterns", "Livros", 1, 299.90),
			new Negrito(new ProdutoPadrao(21, "Playstation 2", "Games", 10, 499.90)),
			new ProdutoPadrao(22, "Carregador Nokia", "Telefonia", 14, 89.00),
			new ProdutoPadrao(23, "Placa Aceleradora Voodoo 2", "Informatica", 4, 189.00),
			new ProdutoPadrao(24, "Stunts", "Games", 3, 19.90),
			new ProdutoPadrao(25, "Carregador Generico", "Telefonia", 9, 30.00),
			new ProdutoPadrao(26, "Monitor VGA 14 polegadas", "Informatica", 2, 199.90),
			new ProdutoPadrao(27, "Nokia N-Gage", "Telefonia", 9, 699.00),
			new ProdutoPadrao(28, "Disquetes Maxell 5.25 polegadas (caixa com 10 unidades)", "Informatica", 23, 49.00),
			new ProdutoPadrao(29, "Alone in The Dark", "Games", 11, 59.00),
			new ProdutoPadrao(30, "The Art of Computer Programming Vol. 1", "Livros", 3, 240.00),
			new ProdutoPadrao(31, "The Art of Computer Programming Vol. 2", "Livros", 2, 200.00),
			new ProdutoPadrao(32, "The Art of Computer Programming Vol. 3", "Livros", 4, 270.00)
		};
	} 

	public static void main(String [] args) {
	
		Produto [] produtos = carregaProdutos();

		GeradorDeRelatorios gdr;

		List<Double> intervalo = new LinkedList<Double>();
		intervalo.add(10.0);
		intervalo.add(100.0);

		gdr = new GeradorDeRelatorios(	produtos, quick_sort, preco_decresc,  
						preco_intervalo_de, intervalo);
		
		try{
			gdr.geraRelatorio("saida.html");
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
	}
}




/*if(selecionado){

				out.print("<li>");

				if((format_flags & FORMATO_ITALICO) > 0){

					out.print("<span style=\"font-style:italic\">");
				}

				if((format_flags & FORMATO_NEGRITO) > 0){

					out.print("<span style=\"font-weight:bold\">");
				} 
			
				out.print(p.formataParaImpressao());

				if((format_flags & FORMATO_NEGRITO) > 0){

					out.print("</span>");
				} 

				if((format_flags & FORMATO_ITALICO) > 0){

					out.print("</span>");
				}

				out.println("</li>");
				count++;*/