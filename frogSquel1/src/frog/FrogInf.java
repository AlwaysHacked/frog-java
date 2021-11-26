package frog;

import environment.EnvInf;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class FrogInf extends Frog implements IFrog {
    private final int maxHeight = 6;
    private IEnvironment environment;
    private int maxScore = 0;

    public FrogInf(Game g, IEnvironment env) {
        super(g);
        this.environment = env;
    }

    public void move(Direction d) {
        if (d == Direction.up) {
            if (this.c.ord >= maxHeight) {
                this.c = new Case(this.c.absc, maxHeight);
                this.environment.moveLanes(this.game);
                this.game.incrementeScore();
            } else {
                this.c = new Case(this.c.absc, this.c.ord + 1);
                this.game.incrementeScore();
            }
        } else if (d == Direction.down && this.c.ord > 0) {
            this.game.decrementeScore();
            this.c = new Case(this.c.absc, this.c.ord - 1);
        } else if (d == Direction.left && this.c.absc - 1 >= 0) {
            this.c = new Case(this.c.absc - 1, this.c.ord);
        } else if (d == Direction.right && this.c.absc < this.game.width - 1) {
            this.c = new Case(this.c.absc + 1, this.c.ord);
        }

        if(this.game.getScore() == this.maxScore + 1)
            maxScore++;
    }

    public int getScore() {
        return maxScore;
    }
}
