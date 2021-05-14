package org.csc133.a3.visitors;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.Bird;
import org.csc133.a3.gameobjects.Helicopter;
import org.csc133.a3.gameobjects.RefuelingBlimp;
import org.csc133.a3.gameobjects.SkyScraper;

public class BlimpVisitor implements IVisitor{
    private RefuelingBlimp blimp;
    private GameWorld gameWorld;

    public BlimpVisitor(RefuelingBlimp b, GameWorld gw){
        this.blimp = b;
        this.gameWorld = gw;
    }

    @Override
    public void visit(Helicopter h) {
        gameWorld.collideWithBlimp(h, blimp);
    }

    @Override
    public void visit(Bird b) {

    }

    @Override
    public void visit(SkyScraper s) {

    }

    @Override
    public void visit(RefuelingBlimp b) {

    }
}
