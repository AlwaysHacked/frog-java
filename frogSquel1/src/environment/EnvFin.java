package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class EnvFin extends Environment implements IEnvironment {
    public EnvFin(Game g) {
        super(g);
    }

    @Override
    public void moveLanes(Game g) { }
}
