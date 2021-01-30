import java.util.Scanner;

public class Narciso {
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		int n = Integer.parseInt(input.nextLine());

		for(int i = 0; i < n; i++){

			String conta = input.nextLine();
			String[] nar = {"n", "a", "r", "c", "i", "s", "o"};

			int op = -1;
			int n1 = 0;
			int n2 = 0;

			for(int j = 0; j < nar.length; j++){
				if(conta.contains(nar[j])){
					int sinal = conta.indexOf(nar[j]);

					n1 = Integer.parseInt(conta.substring(0, sinal));
					n2 = Integer.parseInt(conta.substring(sinal + 1, conta.length()));

					op = j;

					break;
				}
			}

			switch(op){
				case 0:
					System.out.println(n1+n2);
					break;

				case 1:
					System.out.println(n1-n2);
					break;

				case 2:
					System.out.println(n1*n2);
					break;

				case 3:
					System.out.println(n1/n2);
					break;

				case 4:
					System.out.println(fatorial(n1)/fatorial(n2));
					break;

				case 5:
					System.out.println(n1%n2);
					break;

				case 6:
					int p = (int)Math.pow(n1, n2);
					System.out.println(p);
					break;
			}
		}
	}

	public static int fatorial(int n){

		int mul = 1;

		for(int i = 2; i <= n; i++)
			mul *= i;

		return mul;
	}
}