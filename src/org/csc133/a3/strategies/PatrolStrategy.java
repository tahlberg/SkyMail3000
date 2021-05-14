package org.csc133.a3.strategies;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.NonPlayerHelicopter;

import java.util.Random;

public class PatrolStrategy implements IStrategy {
    public void doStrategy(GameWorld gw, NonPlayerHelicopter nph){
        Random randGen = new Random();
        int changeChance = randGen.nextInt(2);
        switch(changeChance) {
            case 0:
                nph.setHeading(nph.getHeading()-5);
                break;
            case 1:
                break;
            case 2:
                nph.setHeading((nph.getHeading()+5));
        }
    }
}
