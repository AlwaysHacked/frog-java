package CaseSpec;

import util.Case;

public abstract class CaseSpec {
    protected Case pos;
    private int exist;

    public CaseSpec(Case c, int exist){
        this.exist = exist;
        this.pos = c;
    }


}
