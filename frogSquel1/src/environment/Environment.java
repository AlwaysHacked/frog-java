package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
    private ArrayList<Lane> voies;

    public Environment(Game g){
        voies = new ArrayList<>();
        for(int i = 1; i < g.height - 1; i++)
            voies.add(new Lane(g, i));
    }

    @Override
    public boolean isSafe(Case aCase) {
        return true;
    }

    @Override
    public boolean isWinningPosition(Case aCase) {
        return false;
    }

    @Override
    public void update() {
        for(Lane l : voies)
            l.update();
    }
}
