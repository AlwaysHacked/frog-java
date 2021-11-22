package frog;

import environment.EnvInf;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

public class FrogInf extends Frog{
    private final int maxHeight = 4;
    private EnvInf environment;

    public FrogInf(Game g, EnvInf env) {
        super(g);
        c = new Case(g.width/2, maxHeight);
        this.environment = env;
    }

//    @Override
    public void move(Direction d){
        if(d == Direction.up){
            System.out.println(c.ord);
            if(c.ord >= maxHeight) {
                c = new Case(c.absc, maxHeight);
                environment.moveLanes(game, true);
            }
            else {
                this.c = new Case(c.absc, c.ord + 1);
                environment.moveLanes(game, false);
            }
        }
        else if(d == Direction.down && c.ord - 1 >= 0){
            c = new Case(c.absc, c.ord -1);
            if (c.ord == 0){
                c = new Case(c.absc, 0);
                environment.moveLanes(game, false);
            }
//            else

        }
        else if(d == Direction.left && c.absc - 1 >= 0){
            c = new Case(c.absc - 1, c.ord);
        }
        else if(d == Direction.right && c.absc < game.width - 1){
            c = new Case(c.absc + 1, c.ord);
        }
    }
}


