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
		int tmp = this.leftToRight ? leftPosition.absc + 1 : leftPosition.absc - 1;
		this.leftPosition = new Case(tmp, leftPosition.ord);
		this.addToGraphics();

//		if(this.leftToRight)
//			this.leftPosition = new Case(leftPosition.absc + 1, leftPosition.ord);
//		else
//			this.leftPosition = new Case(leftPosition.absc - 1, leftPosition.ord);
	}

	public boolean needsDelete(){
		if (leftToRight && leftPosition.absc - length > game.width)
			return true;
		if(!leftToRight && leftPosition.absc + length < game.width)
			return true;
		return false;
	}

	public boolean coversCase(Case pos) {
		if (pos.ord != this.leftPosition.ord) {
			return false;
		} else {
			return pos.absc >= this.leftPosition.absc && pos.absc < this.leftPosition.absc + this.length;
		}
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
