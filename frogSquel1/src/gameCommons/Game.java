package gameCommons;

import java.awt.*;
import java.util.*;
import environment.*;

import frog.FrogInf;
import graphicalElements.*;
//import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
//	public final int frogMaxHeight;
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	private int score = 0;


	// Lien aux objets utilis�s
	private IEnvironment environment; // IEnvironment
	private IFrog frog; // IFrog
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		//this.frogMaxHeight = frogMaxHeight;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose(long time) {
		if(!environment.isSafe(frog.getPosition())){
				graphic.endGameScreen(Integer.toString(frog.getScore()) + " points in " + String.valueOf(time) + " seconds");
			return true;
		}
//		toString(frog.getScore());
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin(long time) {
		if(environment.isWinningPosition(frog.getPosition())){
			graphic.endGameScreen("Level completed in " + String.valueOf(time) + " seconds");
			return true;
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	boolean playing = true; //  not print every second

	public void update() {
		long time = (System.currentTimeMillis() - environment.getTime()) / 1000;

		if(playing)
			if (testLose(time) || testWin(time))
				playing = false;

		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
	}

	public int getScore() {
		return score;
	}

	public void incrementeScore(){
		this.score++;
	}

	public void decrementeScore(){
		this.score--;
	}
}