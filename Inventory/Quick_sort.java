public class Quick_sort implements Alg_Ord{

	public Quick_sort(){
	}

	public void ordena(int ini, int fim, Produto[] produtos, Crit_Ord crit){

		if(ini < fim) {

				int q = crit.particiona(ini, fim, produtos);
				
				ordena(ini, q, produtos, crit);
				ordena(q + 1, fim, produtos, crit);
			}
	}
}