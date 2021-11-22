package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
    protected ArrayList<Lane> voies;
    protected Game game;

//    public Lane getVoie(int ord){
//        return this.voies.get(ord);
//    }

    public Environment(Game g){
        voies = new ArrayList<>();

        voies.add(new Lane(g, 0, 0));
        for(int i = 1; i < g.height - 1; i++)
            voies.add(new Lane(g, g.defaultDensity, i));
        voies.add(new Lane(g, 0, g.height));

        this.game = g;
    }

    public Environment() {
    }

    @Override
    public boolean isSafe(Case c){
        return voies.get(c.ord).isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case aCase) {
        return this.game.height - 1 == aCase.ord ;
    }

    @Override
    public void update() {
        for(Lane l : voies)
            l.update();
    }
}
