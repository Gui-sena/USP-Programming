public class Desc_subString implements Filtro{

	public Desc_subString(){
	}

	public boolean filtra( Produto produto, Object argFiltro ) {

		String stg = (String) argFiltro;
		String comp = produto.getDescricao();
		char letra1;
		char letra2;
		int i = 0;
		//for (int i = 0; i < stg.length(); i++){
			letra1 = stg.charAt(i);
			for (int j = 0; j < comp.length(); j++){
				letra2 = comp.charAt(j);
				if (letra1 == letra2){
					char teste1 = letra1;
					char teste2 = letra2;
					int l = j;
					int k = i;
					for (k = i; k < stg.length(); k++){
						if (l < comp.length())
							if ( stg.charAt(k) == comp.charAt(l)){
								if ( k == stg.length()-1){
									return true;
								}
							}
						else
							k = stg.length()+1;
						l++;		
					}
				}
			}
		return false;
	}
}