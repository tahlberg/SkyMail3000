package org.csc133.a3.visitors;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.Bird;
import org.csc133.a3.gameobjects.Helicopter;
import org.csc133.a3.gameobjects.RefuelingBlimp;
import org.csc133.a3.gameobjects.SkyScraper;

public class BirdVisitor implements IVisitor{
    private Bird bird;
    private GameWorld gameWorld;

    public BirdVisitor(Bird b, GameWorld gw){
        bird = b;
        gameWorld = gw;
    }

    @Override
    public void visit(Helicopter h) {
        gameWorld.collideWithBird(h, bird);
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
