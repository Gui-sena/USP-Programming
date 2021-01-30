import java.awt.Color;
import java.lang.reflect.*;

public class FxBall extends Ball implements IBall{

private double vetor[];
private int tam;
private int atual;

	public FxBall(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){

		super(cx, cy, width, height, color, speed, vx, vy);
		vetor = new double[400];
		tam = 1;
		atual = 1;
	}

	public void draw(){

		GameLib.setColor(getColor());
		double w = 0.0;
		int x = atual - 1;
		int y = atual;
		vetor[x] = getCx();
		vetor[y] = getCy();
		for(double h = 0.0; getHeight() > h; h += 0.1){
			if(x < 0 || y < 0){
				x = 398;
				y = 399;
			}
			if(x > tam || y > tam){
				x = tam-1;
				y = tam;
			}
			if (x % 2 == 0)
			GameLib.fillRect(takeCx(x), takeCy(y), getWidth() - w, getHeight() - h);   
			    
			w += 0.1;
			x--;
			y--;
		}
		if(tam < 399) tam += 2;
		atual +=2;
		if(atual>399) atual = 1;
		//System.out.println("direcao :" + getVx() + ", " + getVy() + " ");
		//System.out.println("posicao :" + getCx() + ", " + getCy() + " ");
	//System.out.println(x + " " + y);
	}
	public double takeCx(int z){

		//if(z == -1) z = 399;
		return vetor[z];
	}			

	public double takeCy(int z){

		//if(z == -1) z = 399;
		return vetor[z];
	}																				

}