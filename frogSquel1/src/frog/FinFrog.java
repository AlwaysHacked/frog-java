package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class FinFrog extends Frog implements IFrog{
    public FinFrog(Game g) {
        super(g);
    }

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

    @Override
    public int getScore() {
        return 0;
    }
}
