import java.util.Scanner;

public class Sereia {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        int n = Integer.parseInt(input.nextLine());

        String[] chaves = {"dor", "medo", "morte"};

        for(int i = 0; i < n; i++){
            String palavra = "";
            int check = 0;

            String entrada = (input.nextLine()).toLowerCase();

                if(entrada.contains("morte")){
                	palavra = "morte";
                	int[] resp = new int[5];
                	resp = checkPalavra("morte", entrada);
                	System.out.println(resp[0] + "-" + resp[1] + "-" + resp[2] + "-" + resp[3] + "-" 
                		+ resp[4] + " " + palavra );
                	check = 1;
                }
            	if(entrada.contains("medo") && check != 1){
                	palavra = "medo";
                	int[] resp = new int[4];
                	resp = checkPalavra("medo", entrada);
                	System.out.println(resp[0] + "-" + resp[1] + "-" + resp[2] + "-" + resp[3] + " " + palavra );
                	check = 1;
                }
                if(entrada.contains("dor") && check != 1){
                	palavra = "dor";
                	int[] resp = new int[3];
                	resp = checkPalavra("dor", entrada);
                	System.out.println(resp[0] + "-" + resp[1] + "-" + resp[2] + " " + palavra );
                }

        }
    }

    public static int[] checkPalavra(String chave, String entrada){
    	int[] ump = new int[5];
    	int p = 0;
    	int j;
    	int q;
    	char ch = chave.charAt(p);
    	for(int i = 0; i<entrada.length(); i++){
    		int[] num = new int[5];
    		p = 0;
    		int acertando = 0;
    		if(entrada.charAt(i) == chave.charAt(p)){
    			acertando++;
    			num[0] = i;
    		}
    		j = i;
    		q = p;
    		while(acertando>0 && acertando<chave.length()){
    			j++;
    			q++;
    			if(entrada.charAt(j) == chave.charAt(q)){
    				acertando++;
    				num[q] = j;
    			}
    			else
    				acertando = 0;
    		}
    		if(acertando==chave.length())
    			return num;
    	}
    	return ump;
    }
}