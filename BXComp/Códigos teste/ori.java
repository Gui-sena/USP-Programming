import java.util.Scanner;

public class ori {
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		int n = Integer.parseInt(input.nextLine());

		for(int i = 0; i < n; i++){

				
				String tam = input.next();
				String matrixS = input.next();
				int tamanho = Integer.parseInt(tam.substring(0,1));
				//System.out.println
				matrixS = matrixS.substring(1, matrixS.length()-1);
				String[] vetor = matrixS.split(",");
				String[][] matrix = matrixDeString(vetor, tamanho);
				boolean verifica = true;
				int resp1 = 0;

				while(verifica){
					int[] local = melhorLocal(matrix, tamanho);
					int pX = local[0];
					int pY = local[1];
					if (pX != -1){
						if (pX > 0){
							matrix[pX-1][pY] = "0";	
						}
						if (pX < tamanho-1){
							matrix[pX+1][pY] = "0";
						}
						if (pY < tamanho-1){
							matrix[pX][pY+1] = "0";
						}
						if (pY > 0){
							matrix[pX][pY-1] = "0";
						}
						resp1++;
					}
					else
						verifica = false;
				}
				System.out.print(resp1 + " ");
				System.out.println("1" + "X" + ((tamanho/2)+1));
		}
	}

	public static String[][] matrixDeString(String[] vetor, int tamanho){

		int cont = 0;
		String[][] matrix = new String[tamanho][tamanho];
		
		for (int i = 0; i < tamanho; i++){
			for (int j = 0; j < tamanho; j++){
				matrix[i][j] = vetor[cont];
				cont++;
			}
		}
		return matrix;
	}

	public static int[] melhorLocal(String[][] matrix, int tamanho){

		int mais1 = 0;
		int cont;
		int[] melhor = new int[2];
		
		for (int i = 0; i < tamanho; i++){
			for (int j = 0; j < tamanho; j++){
				cont = 0;
				if (i > 0){
					if (matrix[i-1][j].equals("1"))
						cont++;
					if (matrix[i-1][j].equals("Ori"))
						cont = cont-10;
				}
				if (i < tamanho-1){
					if (matrix[i+1][j].equals("1"))
						cont++;
					if (matrix[i+1][j].equals("Ori"))
						cont = cont-10;
				}
				if (j < tamanho-1){
					if (matrix[i][j+1].equals("1"))
						cont++;
					if (matrix[i][j+1].equals("Ori"))
						cont = cont-10;
				}
				if (j > 0){
					if (matrix[i][j-1].equals("1"))
						cont++;
					if (matrix[i][j-1].equals("Ori"))
						cont = cont-10;
				}
				if (cont > mais1){
					mais1 = cont;
					melhor[0] = i;
					melhor[1] = j;
				}
			}
		}
		if (mais1 == 0){
			melhor[0] = -1;
			melhor[1] = -1;
		}

		return melhor;
	}
}