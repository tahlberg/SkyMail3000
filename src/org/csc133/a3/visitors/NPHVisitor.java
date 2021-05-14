package org.csc133.a3.visitors;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.*;

public class NPHVisitor implements IVisitor{
    NonPlayerHelicopter nonPlayerHelicopter;
    GameWorld gameWorld;

    public NPHVisitor(NonPlayerHelicopter nph, GameWorld gw){
        nonPlayerHelicopter = nph;
        gameWorld = gw;
    }

    @Override
    public void visit(Helicopter h) {
        gameWorld.collideWithHelicopter(this.nonPlayerHelicopter, h);
    }

    @Override
    public void visit(Bird b) {
        gameWorld.collideWithBird(this.nonPlayerHelicopter, b);
    }

    @Override
    public void visit(SkyScraper s) {
        gameWorld.collideWithSkyScraper(this.nonPlayerHelicopter, s);
    }

    @Override
    public void visit(RefuelingBlimp b) {
        gameWorld.collideWithBlimp(this.nonPlayerHelicopter, b);
    }
}
