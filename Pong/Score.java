import java.awt.*;
import java.util.*;
import java.lang.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {

	private int pontos;
	private int pontosX;
	private String playerId;

	/**
		Construtor da classe Score.

		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/

	public Score(String playerId){
		
		this.playerId = playerId;
		this.pontos = 0;
		this.pontosX = 0;
	}

	/**
		Método de desenho do placar.
	*/

	public void draw(){
		if(playerId.equals("Player 1")){
			GameLib.setColor(Color.GREEN);
			GameLib.drawText(playerId + " : " + getScore(), 70, GameLib.ALIGN_LEFT);	}		
		else{
			GameLib.setColor(Color.BLUE);
			GameLib.drawText(playerId + " : " + getScore(), 70, GameLib.ALIGN_RIGHT);}	
	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){
		
		if(playerId.equals("Player 1")){
			GameLib.setColor(Color.GREEN);
			GameLib.drawText(playerId + " : " + (getScore()+1), 70, GameLib.ALIGN_LEFT);	}		
		else{
			GameLib.setColor(Color.BLUE);
			GameLib.drawText(playerId + " : " + (getScore()+1), 70, GameLib.ALIGN_RIGHT);}	
		pontos++;
	}

	public void incX(){
		
		this.pontosX = pontosX++;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){

		return pontos;
	}

	public String getPontos(){

		return Integer.toString(pontos);
	}

	public String getPontosX(){

		return Integer.toString(pontosX);
	}
}
