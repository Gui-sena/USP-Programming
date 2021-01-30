import java.awt.Color;
import java.lang.reflect.*;
import java.util.*;
import java.lang.*;
import java.util.Random;

/**
	Classe que gerencia uma ou mais bolas presentes em uma partida. Esta classe é a responsável por instanciar 
	e gerenciar a bola principal do jogo (aquela que existe desde o ínicio de uma partida), assim como eventuais 
	bolas extras que apareçam no decorrer da partida. Esta classe também deve gerenciar a interação da(s) bola(s)
	com os alvos, bem como a aplicação dos efeitos produzidos para cada tipo de alvo atingido.
*/

public class BallManager {

	/**
		Atributo privado que representa a bola principal do jogo.	
	*/

	private IBall theBall = null;

	/**
		Atributo privado que representa o tipo (classe) das instâncias de bola que serão criadas por esta classe.
	*/

	private Class<?> ballClass = null;            

	private IBall dupBall = null;             // Atributo que representa uma bola duplicata

	private long dupTime = 0;               // Tempo de vida de duplicata
	
	private double origSpeed = 0;                // Atributo que respresenta a velocidade original da MAINball
	
	private long boostTime = -1;              // Tempo que a bola já passou com o Boost

	private long boostDuration = 0;            // Tempo que ela deve passar com o Boost

	private Random random = new Random();              // Número aleatório 

	private List<IBall> duplicatas = new LinkedList<IBall>();      // Conjunto das bolas duplicatas

	private List<Long> tempoDeVida = new LinkedList<Long>();       // Lista do tempo de vida das duplicatas


	/**
		Construtor da classe BallManager.
		
		@param className nome da classe que define o tipo das instâncias de bola que serão criadas por esta classe. 
	*/

	public BallManager(String className){

		try{
			ballClass = Class.forName(className);
		}
		catch(Exception e){

			System.out.println("Classe '" + className + "' não reconhecida... Usando 'Ball' como classe padrão.");
			ballClass = Ball.class;
		}
	}

	/**
		Recebe as componetes x e y de um vetor, e devolve as componentes x e y do vetor normalizado (isto é, com comprimento igual a 1.0).
	
		@param x componente x de um vetor que representa uma direção.
		@param y componente y de um vetor que represetna uma direção.

		@return array contendo dois valores double que representam as componentes x (índice 0) e y (índice 1) do vetor normalizado (unitário).
	*/
	private double [] normalize(double x, double y){

		double length = Math.sqrt(x * x + y * y);

		return new double [] { x / length, y / length };
	}
	
	/**
		Cria uma instancia de bola, a partir do tipo (classe) cujo nome foi passado ao construtor desta classe.
		O vetor direção definido por (vx, vy) não precisa estar normalizado. A implemntação do método se encarrega
		de fazer a normalização.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
		@param vx componente x do vetor (não precisa ser unitário) que representa a direção da bola.
		@param vy componente y do vetor (não precisa ser unitário) que representa a direção da bola.
	*/

	private IBall createBallInstance(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){

		IBall ball = null;
		double [] v = normalize(vx, vy);

		try{
			Constructor<?> constructor = ballClass.getConstructors()[0];
			ball = (IBall) constructor.newInstance(cx, cy, width, height, color, speed, v[0], v[1]);
		}
		catch(Exception e){

			System.out.println("Falha na instanciação da bola do tipo '" + ballClass.getName() + "' ... Instanciando bola do tipo 'Ball'");
			ball = new Ball(cx, cy, width, height, color, speed, v[0], v[1]);
		}

		return ball;
	} 

	/**
		Cria a bola principal do jogo. Este método é chamado pela classe Pong, que contem uma instância de BallManager.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
		@param vx componente x do vetor (não precisa ser unitário) que representa a direção da bola.
		@param vy componente y do vetor (não precisa ser unitário) que representa a direção da bola.
	*/

	public void initMainBall(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){

		theBall = createBallInstance(cx, cy, width, height, color, speed, vx, vy);
		origSpeed = speed;
	}

	/**
		Método que desenha todas as bolas gerenciadas pela instância de BallManager.
		Chamado sempre que a(s) bola(s) precisa ser (re)desenhada(s).
	*/

	public void draw(){

		theBall.draw();
		if (duplicatas.isEmpty() == false){
			for(Iterator<IBall> it = duplicatas.iterator(); it.hasNext();){
				dupBall = it.next();
				dupBall.draw();
			}
		}
	}
	
