package CaseSpec;

import util.Case;
import gameCommons.Game;

public abstract class CaseSpec {
    protected Game game;
    protected Case pos;

    public CaseSpec(Case c, Game g){
        this.pos = c;
        this.game=g;
    }

    public Case getPos(){
        return this.pos;
    }


}
