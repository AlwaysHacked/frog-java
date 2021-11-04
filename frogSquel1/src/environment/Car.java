package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

import java.util.Random;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	private Random rand = new Random();
	//TODO Constructeur(s)
	public Car (Game g, Case c, boolean b){
		this.game = g;
		this.leftPosition = c;
		this.leftToRight = b;
		this.length = rand.nextInt(3) + 1;
	}
	
	//TODO : ajout de methodes
	public void move(){
		if(this.leftToRight)
			this.leftPosition = new Case(leftPosition.absc + 1, leftPosition.ord);
		else
			this.leftPosition = new Case(leftPosition.absc - 1, leftPosition.ord);
	}


	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
