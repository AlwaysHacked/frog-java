package environment;

import environment.Environment;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;
import java.util.Random;

import CaseSpec.*;
import java.util.ArrayList;

public class Lane {
	private Random rand = new Random();

	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int moveTime = 0;

	private final int densiteCaseSpecial = 100;
	private final int densitePiege = 7;
	private final int densiteCiel = 3;

	private Piege p = null;
	private Sky s = null;
	int r = rand.nextInt(densiteCaseSpecial);

	public Lane(Game g, double density, int ordonnee){
		this.ord = ordonnee;
		this.game = g;
		this.speed = rand.nextInt(3) + 2;
		this.leftToRight = rand.nextBoolean();
		this.density = density;

		if(r <= densitePiege && this.density != 0) {
			p = new Piege(new Case(rand.nextInt(this.game.width), this.ord), this.game);
			p.addGraphics();
		}
		else if(r <= densitePiege + densiteCiel && this.density != 0) {
			s = new Sky(new Case(rand.nextInt(this.game.width), this.ord), this.game);
			s.addGraphics();
		}
		else if(this.density == 0)
			r = densiteCiel + densitePiege + 1;
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
		if(p != null)
			p.addGraphics(); // parfois null -> erreur

		else if(s != null)
			s.addGraphics(); // parfois null -> erreur

		removeCar();
		mayAddCar();
	}

	private void removeCar(){ // void ou bool a voir
		while(! cars.isEmpty() && cars.get(0).needsDelete()){
			cars.remove(0);
		}
	}



	public boolean isSafe(Case c) {
		if(s != null && s.isOnAbsc(c)) // sky is safe
			return true;

		for (Car car : cars){ // car on a position not safe
			if (car.isOnPosition(c))
				return false;
		}

		if(p != null && p.isOnAbsc(c)) // piege not safe
			return false;

		return true;
	}

	public void moveOneLaneToUp(){
		this.ord++;
		for (Car c : cars){
			c.moveOneCarToUp();
		}

		if(p != null)
			p = new Piege (new Case(
					p.getPos().absc, this.ord), this.game
			);

		else if(s != null)
			s = new Sky(new Case(
					s.getPos().absc, this.ord), this.game
			);
	}

	public void moveOneLaneToDown(){
		this.ord--;
		for (Car c : cars){
			c.moveOneCarToDown();
		}
		if(p != null)
			p = new Piege (new Case(
					p.getPos().absc, this.ord), this.game
			);

		else if(s != null)
			s = new Sky(new Case(
					s.getPos().absc, this.ord), this.game
			);
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
