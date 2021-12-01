package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public abstract class Frog implements IFrog {
	protected Game game;
	protected Case c;
	protected Direction d;
	protected int maxScore = 0;

	public Frog(Game g){
		this.game = g;
		c = new Case(g.width/2, 0);
	}

	public Case getPosition(){ return c; }
	public Direction getDirection(){ return d; }

	public abstract void move(Direction d);
	
}
