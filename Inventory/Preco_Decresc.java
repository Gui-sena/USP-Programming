public class Preco_Decresc implements Crit_Ord {

	public Preco_Decresc(){
	}

	public void ordena_parte02(int ini, int j, Produto x, Produto[] produtos){

		while(j >= ini){

			if( produtos[ini].getPreco() < produtos[j].getPreco() ){
			
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

			} while(produtos[j].getPreco() < x.getPreco());

			do{
				i++;

			} while(produtos[i].getPreco() > x.getPreco());
		
			if(i < j){
				Produto temp = produtos[i];
				produtos[i] = produtos[j]; 				
				produtos[j] = temp;
			}
			else return j;
		}
	}
}