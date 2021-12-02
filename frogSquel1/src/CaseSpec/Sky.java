package CaseSpec;

import util.Case;
import graphicalElements.Element;
import gameCommons.Game;

import java.awt.*;

public class Sky extends CaseSpec{
    private final Color colorP=Color.MAGENTA;
    public Sky(Case c, Game g) {
        super(c, g);
    }

    public void addGraphics() {
        Color color = colorP;
        super.game.getGraphic().add(new Element(super.pos.absc, super.pos.ord, color));
    }
}
