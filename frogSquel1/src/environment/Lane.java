package environment;

import environment.Environment;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;
import java.util.Random;

import java.util.ArrayList;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars; // = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int moveTime = 0;

	private Random rand = new Random();

	public Lane(Game g, int ordonnee){
		this.ord = ordonnee;

		this.game = g;
		this.speed = g.minSpeedInTimerLoops;
		this.cars = new ArrayList<>();
		this.leftToRight = rand.nextBoolean();
		this.density = g.defaultDensity;
	}

	public void update() {
//		removeCar();
		mayAddCar();
		if(++moveTime >= speed) {
			moveTime = 0;
			for (Car c : cars)
				c.move();
		}
		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e

	}

	private void removeCar(){ // void ou bool a voir
		if (! cars.isEmpty()){
			while(cars.get(0).needsDelete()){
				cars.remove(0);
			}
		}
	}

//	public void setCars(){
//		cars = new ArrayList<Car>();
//		for(int i; )
//	}

	public boolean isSafe(Case c){
		if(c.ord != this.leftPosition)
	}

//	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()


	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		//this.isSafe(getFirstCase()) && this.isSafe(getBeforeFirstCase())
		if (this.isSafe(getFirstCase()) && this.isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
