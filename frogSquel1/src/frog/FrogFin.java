package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class FrogFin extends Frog implements IFrog{
    public FrogFin(Game g) {
        super(g);
    }

    public void move(Direction d){
        if(d == Direction.up && c.ord + 1 < game.height){
            c = new Case(c.absc, c.ord + 1);
            this.game.incrementeScore();
        }
        else if(d == Direction.down && c.ord - 1 >= 0){
            c = new Case(c.absc, c.ord -1);
            this.game.decrementeScore();
        }
        else if(d == Direction.left && c.absc - 1 >= 0){
            c = new Case(c.absc - 1, c.ord);
        }
        else if(d == Direction.right && c.absc < game.width - 1){
            c = new Case(c.absc + 1, c.ord);
        }

        if(this.game.getScore() == this.maxScore + 1)
            maxScore++;
    }

    @Override
    public int getScore() {
        return maxScore;
    }
}
