package org.csc133.a3.visitors;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.*;

public class PlayerVisitor implements IVisitor {
    private Helicopter helicopter;
    private GameWorld gameWorld;

    public PlayerVisitor(Helicopter heli, GameWorld gw){
        helicopter = heli;
        gameWorld = gw;
    }

    @Override
    public void visit(Helicopter h) {
        gameWorld.collideWithHelicopter(this.helicopter, h);
    }

    @Override
    public void visit(Bird b) {
        gameWorld.collideWithBird(this.helicopter, b);
    }

    @Override
    public void visit(SkyScraper s) {
        gameWorld.collideWithSkyScraper(this.helicopter, s);
    }

    @Override
    public void visit(RefuelingBlimp b) {
        gameWorld.collideWithBlimp(this.helicopter, b);
    }
}
