public class Insertion_sort implements Alg_Ord{

	public Insertion_sort(){
	}

	public void ordena(int ini, int fim, Produto[] produtos, Crit_Ord crit){

		for(int i = ini; i <= fim; i++){

			Produto x = produtos[i];				
			crit.ordena_parte02(i, fim, x, produtos);
		}
	}
}