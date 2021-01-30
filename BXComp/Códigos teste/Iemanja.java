import java.util.Scanner;
import java.lang.Math;

public class Iemanja {
	public static void main(String[] args){

		Scanner input = new Scanner(System.in); 

		while(true){

			String s = input.nextLine();
			String[] entrada = s.split("!");
			String[] caminho01 = entrada[0].split(",");
			String[] caminho02 = entrada[1].split(",");
			String[] caminho03 = entrada[2].split(",");
			int[] caminho1 = numerando(caminho01);
			int[] caminho2 = numerando(caminho02);
			int[] caminho3 = numerando(caminho03);
			int c1 = 0;
			int c2 = 0;
			int c3 = 0;
			double mediac1 = media(caminho1);
			double mediac2 = media(caminho2);
			double mediac3 = media(caminho3);
			if (mediac1 < mediac2 && mediac1 < mediac3)
				c1++;
			if (mediac2 < mediac1 && mediac2 < mediac3)
				c2++;
			if (mediac3 < mediac2 && mediac3 < mediac1)
				c3++;

			if (mediac1 == 0 && mediac2 == 0 && mediac3 == 0){
				System.out.println("Bem vinda ao lar, oh rainha do mar!");
				System.exit(0);
			}
			
			double desvio1 = desvio(caminho1);
			double desvio2 = desvio(caminho2);
			double desvio3 = desvio(caminho3);
			int menor = 0;
			if (desvio1 < desvio2 && desvio1 < desvio3){
				menor = 1;
				c1++;
			}
			if (desvio2 < desvio1 && desvio2 < desvio3){
				menor = 2;
				c2++;
			}
			if (desvio3 < desvio2 && desvio3 < desvio1){
				menor = 3;
				c3++;
			}

			int tamanho1 = caminho1.length;
			int tamanho2 = caminho2.length;
			int tamanho3 = caminho3.length;
			if (tamanho1 < tamanho2 && tamanho1 < tamanho3)
				c1++;
			else if(tamanho2 < tamanho1 && tamanho2 < tamanho3)
				c2++;
			else if(tamanho3 < tamanho2 && tamanho3 < tamanho1)
				c3++;
			else if(tamanho3 > tamanho2 && tamanho1 < tamanho3){
				c1++;
				c2++;
			}
			else if(tamanho1 < tamanho2 && tamanho3 < tamanho2){
				c1++;
				c3++;
			}
			else if(tamanho1 > tamanho2 && tamanho1 > tamanho3){
				c2++;
				c3++;
			}
			else {
				c1++;
				c2++;
				c3++;
			}
			if (c1 > c2 && c1 > c3)
				System.out.println("Continue pelo primeiro caminho, Iemanja!");
			else if  (c2 > c1 && c2 > c3)
				System.out.println("Continue pelo segundo caminho, Iemanja!");
			else if(c3 > c2 && c3 > c1)
				System.out.println("Continue pelo terceiro caminho, Iemanja!");
			else if(menor == 1)
				System.out.println("Continue pelo primeiro caminho, Iemanja!");
			else if (menor == 2)
				System.out.println("Continue pelo segundo caminho, Iemanja!");
			else 
				System.out.println("Continue pelo terceiro caminho, Iemanja!");
		}
	}

	public static int[] numerando(String[] caminho){

		int[] novoC = new int[caminho.length];
		for (int i = 0; i < caminho.length; i++){
			novoC[i] = Integer.parseInt(caminho[i]);
		}
		return novoC;
	}

	public static double media(int[] caminho){

		int media = 0;
		for (int i = 0; i < caminho.length; i++){
			media += caminho[i];
		}
		return media/caminho.length;
	}

	public static double desvio(int[] caminho){

		double desvio01 = 0;
		for (int i = 0; i < caminho.length; i++){
			desvio01 += Math.pow(media(caminho) - (double)caminho[i], 2.0);
		}
		double desvio = desvio01/caminho.length;
		desvio01 = Math.sqrt(desvio);
		return desvio01;
	}
}