	/**
		Método que atualiza todas as bolas gerenciadas pela instância de BallManager, em decorrência da passagem do tempo.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
	
		theBall.update(delta);
		if (boostTime >= 0){
			if (boostTime <= 500L)
				boostTime += delta;
			else {
				theBall.setSpeed(origSpeed);
				boostTime = -1;
			}
		}
		if (duplicatas.isEmpty() == false){
			for(Iterator<IBall> it = duplicatas.iterator(); it.hasNext();){
				it.next().update(delta);
			}
		}
		if (tempoDeVida.isEmpty() == false){
			    int i;
			    for (i = 0; i < tempoDeVida.size(); i++){                                                     //for(Iterator<Long> it = tempoDeVida.iterator(); it.hasNext();){
				dupTime = tempoDeVida.get(i);                                                             //dupTime = it.next();
				if (dupTime >= 5000L){
					tempoDeVida.remove(i);
					duplicatas.remove(i); //System.out.print("removeu");
				}
				else{
					dupTime += delta;//System.out.print(dupTime + " ");
					tempoDeVida.set(i, dupTime);
				}
			}
		}
		
	}
	
	/**
		Método que processa as colisões entre as bolas gerenciadas pela instância de BallManager com uma parede.

		@param wall referência para uma instância de Wall para a qual será verificada a ocorrência de colisões.
		@return um valor int que indica quantas bolas colidiram com a parede (uma vez que é possível que mais de 
		uma bola tenha entrado em contato com a parede ao mesmo tempo).
	*/

	public int checkCollision(Wall wall){

		int hits = 0;

		if (theBall.checkCollision(wall)) hits++;
		if (duplicatas.isEmpty() == false){
			for(Iterator<IBall> it = duplicatas.iterator(); it.hasNext();){
				if(it.next().checkCollision(wall)) hits++;
			}
		}

		return hits;
	}

	/**
		Método que processa as colisões entre as bolas gerenciadas pela instância de BallManager com um player.

		@param player referência para uma instância de Player para a qual será verificada a ocorrência de colisões.
	*/
	
	public void checkCollision(Player player){

		theBall.checkCollision(player);
		if (duplicatas.isEmpty() == false){
			for(Iterator<IBall> it = duplicatas.iterator(); it.hasNext();){
				it.next().checkCollision(player);
			}
		}
	}

	/**
		Método que processa as colisões entre as bolas gerenciadas pela instância de BallManager com um alvo.

		@param target referência para uma instância de Target para a qual será verificada a ocorrência de colisões.
	*/

	public void checkCollision(Target target){

		int java = 0;
		if (theBall.checkCollision(target) == true){
			if (theBall.getCy() < 250 || theBall.getCy() > 450){
				if (theBall.getSpeed() != origSpeed * 1.5){
					theBall.setSpeed(theBall.getSpeed() * 1.5);
					boostTime = 0;
					//boostDuration = 0.5;
					//System.out.print(theBall.getCy() + " ");
				}
			}
			else {
				double ale1 = random.nextDouble();
				double ale2 = random.nextDouble();
				double sorte = random.nextDouble();
				if (sorte > 0.5){
					ale1 = -(ale1);
					ale2 = -(ale2);
				}
				dupBall = createBallInstance(target.getCx(), target.getCy(), theBall.getWidth(), theBall.getHeight(), Color.RED, theBall.getSpeed(), 
					ale1, ale2);
				duplicatas.add(dupBall);
				long nl = 0;
				tempoDeVida.add(nl);//System.out.print("removeu");

			}
		}
		if (duplicatas.isEmpty() == false){
			for(Iterator<IBall> it = duplicatas.iterator(); it.hasNext();){
				dupBall = it.next();
				if (dupBall.checkCollision(target) == true){
					if (dupBall.getCy() < 250 || dupBall.getCy() > 450){
						/*if (dupBall.getSpeed() != origSpeed * 1.5){
							dupBall.setSpeed(theBall.getSpeed() * 1.5);
							long nl = 0;
							tempoDeBoost[0] = nl;
					//boostDuration = 0.5;
					//System.out.print(theBall.getCy() + " ");
						}*/
					}
					else {
						java++;
						long nl = 0;
						tempoDeVida.add(nl);//System.out.print("removeu");

					}
				}
			}
		}
		if (java > 0){
			for (int i = 1; i <= java; i++){
				double ale1 = random.nextDouble();
				double ale2 = random.nextDouble();
				double sorte = random.nextDouble();
				if (sorte > 0.5){
					ale1 = -(ale1);
					ale2 = -(ale2);
				}
				dupBall = createBallInstance(target.getCx(), target.getCy(), theBall.getWidth(), theBall.getHeight(), Color.RED, origSpeed, 
											ale1, ale2);  //System.out.print(ale1 + "-" + ale2 + " ");

				duplicatas.add(dupBall);
			}
		}
	}
}


