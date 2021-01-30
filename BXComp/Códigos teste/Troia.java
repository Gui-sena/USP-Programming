import java.util.Scanner;

public class Troia {
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		while(true){

			String s = input.next();
			int n = input.nextInt();

			if(s.contains("C")){
				System.out.println("Ah que mal deve ser receber um presente de grego?");
				System.exit(0);
			}

			int b = s.length();

			if(s.contains("A")){
				b+=9;

				if(b >= n)
					System.out.println("Eu que nao fico no caminho dele!");
				else{
					System.out.println("Atacar!");
				}

				continue;
			}

			if(b >= n)
				System.out.println("Recuar!");

			else
				System.out.println("Atacar!");
		}
	}
}