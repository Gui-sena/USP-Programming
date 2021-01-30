import java.awt.*;
import java.util.*;
import java.lang.*;
import java.util.Random;
/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/


public class Ball {

	private double cx;
	private double cy;
	private double width;
	private double height;
	private Color color;
	private double speed;
	private double direcao;
	private int ver;
	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
	
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;
		Random random = new Random();
		this.direcao = 0;//random.nextInt(8) * 45);
		this.ver = 0;
	}


	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){

		GameLib.setColor(Color.YELLOW);
		GameLib.fillRect(cx, cy, width, height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){                              

		double pixels = speed * delta;
		for (int i = 0; i < pixels; i++){                          //fazer apenas for e colocar os ifs dentro
			
			if (this.direcao == 0){
				this.cx = cx;
				this.cy = cy - 1;
			}
			if (this.direcao == 45){
			this.cx = cx + 1;
			this.cy = cy - 1;
				
			}
			if (this.direcao == 90){
			this.cx = cx + 1;
			this.cy = cy;
				
			}
			if (this.direcao == 135){
			this.cx = cx + 1;
			this.cy = cy + 1;
				
			}	
			if (this.direcao == 180){
			this.cx = cx;
			this.cy = cy - 1;
				
			}
			if (this.direcao == 225){
			this.cx = cx - 1;
			this.cy = cy + 1;
				
			}
			if (this.direcao == 270){
			this.cx = cx - 1;
			this.cy = cy;
				
			}
			if (this.direcao == 315){
			this.cx = cx - 1;
			this.cy = cy - 1;
				
			}
		}
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		if (playerId.equals("Player 1")){
			if (this.direcao == 225)
				this.direcao = 135;
			if (this.direcao == 315)
				this.direcao = 45;	
			if (this.direcao == 270)
				this.direcao = 45;		
		}
		if (playerId.equals("Player 2")){
			if (this.direcao == 135)
				this.direcao = 225;
			if (this.direcao == 45)
				this.direcao = 315;
			if (this.direcao == 90)
				this.direcao = 225;
		}
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		if (wallId.equals("Top")){
			if (this.direcao == 45 )
			this.direcao = 135;
			if (this.direcao == 315 )
			this.direcao = 225;
			if (this.direcao == 0 )
			this.direcao = 135;
		}
		if (wallId.equals("Bottom")){
			if (this.direcao == 135)
			this.direcao = 45;
			if (this.direcao == 225 )
			this.direcao = 315;
			if (this.direcao == 180 )
			this.direcao = 315;
		}
		if (wallId.equals("Left")){
			if (this.direcao == 225)
			this.direcao = 135;
			if (this.direcao == 315 )
			this.direcao = 45;
			if (this.direcao == 270 )
			this.direcao = 45;
		}
		if (wallId.equals("Right")){
			if (this.direcao == 135)
			this.direcao = 225;
			if (this.direcao ==  45)
			this.direcao = 315;
			if (this.direcao ==  90)
			this.direcao = 225;
		}
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		String wallId = wall.getId();
		if ( wallId.equals("Left") ){
			if (ver == 0){
			if ( (this.cx <= (wall.getCx() + wall.getWidth())) && (this.cy <= wall.getCy() + (wall.getHeight()/2) 
			&& (this.cy >= wall.getCy() - (wall.getHeight()/2) ))){ 
				ver = 1;
				return true;
			}
			}	
		}
		if (wallId.equals("Right")){
			if (ver == 0 ){
			if ( (this.cx >= (wall.getCx() - wall.getWidth()))  && (this.cy <= wall.getCy() + (wall.getHeight()/2) 
			&& (this.cy >= wall.getCy() - (wall.getHeight()/2) ))) {
				ver = 1;
				return true;
			}
			}
		}
		if ( wallId.equals("Top") ){
			if ( (this.cy <= (wall.getCy() + wall.getHeight()))  && (this.cx <= wall.getCx() + (wall.getWidth()/2) 
			&& (this.cx >= wall.getCx() - (wall.getWidth()/2) )) ) {
				ver = 0;
				return true; 
			}
		}
		if ( wallId.equals("Bottom")){
			if ( (this.cy >= (wall.getCy() -wall.getHeight()))  && (this.cx <= wall.getCx() + (wall.getWidth()/2) 
			&& (this.cx >= wall.getCx() - (wall.getWidth()) ))) {
				ver = 0;
				return true;
			}
		}
		return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		String playerId = player.getId();
		if ( playerId.equals("Player 1")){	
			if ( (this.cx <= (player.getCx() + player.getWidth()/2))  
			&& (this.cy <= player.getCy() + (player.getHeight()/2) 
			&& (this.cy >= player.getCy() - (player.getHeight()/2) ))) {
				ver = 0;
				return true;
			}

		}
		if ( playerId.equals("Player 2")){	
			if ( (this.cx >= (player.getCx() - player.getWidth()/2)) 
			&& (this.cy <= player.getCy() + (player.getHeight()/2) 
			&& (this.cy >= player.getCy() - (player.getHeight()/2) )))  {
				ver = 0;
				return true;
			}
		}
		return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){

		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){

		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){

		return this.speed;
	}

}
