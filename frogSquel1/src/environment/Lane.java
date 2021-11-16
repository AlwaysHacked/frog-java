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
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int moveTime = 0;

	private Random rand = new Random();

	public Lane(Game g, double density, int ordonnee){
		this.ord = ordonnee;

		this.game = g;
		this.speed = rand.nextInt(2) + 1;
		this.leftToRight = rand.nextBoolean();
		this.density = density;
	}

	public ArrayList<Car> getCars(){
		return this.cars;
	}

	private void moveAllCars(boolean b){
		for (Car c : cars)
			c.move(b);
	}

	public void update() {
		moveTime++;
		if(moveTime >= speed) {
			moveTime = 0;
			this.moveAllCars(true);
		}
		else this.moveAllCars(false);
		removeCar();
		mayAddCar();
	}

	private void removeCar(){ // void ou bool a voir
		while(! cars.isEmpty() && cars.get(0).needsDelete()){
			cars.remove(0);
		}
	}


	public boolean isSafe(Case c){;
		for(Car car : cars)
			if(car.isOnPosition(c))
				return false;
		return true;
	}


//	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
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
