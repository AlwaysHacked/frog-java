package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public abstract class Environment implements IEnvironment {
    protected ArrayList<Lane> voies = new ArrayList<>();
    protected Game game;

//    public Lane getVoie(int ord){
//        return this.voies.get(ord);
//    }

    public Environment(Game g){
//        voies = new ArrayList<>();
//        for(int i = 1; i < this.game.height - 1; i++)
//            voies.add(new Lane(this.game, this.game.defaultDensity, i));
//        voies.add(new Lane(this.game, 0, this.game.height));
        this.game = g;

        this.voies.add(new Lane(this.game, 0, 0));

        this.assignVoies();


    }

    void assignVoies()
    {
        for(int i = 1; i < this.game.height - 1; i++)
            voies.add(new Lane(this.game, this.game.defaultDensity, i));
        voies.add(new Lane(this.game, 0, this.game.height));
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
