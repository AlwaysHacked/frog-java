package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import frog.Frog;
//import givenEnvironment.GivenEnvironment;
import frog.FrogInf;
import environment.EnvInf;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

import environment.Environment;

public class Main {
	public static void main(String[] args) {

		//Caract�ristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 1;
		double defaultDensity = 0.05;
		
		//Cr�ation de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Cr�ation de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);

		boolean jeuInfini = false;

		IEnvironment env;
		IFrog frog;

//		if(jeuInfini) {
			env = new EnvInf(game);
			frog = new FrogInf(game, env);
//		}
//		else{
//			env = new Environment(game) {};
//			frog = new Frog(game, env);
//		}
		game.setFrog(frog);
		graphic.setFrog(frog);
		//Cr�ation et liaison de l'environnement
		game.setEnvironment(env);
		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});
		long start = System.currentTimeMillis();
		timer.setInitialDelay(0);
		timer.start();
	}
}
