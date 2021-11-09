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
	public void move(boolean b){
		if(b) {
			int tmp = this.leftToRight ? leftPosition.absc + 1 : leftPosition.absc - 1;
			this.leftPosition = new Case(tmp, leftPosition.ord);
		}
		this.addToGraphics();
	}
	public Case getPosition(){
		return this.leftPosition;
	}

	public boolean  needsDelete(){
		if (leftToRight && leftPosition.ord - length > game.width)
			return true;
		if(!leftToRight && leftPosition.ord + length < 0)
			return true;
		return false;
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
