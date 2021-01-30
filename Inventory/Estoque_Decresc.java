public class Estoque_Decresc implements Crit_Ord {

	public Estoque_Decresc(){
	}

	public void ordena_parte02(int ini, int j, Produto x, Produto[] produtos){

		while(j >= ini){

			if( produtos[ini].getQtdEstoque() < produtos[j].getQtdEstoque() ){
			
				Produto temp = produtos[ini];
				produtos[ini] = produtos[j]; 				
				produtos[j] = temp;
			}
			j--;
		}
	}


	public int particiona(int ini, int fim, Produto[] produtos){
		
		Produto x = produtos[ini];
		int i = (ini - 1);
		int j = (fim + 1);
		
		while(true){
			do{ 
				j--;

			} while(produtos[j].getQtdEstoque() < x.getQtdEstoque());
			
			do{
				i++;

			} while(produtos[i].getQtdEstoque() > x.getQtdEstoque());
		
			if(i < j){
				Produto temp = produtos[i];
				produtos[i] = produtos[j]; 				
				produtos[j] = temp;
			}
			else return j;
		}
	}
}