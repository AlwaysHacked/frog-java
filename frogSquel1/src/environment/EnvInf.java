package environment;
import gameCommons.Game;
import util.Case;

import java.util.ArrayList;

// available methods : isSafe, update
public class EnvInf extends Environment{
    private int score = 0;

//    @Override
    public EnvInf(Game g) {
        voies = new ArrayList<>();

        for(int i = 0; i <= g.frogMaxHeight; i++)
            voies.add(new Lane(g, 0, i));

        for(int i = g.frogMaxHeight + 1; i < g.height; i++)
            voies.add(new Lane(g, 0.001, i));
    }

    public void moveLanes(Game g, boolean b){
        if (b) {
            voies.add(new Lane(g, g.defaultDensity, voies.size()));
            for (Lane l : voies){
                l.moveOneLaneToDown();
            }
        }
        else {
            for (Lane l : voies){
                l.moveOneLaneToUp();
            }
        }

        if(voies.size() >= g.height + (int) (g.height / 3))
            voies.remove(0);
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean isWinningPosition(Case aCase) {
        return false;
    }
}
