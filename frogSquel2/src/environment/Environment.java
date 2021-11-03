package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {


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

    }
}
