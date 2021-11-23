package environment;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

import java.util.ArrayList;

// available methods : isSafe, update
public class EnvInf extends Environment implements IEnvironment {
    private int score = 0;
//    private ArrayList<Lane> voies = new ArrayList<>();

    //    @Override
    public EnvInf(Game g) {
//        this.voies = new ArrayList();
//        this.voies.add(new Lane(g, 0, 0));
//        this.game = g;
        super(g);
        this.game = g;


    }

    void assignVoies()
    {
        for(int i = 1; i < super.game.height - 1; i++)
            this.voies.add(new Lane(super.game, super.game.defaultDensity, i));
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
