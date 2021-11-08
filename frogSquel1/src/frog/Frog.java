package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {

	private Game game;
	private Case c;
	private Direction d;

	public Frog(Game g){
		this.game = g;
		c = new Case(g.width/2, 0);
	}

	public Case getPosition(){ return c; }
	public Direction getDirection(){ return d; }


	public void move(Direction d){
		if(d == Direction.up && c.ord + 1 < game.height){
			c = new Case(c.absc, c.ord + 1);
		}
		else if(d == Direction.down && c.ord - 1 >= 0){
			c = new Case(c.absc, c.ord -1);
		}
		else if(d == Direction.left && c.absc - 1 >= 0){
			c = new Case(c.absc - 1, c.ord);
		}
		else if(d == Direction.right && c.absc < game.width - 1){
			c = new Case(c.absc + 1, c.ord);
		}
	}
}
