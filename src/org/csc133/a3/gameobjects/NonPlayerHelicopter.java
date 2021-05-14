package org.csc133.a3.gameobjects;

import org.csc133.a3.GameWorld;
import org.csc133.a3.strategies.AttackStrategy;
import org.csc133.a3.strategies.DefendStrategy;
import org.csc133.a3.strategies.IStrategy;
import org.csc133.a3.strategies.PatrolStrategy;
import org.csc133.a3.visitors.*;

import java.util.Random;

public class NonPlayerHelicopter extends Helicopter{
    private GameWorld gameWorld;
    private IStrategy strategy;
    private int stratNum;

    public NonPlayerHelicopter(int x, int y, GameWorld gw){
        super(x, y);
        gameWorld = gw;
        this.setStrategy();
        this.setSpeed(5);
    }

    public void setStrategy(){
        Random randGen = new Random();
        stratNum = randGen.nextInt(3);
        switch(stratNum){
            case 0:
                this.strategy = new AttackStrategy();
                break;
            case 1:
                this.strategy = new DefendStrategy();
                break;
            case 2:
                this.strategy = new PatrolStrategy();
                break;
        }
    }

    @Override
    public void handleCollision(GameObject otherObject, GameWorld gw){
        if(this.checkCollisionVector(otherObject)){
            return;
        }
        IVisitor visitor;
        if(otherObject instanceof PlayerHelicopter){
            visitor = new PlayerVisitor((PlayerHelicopter) otherObject, gw);
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
        visitor.visit(this);
    }

    public void invokeStrategy(){
        strategy.doStrategy(gameWorld, this);
    }

    //Overrides the toString function with object details
    @Override
    public String toString(){
        String objString;
        objString = super.toString() + " Strategy number: " + stratNum;
        return objString;
    }

}
