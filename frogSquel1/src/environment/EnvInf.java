package environment;
import gameCommons.Game;
import util.Case;

// available methods : isSafe, update
public class EnvInf extends Environment{
    private int score = 0;

    public EnvInf(Game g) {
        super(g);
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean isWinningPosition(Case aCase) {
        return false;
    }
}
