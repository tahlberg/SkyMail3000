package org.csc133.a3.visitors;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.Bird;
import org.csc133.a3.gameobjects.Helicopter;
import org.csc133.a3.gameobjects.RefuelingBlimp;
import org.csc133.a3.gameobjects.SkyScraper;

public class SkyScraperVisitor implements IVisitor{
    private SkyScraper scraper;
    private GameWorld gameWorld;

    public SkyScraperVisitor(SkyScraper s, GameWorld gw){
        this.scraper = s;
        this.gameWorld = gw;
    }

    @Override
    public void visit(Helicopter h) {
        gameWorld.collideWithSkyScraper(h, scraper);
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
