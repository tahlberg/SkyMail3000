package org.csc133.a3.gameobjects;

import org.csc133.a3.GameWorld;
import org.csc133.a3.visitors.*;

public class PlayerHelicopter extends Helicopter{

    private static PlayerHelicopter instance;

    private PlayerHelicopter(double x, double y){
        super(x, y);
    }

    public static PlayerHelicopter getInstance(){
        return instance;
    }

    //creates or returns playerHelicopter with non-default constructor
    public static PlayerHelicopter createInstance(double x, double y){
        if(instance == null){
            instance = new PlayerHelicopter(x, y);
        }
        return instance;
    }

    @Override
    public void handleCollision(GameObject otherObject, GameWorld gw){
        if(this.checkCollisionVector(otherObject)){
            return;
        }
        IVisitor visitor;
        if(otherObject instanceof NonPlayerHelicopter){
            visitor = new NPHVisitor((NonPlayerHelicopter) otherObject, gw);
        } else if(otherObject instanceof Bird){
            visitor = new BirdVisitor((Bird) otherObject, gw);
        } else if(otherObject instanceof RefuelingBlimp) {
            visitor = new BlimpVisitor((RefuelingBlimp) otherObject, gw);
        } else if(otherObject instanceof SkyScraper){
            visitor = new SkyScraperVisitor((SkyScraper) otherObject, gw);
        } else{
            visitor = null;
            return;
        }
        this.accept(visitor);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(instance);
    }
}
