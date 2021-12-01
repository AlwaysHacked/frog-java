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

	private Piege p = null;
	private Tunnel t = null;
	int r = rand.nextInt(9);


	public Lane(Game g, double density, int ordonnee){
		this.ord = ordonnee;
		this.game = g;
		this.speed = rand.nextInt(3) + 2;
		this.leftToRight = rand.nextBoolean();
		this.density = density;

		if(r == 0 && this.ord!=0) {
			p = new Piege(new Case(rand.nextInt(this.game.width), this.ord), this.game);
			p.addGraphics();
		}
		else if(r == 1 && this.ord!=0) {
			t = new Tunnel(new Case(rand.nextInt(this.game.width), this.ord), this.game);
			t.addGraphics();
		}
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
		if(r == 0) {
			p.addGraphics();
		}
		else if(r == 1) {
			t.addGraphics();
		}
		removeCar();
		mayAddCar();
	}

	private void removeCar(){ // void ou bool a voir
		while(! cars.isEmpty() && cars.get(0).needsDelete()){
			cars.remove(0);
		}
	}

	public boolean isSafe(Case c) {
		;
		for (Car car : cars){
			if (car.isOnPosition(c)){
				if(t!=null) {
					if (c.absc==t.getPos().absc) {
						return true;
					}
				}
				return false;
			}
		}
		if(p!=null) {
			if (c.absc==p.getPos().absc) return false;
		}
		return true;
	}

	public void moveOneLaneToUp(){
		this.ord++;
		for (Car c : cars){
			c.moveOneCarToUp();
		}
		if(r == 0 && p!=null)
			p = new Piege (new Case(p.getPos().absc, this.ord), this.game);
		else if(r == 1 && t!=null)
			t = new Tunnel(new Case(t.getPos().absc, this.ord), this.game);

	}
	public void moveOneLaneToDown(){
		this.ord--;
		for (Car c : cars){
			c.moveOneCarToDown();
		}
		if(r == 0 && p!=null)
			p = new Piege (new Case(p.getPos().absc, this.ord), this.game);
		else if(r == 1 && t!=null)
			t = new Tunnel(new Case(t.getPos().absc, this.ord), this.game);
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
